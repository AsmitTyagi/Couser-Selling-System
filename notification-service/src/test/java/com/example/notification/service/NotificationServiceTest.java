package com.example.notification.service;

import com.example.notification.exception.NotificationException;
import com.example.notification.model.Notification;
import com.example.notification.repository.NotificationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class NotificationServiceTest {

    @Mock
    private NotificationRepository notificationRepository;

    @InjectMocks
    private NotificationService notificationService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSendNotificationSuccess() {
        Notification notification = Notification.builder()
                .userId(1L)
                .message("Course Purchased!")
                .build();

        when(notificationRepository.save(any(Notification.class))).thenReturn(notification);

        Notification result = notificationService.sendNotification(notification);

        assertEquals("SENT", result.getStatus());
    }

    @Test
    void testSendNotificationThrowsException() {
        Notification emptyMessage = Notification.builder()
                .userId(1L)
                .message("")
                .build();

        assertThrows(NotificationException.class, () -> {
            notificationService.sendNotification(emptyMessage);
        });
    }
}
