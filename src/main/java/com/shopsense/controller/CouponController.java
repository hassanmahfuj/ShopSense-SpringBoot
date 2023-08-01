package com.shopsense.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shopsense.dao.CouponDA;
import com.shopsense.model.Coupon;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class CouponController {

	CouponDA da = new CouponDA();

	@GetMapping(value = "/coupon/check")
	public Coupon checkCoupon(@RequestParam String code) {
		return da.checkCoupon(code);
	}

	@GetMapping(value = "/coupon/all")
	public List<Coupon> getAllCoupons() {
		return da.getAllCoupons();
	}

	@PostMapping(value = "/coupon")
	public Coupon createCoupon(@RequestBody Coupon c) {
		return da.createCoupon(c);
	}

	@PutMapping(value = "/coupon")
	public boolean updateCoupon(@RequestBody Coupon c) {
		return da.updateCoupon(c);
	}

	@DeleteMapping(value = "/coupon")
	public boolean deleteCoupon(@RequestParam int couponId) {
		return da.deleteCoupon(couponId);
	}
}
