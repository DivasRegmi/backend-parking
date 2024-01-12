package com.project.carparking.repository;

import com.project.carparking.entity.ParkingSlot;
import com.project.carparking.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParkingSlotRepository extends JpaRepository<ParkingSlot, Long> {
    Optional<ParkingSlot> findByParkingSlot(Long id);
}
