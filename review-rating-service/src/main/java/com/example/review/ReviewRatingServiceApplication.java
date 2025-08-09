package com.example.review;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ReviewRatingServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ReviewRatingServiceApplication.class, args);
    }
}
