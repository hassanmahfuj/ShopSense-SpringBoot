package com.shopsense.model;

import java.sql.Date;

public class OrderDetails {
	int id;
	int orderId;
	int productId;
	int sellerId;
	String storeName;
	String productName;
	double productUnitPrice;
	String productThumbnailUrl;
	String status;
	int quantity;
	double subTotal;
	Date deliveryDate;

	public OrderDetails() {
		super();
	}

	public OrderDetails(int id, int orderId, int productId, int sellerId, String storeName, String productName,
			double productUnitPrice, String productThumbnailUrl, String status, int quantity, double subTotal,
			Date deliveryDate) {
		super();
		this.id = id;
		this.orderId = orderId;
		this.productId = productId;
		this.sellerId = sellerId;
		this.storeName = storeName;
		this.productName = productName;
		this.productUnitPrice = productUnitPrice;
		this.productThumbnailUrl = productThumbnailUrl;
		this.status = status;
		this.quantity = quantity;
		this.subTotal = subTotal;
		this.deliveryDate = deliveryDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getSellerId() {
		return sellerId;
	}

	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getProductUnitPrice() {
		return productUnitPrice;
	}

	public void setProductUnitPrice(double productUnitPrice) {
		this.productUnitPrice = productUnitPrice;
	}

	public String getProductThumbnailUrl() {
		return productThumbnailUrl;
	}

	public void setProductThumbnailUrl(String productThumbnailUrl) {
		this.productThumbnailUrl = productThumbnailUrl;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
}
