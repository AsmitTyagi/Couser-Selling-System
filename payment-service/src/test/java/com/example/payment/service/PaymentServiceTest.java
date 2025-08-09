package com.example.payment.service;

import com.example.payment.model.Payment;
import com.example.payment.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PaymentServiceTest {

    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private PaymentService paymentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testMakePayment() {
        Payment payment = Payment.builder().purchaseId(1L).amount(299.99).build();
        when(paymentRepository.save(any(Payment.class))).thenReturn(payment);

        Payment result = paymentService.makePayment(payment);

        assertEquals("SUCCESS", result.getStatus());
        assertEquals(299.99, result.getAmount());
    }

    @Test
    void testGetPaymentById() {
        Payment payment = Payment.builder().id(1L).purchaseId(2L).status("SUCCESS").amount(299.99).build();
        when(paymentRepository.findById(1L)).thenReturn(Optional.of(payment));

        Payment result = paymentService.getPaymentById(1L);

        assertEquals("SUCCESS", result.getStatus());
    }
}
