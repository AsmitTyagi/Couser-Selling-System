package com.example.purchase.service;

import com.example.purchase.model.Purchase;

import com.example.purchase.repository.PurchaseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PurchaseServiceTest {

    @Mock
    private PurchaseRepository purchaseRepository;

    @InjectMocks
    private PurchaseService purchaseService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreatePurchase() {
    	 Long userId = 1L;
    	 Long courseId = 2L;
        Purchase purchase = Purchase.builder().userId(userId).courseId(courseId).paymentStatus(true).build();
        when(purchaseRepository.save(any(Purchase.class))).thenReturn(purchase);

        Purchase result = purchaseService.createPurchase(userId, courseId);

        assertNotNull(result);
        assertTrue(result.isPaymentStatus());
    }

    @Test
    void testGetPurchaseById() {
        Purchase purchase = Purchase.builder().id(1L).userId(1L).courseId(2L).paymentStatus(true).build();
        when(purchaseRepository.findById(1L)).thenReturn(Optional.of(purchase));

        Purchase result = purchaseService.getPurchaseById(1L);

        assertEquals(1L, result.getId());
    }
}
