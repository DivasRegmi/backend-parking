package com.project.carparking.controller;

import com.project.carparking.config.AppConstants;
import com.project.carparking.dto.WithPaginationResponse;
import com.project.carparking.entity.Vehicle;
import com.project.carparking.service.VehicleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @GetMapping()
    public ResponseEntity<WithPaginationResponse<Vehicle>> fetchVehicleList(@RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
                                                                            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize) {

        return ResponseEntity.ok(vehicleService.findAll(pageNo, pageSize));
    }

    @GetMapping("/{vehicleId}")
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable Long vehicleId) {
        return ResponseEntity.ok(vehicleService.getVehicleById(vehicleId));
    }

    @PostMapping("/users/{userId}")
    public Vehicle createVehicle(@PathVariable Long userId ,@Valid @RequestBody Vehicle vehicle) {
        return vehicleService.saveVehicle(userId,vehicle);
    }

    @PatchMapping("/{vehicleId}")
    public ResponseEntity<Vehicle> updateVehicleDetails(@PathVariable Long vehicleId,@Valid @RequestBody Vehicle updatedVehicle) {
        Vehicle updatedVehicleData = vehicleService.updateVehicleDetails(vehicleId, updatedVehicle);

        return new ResponseEntity<>(updatedVehicleData, HttpStatus.OK);
    }

    @DeleteMapping("/{vehicleId}")
    public void deleteVehicle(@PathVariable Long vehicleId) {
        vehicleService.deleteVehicle(vehicleId);
    }
}