package com.project.carparking.repository;

import com.project.carparking.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByUserId(Long userId);
    List<Notification> findByUserIdOrderByDateDesc(Long userId);

    void deleteByDateBefore(LocalDateTime oneMonthAgo);
}