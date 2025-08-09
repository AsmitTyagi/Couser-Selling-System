package com.example.purchase.repository;

import com.example.purchase.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
	boolean existsByUserIdAndCourseId(Long userId, Long courseId);

}
