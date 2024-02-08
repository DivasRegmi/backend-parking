package com.project.carparking.service;

import com.project.carparking.dto.WithPaginationResponse;
import com.project.carparking.dto.converter.Converter;
import com.project.carparking.entity.ParkingSlot;
import com.project.carparking.entity.User;
import com.project.carparking.entity.Vehicle;
import com.project.carparking.exception.ResourceNotFoundException;
import com.project.carparking.repository.ParkingSlotRepository;
import com.project.carparking.repository.UserRepository;
import com.project.carparking.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private ParkingSlotRepository parkingSlotRepository;

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

    public Vehicle saveVehicle(Long userId, Vehicle vehicle) {
        // Find the user
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        // Associate the user with the vehicle
        vehicle.setUser(user);
        Vehicle savedVehicle = vehicleRepository.save(vehicle);

        if (vehicle.getParkingSlot() != null) {
            ParkingSlot parkingSlot = vehicle.getParkingSlot();
            parkingSlot.setUser(user);
            parkingSlot.setVehicle(savedVehicle);
            parkingSlotRepository.save(parkingSlot);
        }

        // Save the vehicle
        return savedVehicle;
    }



    public Vehicle updateVehicleDetails(Long vehicleId, Vehicle vehicleRequest) {
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

                parkingSlot.setUser(existingVehicle.getUser());
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
}
