package com.example.purchase.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.purchase.client.dto.NotificationRequest;

@FeignClient(name = "notification-service")
public interface NotificationClient {
    @PostMapping("/notify")
    void send(@RequestBody NotificationRequest request);
}
