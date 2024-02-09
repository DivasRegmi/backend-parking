package com.project.carparking.repository;

import com.project.carparking.entity.Notification;
import com.project.carparking.entity.NotificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NotificationTokenRepository extends JpaRepository<NotificationToken, Long> {

    List<Notification> findByUserId(Long userId);
    @Query("SELECT nt.pushNotificationToken FROM NotificationToken nt WHERE nt.user.id = :userId")
    List<String> findPushNotificationTokensByUserId(Long userId);


    NotificationToken findByPushNotificationTokenAndUserId(String pushNotificationToken, Long userId);
}