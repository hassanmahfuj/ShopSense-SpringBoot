package com.shopsense.model;

public class CartItem {
	int id;
	int customerId;
	int productId;
	int sellerId;
	String storeName;
	String productName;
	String productThumbnailUrl;
	double productUnitPrice;
	int productQuantity;
	double subTotal;

	public CartItem() {
		super();
	}

	public CartItem(int id, int customerId, int productId, int sellerId, String storeName, String productName,
			String productThumbnailUrl, double productUnitPrice, int productQuantity, double subTotal) {
		super();
		this.id = id;
		this.customerId = customerId;
		this.productId = productId;
		this.sellerId = sellerId;
		this.storeName = storeName;
		this.productName = productName;
		this.productThumbnailUrl = productThumbnailUrl;
		this.productUnitPrice = productUnitPrice;
		this.productQuantity = productQuantity;
		this.subTotal = subTotal;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
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

	public String getProductThumbnailUrl() {
		return productThumbnailUrl;
	}

	public void setProductThumbnailUrl(String productThumbnailUrl) {
		this.productThumbnailUrl = productThumbnailUrl;
	}

	public double getProductUnitPrice() {
		return productUnitPrice;
	}

	public void setProductUnitPrice(double productUnitPrice) {
		this.productUnitPrice = productUnitPrice;
	}

	public int getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}

	public double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}
}
