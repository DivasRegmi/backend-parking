package com.project.carparking.service;

import com.project.carparking.dto.WithPaginationResponse;
import com.project.carparking.dto.converter.Converter;
import com.project.carparking.entity.ParkingSlot;
import com.project.carparking.entity.User;
import com.project.carparking.entity.Vehicle;
import com.project.carparking.entity.VehicleEntryExitStamp;
import com.project.carparking.exception.ResourceCanNotCreateException;
import com.project.carparking.exception.ResourceNotFoundException;
import com.project.carparking.repository.ParkingSlotRepository;
import com.project.carparking.repository.UserRepository;
import com.project.carparking.repository.VehicleEntryExitStampRepository;
import com.project.carparking.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private ParkingSlotRepository parkingSlotRepository;

    @Autowired
    private VehicleEntryExitStampRepository vehicleEntryExitStampRepository;

    public WithPaginationResponse<Vehicle> findAll(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Vehicle> vehiclePage = vehicleRepository.findAll(pageable);

        WithPaginationResponse<Vehicle> vehicleAndPagination = new WithPaginationResponse<>();
        vehicleAndPagination.getContent().addAll(vehiclePage.stream().toList());
        vehicleAndPagination.setPaginationResponse(Converter.convertPageToPageResponse(vehiclePage));

        return vehicleAndPagination;
    }

    public Vehicle getVehicleById(Long vehicleId) {
        Vehicle vehicle = vehicleRepository.findById(vehicleId).orElseThrow(() -> {
            return new ResourceNotFoundException("Vehicle " + vehicleId + " not found");
        });

        vehicle.setParkingSlot(vehicle.getParkingSlot());


        return vehicle;
    }

    public List<Vehicle> getVehicleByUserId(Long userId) {

        return vehicleRepository.findByUserId(userId);
    }

    @Transactional
    public Vehicle saveVehicle(Long userId, Vehicle vehicle) {

        if (vehicle.getParkingSlot().getSlotNumber() != null) {
            ParkingSlot parkingSlot = vehicle.getParkingSlot();
            Optional<ParkingSlot> bySlotNumber = parkingSlotRepository.findBySlotNumber(parkingSlot.getSlotNumber());

            if (bySlotNumber.isPresent()) {
                throw new ResourceCanNotCreateException("Parking Slot already allocated. Choose a new one.");
            }
        }

        // Find the user
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        // Associate the user with the vehicle
        vehicle.setUser(user);
        Vehicle savedVehicle = vehicleRepository.save(vehicle);

        if (vehicle.getParkingSlot() != null) {
            ParkingSlot parkingSlot = vehicle.getParkingSlot();

            parkingSlot.setVehicle(savedVehicle);
            parkingSlotRepository.save(parkingSlot);

        }

        // Save the vehicle
        return savedVehicle;
    }


    public Vehicle updateVehicleDetails(Long vehicleId, Vehicle vehicleRequest) {
        if (vehicleRequest.getParkingSlot().getSlotNumber() != null) {
            ParkingSlot parkingSlot = vehicleRequest.getParkingSlot();
            Optional<ParkingSlot> bySlotNumber = parkingSlotRepository.findBySlotNumber(parkingSlot.getSlotNumber());

            if (bySlotNumber.isPresent()) {
                throw new ResourceCanNotCreateException("Parking Slot already allocated. Choose a new one.");
            }
        }

        Vehicle existingVehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle " + vehicleId + " not found"));

        // Update vehicle details from VehicleRequest
        if (vehicleRequest.getNumberPlate() != null) {
            existingVehicle.setNumberPlate(vehicleRequest.getNumberPlate());
        }
        if (vehicleRequest.getModel() != null) {
            existingVehicle.setModel(vehicleRequest.getModel());
        }
        if (vehicleRequest.getParkingSlot() != null) {
            ParkingSlot parkingSlot = vehicleRequest.getParkingSlot();
            if (parkingSlot.getId() != null) {
                // Try to find the existing ParkingSlot by ID
                ParkingSlot existingParkingSlot = parkingSlotRepository.findById(parkingSlot.getId())
                        .orElseThrow(() -> new ResourceNotFoundException("ParkingSlot " + parkingSlot.getId() + " not found"));
                // Update the existing ParkingSlot
                existingParkingSlot.setSlotStatus(parkingSlot.isSlotStatus());
                existingParkingSlot.setSlotNumber(parkingSlot.getSlotNumber());
                // Link the ParkingSlot with the Vehicle

                // Save the updated ParkingSlot
                parkingSlotRepository.save(existingParkingSlot);
            } else {


                parkingSlot.setVehicle(existingVehicle);
                parkingSlotRepository.save(parkingSlot);

            }
        }

        // Save the updated vehicle
        vehicleRepository.save(existingVehicle);
        return existingVehicle;
    }


    //    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteVehicle(Long vehicleId) {
        boolean existsById = vehicleRepository.existsById(vehicleId);
        if (existsById) {
            vehicleRepository.deleteById(vehicleId);
        } else {
            throw new ResourceNotFoundException("Vehicle " + vehicleId + " not found");
        }
    }

    //11 -> true false
    public void updateParkingSlotStatus(String slotNumber, boolean slotStatus) {
        ParkingSlot parkingSlot = parkingSlotRepository.findBySlotNumber(slotNumber).orElseThrow(() -> {
            return new ResourceNotFoundException("Parking Slot " + slotNumber + " not found");
        });

        parkingSlot.setSlotStatus(slotStatus);
        parkingSlotRepository.save(parkingSlot);

        Vehicle vehicle = parkingSlot.getVehicle();

        VehicleEntryExitStamp lastStamp = vehicleEntryExitStampRepository.findFirstByOrderByEntryTimeDesc().orElseThrow(() -> {
            return new ResourceNotFoundException("VehicleEntryExitStamp not found");
        });

        if (slotStatus && lastStamp.getExitTime() != null) {
            //11 -> true  after 5 min 11-> true
            LocalDateTime currentTime = LocalDateTime.now();
            VehicleEntryExitStamp vehicleEntryExitStamp = new VehicleEntryExitStamp();
            vehicleEntryExitStamp.setCountFalseSlotStatus(0);
            vehicleEntryExitStamp.setEntryTime(currentTime);
            vehicleEntryExitStamp.setVehicle(vehicle);

            vehicleEntryExitStampRepository.save(vehicleEntryExitStamp);
        } else if (!slotStatus && lastStamp.getCountFalseSlotStatus() < 1) {
            lastStamp.setCountFalseSlotStatus(lastStamp.getCountFalseSlotStatus() + 1);
            vehicleEntryExitStampRepository.save(lastStamp);

        } else if (!slotStatus && lastStamp.getCountFalseSlotStatus() == 1) {
            LocalDateTime currentTime = LocalDateTime.now();
            LocalDateTime fiveMinutesAgo = currentTime.minusMinutes(5);
            lastStamp.setExitTime(fiveMinutesAgo);
            lastStamp.setCountFalseSlotStatus(lastStamp.getCountFalseSlotStatus() + 1);
            vehicleEntryExitStampRepository.save(lastStamp);
        }
    }

    public String[] getAllNumberPlates() {
        return vehicleRepository.findAllNumberPlates();
    }
}
