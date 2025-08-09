//package com.example.purchase.client;
//
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//@FeignClient(name = "purchase-service", url = "http://localhost:8083")
//public interface PurchaseClient {
//    @GetMapping("/purchase/validate")
//    boolean hasUserPurchasedCourse(@RequestParam Long userId, @RequestParam Long courseId);
//}
//
