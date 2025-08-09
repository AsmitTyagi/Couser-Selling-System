package com.example.course.service;

import com.example.course.dto.CourseDTO;

import com.example.course.model.Course;
import com.example.course.repository.CourseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CourseServiceTest {

    @Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private CourseService courseService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddCourse() {
        CourseDTO dto = CourseDTO.builder()
                .title("Spring Boot")
                .description("Learn Spring Boot")
                .price(199.99)
                .build();

        Course course = Course.builder()
                .id(1L)
                .title("Spring Boot")
                .description("Learn Spring Boot")
                .price(199.99)
                .build();

        when(courseRepository.save(any(Course.class))).thenReturn(course);

        CourseDTO result = courseService.addCourse(dto);

        assertNotNull(result);
        assertEquals("Spring Boot", result.getTitle());
    }

    @Test
    void testGetAllCourses() {
        List<Course> courses = Arrays.asList(
                Course.builder().id(1L).title("Course 1").description("Desc 1").price(100).build(),
                Course.builder().id(2L).title("Course 2").description("Desc 2").price(200).build()
        );

        when(courseRepository.findAll()).thenReturn(courses);

        List<CourseDTO> result = courseService.getAllCourses();

        assertEquals(2, result.size());
    }
}
