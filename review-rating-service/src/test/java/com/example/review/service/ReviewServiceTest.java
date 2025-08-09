package com.example.review.service;

import com.example.review.exception.ReviewException;
import com.example.review.model.Review;
import com.example.review.repository.ReviewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ReviewServiceTest {

    @Mock
    private ReviewRepository reviewRepository;

    @InjectMocks
    private ReviewService reviewService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddReviewSuccess() {
        Review review = Review.builder()
                .userId(1L)
                .courseId(2L)
                .rating(5)
                .comment("Great course!")
                .build();

        when(reviewRepository.save(any(Review.class))).thenReturn(review);

        Review result = reviewService.addReview(review);
        assertEquals(5, result.getRating());
    }

    @Test
    void testAddReviewThrowsException() {
        Review invalidReview = Review.builder()
                .userId(1L)
                .courseId(2L)
                .rating(0)
                .comment("Bad rating")
                .build();

        assertThrows(ReviewException.class, () -> {
            reviewService.addReview(invalidReview);
        });
    }
}
