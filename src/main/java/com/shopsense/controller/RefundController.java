package com.shopsense.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shopsense.dao.RefundDA;
import com.shopsense.model.RefundDetails;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class RefundController {

	@Autowired
	RefundDA refundDA;

	@PostMapping(value = "/customer/refund")
	public RefundDetails createRefund(@RequestBody RefundDetails a) {
		return refundDA.createRefund(a);
	}
	
	@PutMapping(value = "/admin/refund")
	public RefundDetails updateRefund(@RequestBody RefundDetails a) {
		return refundDA.updateRefund(a);
	}
	
	@GetMapping(value = "/admin/refund")
	public List<RefundDetails> getAllRefund() {
		return refundDA.getAllRefund();
	}
	
	@GetMapping(value = "/seller/refund/{sellerId}")
	public List<RefundDetails> getSellerRefund(@PathVariable int sellerId) {
		return refundDA.getSellerRefund(sellerId);
	}
}
