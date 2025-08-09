package com.example.course.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.course.dto.CourseDTO;
import com.example.course.service.CourseService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;

//    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<CourseDTO> addCourse(@RequestBody CourseDTO dto) {
    	
//    	if (!"ADMIN".equalsIgnoreCase(role)) {
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
//        }
    	
        return ResponseEntity.ok(courseService.addCourse(dto));
    }

    @GetMapping
    public ResponseEntity<List<CourseDTO>> getCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }
}
