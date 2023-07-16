package com.shopsense;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shopsense.models.Coupon;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class CouponController {

	CouponDA da = new CouponDA();

	@GetMapping(value = "/coupon/check")
	public Coupon checkCoupon(@RequestParam String code) {
		return da.checkCoupon(code);
	}
}
