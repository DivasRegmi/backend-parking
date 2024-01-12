package com.project.carparking.repository;

import com.project.carparking.entity.Notification;
import com.project.carparking.entity.ParkingSlot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    Optional<Notification> findByNotification(Long id);
}
