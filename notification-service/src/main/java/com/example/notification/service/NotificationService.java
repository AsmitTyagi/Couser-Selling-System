package com.example.notification.service;

import com.example.notification.exception.NotificationException;
import com.example.notification.model.Notification;
import com.example.notification.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;

    public Notification sendNotification(Notification notification) {
        if (notification.getMessage() == null || notification.getMessage().isEmpty()) {
            throw new NotificationException("Message cannot be empty");
        }

        notification.setStatus("SENT");
        return notificationRepository.save(notification);
    }

    public List<Notification> getAll() {
        return notificationRepository.findAll();
    }
}
