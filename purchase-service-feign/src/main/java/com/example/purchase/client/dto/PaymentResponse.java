package com.example.purchase.client.dto;

import lombok.Data;

@Data
public class PaymentResponse {
    private String status;
    private Double amount;
}
