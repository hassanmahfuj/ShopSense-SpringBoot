package com.shopsense.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Coupon {
	int id;
	String couponCode;
	int couponValue;
	String couponType;
	String status;

}
