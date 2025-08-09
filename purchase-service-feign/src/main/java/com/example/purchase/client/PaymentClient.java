package com.example.purchase.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.purchase.client.dto.PaymentRequest;
import com.example.purchase.client.dto.PaymentResponse;

@FeignClient(name = "payment-service")
public interface PaymentClient {
    @PostMapping("/payments")
    PaymentResponse makePayment(@RequestBody PaymentRequest request);
}
