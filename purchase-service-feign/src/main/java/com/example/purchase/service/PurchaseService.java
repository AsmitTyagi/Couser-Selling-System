package com.example.purchase.service;

import com.example.purchase.client.CourseClient;

import com.example.purchase.client.NotificationClient;
import com.example.purchase.client.PaymentClient;
import com.example.purchase.client.dto.CourseDTO;
import com.example.purchase.client.dto.NotificationRequest;
import com.example.purchase.client.dto.PaymentRequest;
import com.example.purchase.model.Purchase;
import com.example.purchase.repository.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final CourseClient courseClient;
    private final PaymentClient paymentClient;
    private final NotificationClient notificationClient;

    public Purchase createPurchase(Long userId, Long courseId) {
        CourseDTO course = courseClient.getCourse(courseId);

        Purchase purchase = Purchase.builder()
                .userId(userId)
                .courseId(courseId)
                .paymentStatus(false)
                .build();

        purchase = purchaseRepository.save(purchase);

        PaymentRequest paymentRequest = new PaymentRequest();
        paymentRequest.setPurchaseId(purchase.getId());
        paymentRequest.setAmount(course.getPrice());

        var paymentResponse = paymentClient.makePayment(paymentRequest);
        purchase.setPaymentStatus("SUCCESS".equalsIgnoreCase(paymentResponse.getStatus()));

        purchase = purchaseRepository.save(purchase);

        NotificationRequest notification = new NotificationRequest();
        notification.setUserId(userId);
        notification.setMessage("Purchase successful for course: " + course.getTitle());

        notificationClient.send(notification);

        return purchase;
    }
    
    public boolean hasUserPurchasedCourse(Long userId, Long courseId) {
        return purchaseRepository.existsByUserIdAndCourseId(userId, courseId);
    }

    public List<Purchase> getAllPurchases() {
        return purchaseRepository.findAll();
    }

    public Purchase getPurchaseById(Long id) {
        return purchaseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Purchase not found with ID: " + id));
    }
}
