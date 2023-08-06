package com.shopsense.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopsense.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

	List<Review> findAllByProductId(int productId);
}
