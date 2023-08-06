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

import com.shopsense.dto.WishlistDetail;
import com.shopsense.entity.Wishlist;
import com.shopsense.service.WishlistService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/wishlist")
public class WishlistController {

	@Autowired
	WishlistService ws;

	@PostMapping("/add")
	public boolean addToWishlist(@RequestBody Wishlist wishlist) {
		return ws.addToWishlist(wishlist);
	}

	@PostMapping("/remove")
	public boolean removeFromWishlist(@RequestBody Wishlist wishlist) {
		return ws.removeFromWishlist(wishlist);
	}

	@PostMapping("/check")
	public boolean isWishlisted(@RequestBody Wishlist wishlist) {
		return ws.isWishlisted(wishlist);
	}

	@GetMapping
	public List<WishlistDetail> findAllByCustomerId(@RequestParam int customerId) {
		return ws.findAllByCustomerId(customerId);
	}
}
