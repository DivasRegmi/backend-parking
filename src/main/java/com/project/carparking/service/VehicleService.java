package com.project.carparking.service;

import com.project.carparking.entity.Vehicle;
import com.project.carparking.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    public Optional<Vehicle> getVehicleById(Long id) {
        return vehicleRepository.findById(id);
    }

    public Optional<Vehicle> getVehicleByNumberPlate(String numberPlate) {
        return vehicleRepository.findByNumberPlate(numberPlate);
    }

    public Vehicle saveVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    public void deleteVehicle(Long id) {
        vehicleRepository.deleteById(id);
    }

    public Vehicle updateVehicle(Long id, Vehicle updatedVehicle) {
        return vehicleRepository.findById(id)
                .map(existingVehicle -> {
                    existingVehicle.setNumberPlate(updatedVehicle.getNumberPlate());
                    existingVehicle.setModel(updatedVehicle.getModel());
                    existingVehicle.setParkingSlot(updatedVehicle.getParkingSlot());
                    return vehicleRepository.save(existingVehicle);
                })
                .orElse(null); // or throw an exception if needed
    }
}
