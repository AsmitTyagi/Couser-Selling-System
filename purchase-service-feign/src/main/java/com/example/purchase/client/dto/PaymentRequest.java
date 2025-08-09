package com.example.purchase.client.dto;

import lombok.Data;

@Data
public class PaymentRequest {
    private Long purchaseId;
    private Double amount;
}
