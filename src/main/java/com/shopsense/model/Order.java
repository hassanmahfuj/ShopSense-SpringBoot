package com.shopsense.model;

import java.sql.Date;
import java.util.List;

public class Order {
	int id;
	Date orderDate;
	double orderTotal;
	int customerId;
	double discount;
	double shippingCharge;
	double tax;
	String shippingStreet;
	String shippingCity;
	String shippingPostCode;
	String shippingState;
	String shippingCountry;
	String status;
	double subTotal;
	String paymentStatus;
	String paymentMethod;
	String cardNumber;
	String cardCvv;
	String cardHolderName;
	String cardExpiryDate;
	double gatewayFee;
	List<OrderDetails> orderDetails;

	public Order() {
		super();
	}

	public Order(int id, Date orderDate, double orderTotal, int customerId, double discount, double shippingCharge,
			double tax, String shippingStreet, String shippingCity, String shippingPostCode, String shippingState,
			String shippingCountry, String status, double subTotal, String paymentStatus, String paymentMethod,
			String cardNumber, String cardCvv, String cardHolderName, String cardExpiryDate, double gatewayFee,
			List<OrderDetails> orderDetails) {
		super();
		this.id = id;
		this.orderDate = orderDate;
		this.orderTotal = orderTotal;
		this.customerId = customerId;
		this.discount = discount;
		this.shippingCharge = shippingCharge;
		this.tax = tax;
		this.shippingStreet = shippingStreet;
		this.shippingCity = shippingCity;
		this.shippingPostCode = shippingPostCode;
		this.shippingState = shippingState;
		this.shippingCountry = shippingCountry;
		this.status = status;
		this.subTotal = subTotal;
		this.paymentStatus = paymentStatus;
		this.paymentMethod = paymentMethod;
		this.cardNumber = cardNumber;
		this.cardCvv = cardCvv;
		this.cardHolderName = cardHolderName;
		this.cardExpiryDate = cardExpiryDate;
		this.gatewayFee = gatewayFee;
		this.orderDetails = orderDetails;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public double getOrderTotal() {
		return orderTotal;
	}

	public void setOrderTotal(double orderTotal) {
		this.orderTotal = orderTotal;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public double getShippingCharge() {
		return shippingCharge;
	}

	public void setShippingCharge(double shippingCharge) {
		this.shippingCharge = shippingCharge;
	}

	public double getTax() {
		return tax;
	}

	public void setTax(double tax) {
		this.tax = tax;
	}

	public String getShippingStreet() {
		return shippingStreet;
	}

	public void setShippingStreet(String shippingStreet) {
		this.shippingStreet = shippingStreet;
	}

	public String getShippingCity() {
		return shippingCity;
	}

	public void setShippingCity(String shippingCity) {
		this.shippingCity = shippingCity;
	}

	public String getShippingPostCode() {
		return shippingPostCode;
	}

	public void setShippingPostCode(String shippingPostCode) {
		this.shippingPostCode = shippingPostCode;
	}

	public String getShippingState() {
		return shippingState;
	}

	public void setShippingState(String shippingState) {
		this.shippingState = shippingState;
	}

	public String getShippingCountry() {
		return shippingCountry;
	}

	public void setShippingCountry(String shippingCountry) {
		this.shippingCountry = shippingCountry;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCardCvv() {
		return cardCvv;
	}

	public void setCardCvv(String cardCvv) {
		this.cardCvv = cardCvv;
	}

	public String getCardHolderName() {
		return cardHolderName;
	}

	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}

	public String getCardExpiryDate() {
		return cardExpiryDate;
	}

	public void setCardExpiryDate(String cardExpiryDate) {
		this.cardExpiryDate = cardExpiryDate;
	}

	public double getGatewayFee() {
		return gatewayFee;
	}

	public void setGatewayFee(double gatewayFee) {
		this.gatewayFee = gatewayFee;
	}

	public List<OrderDetails> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetails> orderDetails) {
		this.orderDetails = orderDetails;
	}

}
