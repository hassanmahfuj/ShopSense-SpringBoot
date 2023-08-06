package com.shopsense.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopsense.entity.Review;
import com.shopsense.repository.ReviewRepository;

@Service
public class ReviewService {

	@Autowired
	ReviewRepository rr;

	public boolean addReview(Review r) {
		rr.save(r);
		return true;
	}

	public List<Review> findAllByProductId(int productId) {
		return rr.findAllByProductId(productId);
	}
}
