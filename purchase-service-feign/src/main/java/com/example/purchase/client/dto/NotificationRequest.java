package com.example.purchase.client.dto;

import lombok.Data;

@Data
public class NotificationRequest {
    private Long userId;
    private String message;
}
