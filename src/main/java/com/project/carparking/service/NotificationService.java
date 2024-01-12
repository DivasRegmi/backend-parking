package com.project.carparking.service;

import com.project.carparking.entity.Notification;
import com.project.carparking.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    @Autowired
    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    public Optional<Notification> getNotificationById(Long id) {
        return notificationRepository.findById(id);
    }

    public void saveNotification(Notification notification) {
        notificationRepository.save(notification);
    }

    public void deleteNotification(Long id) {
        notificationRepository.deleteById(id);
    }

    public void updateNotification(Notification notification) {
        // Check if the notification with the given ID exists
        if (notification.getId() != null && notificationRepository.existsById(notification.getId())) {
            notificationRepository.save(notification);
        } else {
            // Handle the case where the notification with the given ID doesn't exist
            throw new IllegalArgumentException("Notification with ID " + notification.getId() + " not found");
        }
    }
}
