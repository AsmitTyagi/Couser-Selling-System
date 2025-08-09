package com.example.purchase.client;

import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.purchase.client.dto.CourseDTO;

@FeignClient(name = "course-service")
public interface CourseClient {
    @GetMapping("/courses/{id}")
    CourseDTO getCourse(@PathVariable Long id);
}
