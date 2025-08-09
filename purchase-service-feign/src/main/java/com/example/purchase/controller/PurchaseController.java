package com.example.purchase.controller;

import com.example.purchase.model.Purchase;
import com.example.purchase.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchases")
@RequiredArgsConstructor
public class PurchaseController {
    private final PurchaseService purchaseService;

    @PostMapping("/{userId}/{courseId}")
    public ResponseEntity<Purchase> purchaseCourse(@PathVariable Long userId, @PathVariable Long courseId) {
        return ResponseEntity.ok(purchaseService.createPurchase(userId, courseId));
    }

    @GetMapping
    public ResponseEntity<List<Purchase>> getAll() {
        return ResponseEntity.ok(purchaseService.getAllPurchases());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Purchase> getById(@PathVariable Long id) {
        return ResponseEntity.ok(purchaseService.getPurchaseById(id));
    }
    
    @GetMapping("/validate")
    public boolean hasUserPurchasedCourse(@RequestParam Long userId, @RequestParam Long courseId) {
        return purchaseService.hasUserPurchasedCourse(userId, courseId);
    }

}
