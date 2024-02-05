package com.project.carparking.repository;

import com.project.carparking.entity.EnumRole;
import com.project.carparking.entity.ParkingSlot;
import com.project.carparking.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParkingSlotRepository extends JpaRepository<ParkingSlot, Long> {

    List<ParkingSlot> findBySlotNumber(String slotNumber);
}