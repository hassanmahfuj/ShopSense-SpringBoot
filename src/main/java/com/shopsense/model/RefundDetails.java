package com.shopsense.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RefundDetails {
	private int refundId;
	private int orderId;
	private int orderDetailsId;
	private int sellerId;
	private String reason;
	private String bankNumber;
	private String bankName;
	private double amount;
	private String status;
}
