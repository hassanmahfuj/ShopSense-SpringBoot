package com.shopsense.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shopsense.entity.Review;
import com.shopsense.service.ReviewService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/review")
public class ReviewController {

	@Autowired
	ReviewService rs;

	@PostMapping("/add")
	public boolean addReview(@RequestBody Review r) {
		return rs.addReview(r);
	}

	@GetMapping
	public List<Review> findAllByProductId(@RequestParam int productId) {
		return rs.findAllByProductId(productId);
	}
}
