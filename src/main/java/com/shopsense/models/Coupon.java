package com.shopsense.models;

public class Coupon {
	int id;
	String couponCode;
	int couponValue;
	String couponType;

	public Coupon() {
		super();
	}

	public Coupon(int id, String couponCode, int couponValue, String couponType) {
		super();
		this.id = id;
		this.couponCode = couponCode;
		this.couponValue = couponValue;
		this.couponType = couponType;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCouponCode() {
		return couponCode;
	}

	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}

	public int getCouponValue() {
		return couponValue;
	}

	public void setCouponValue(int couponValue) {
		this.couponValue = couponValue;
	}

	public String getCouponType() {
		return couponType;
	}

	public void setCouponType(String couponType) {
		this.couponType = couponType;
	}

}
