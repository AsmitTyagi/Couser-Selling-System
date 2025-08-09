package com.example.review.service;

import com.example.review.client.PurchaseClient;
import com.example.review.exception.ReviewException;
import com.example.review.model.Review;
import com.example.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    
    private final PurchaseClient purchaseClient;

    public Review addReview(Review review) {
    	 boolean purchased = purchaseClient.hasUserPurchasedCourse(review.getUserId(), review.getCourseId());
    	 if (!purchased) {
             throw new IllegalStateException("User has not purchased this course.");
         }
        if (review.getRating() < 1 || review.getRating() > 5) {
            throw new ReviewException("Rating must be between 1 and 5");
        }
        return reviewRepository.save(review);
    }

    public List<Review> getReviewsForCourse(Long courseId) {
        return reviewRepository.findByCourseId(courseId);
    }
}
