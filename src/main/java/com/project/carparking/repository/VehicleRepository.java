package com.project.carparking.repository;

import com.project.carparking.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    Optional<Vehicle> findByNumberPlate(String numberPlate);
}