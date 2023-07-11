package com.shopsense.seller;

public class Product {
	int id;
	String title;
	String thumbnailUrl;
	String description;
	String regularPrice;
	String salePrice;
	String category;
	String stockStatus;
	String stockCount;
	int sellerId;
	String status;

	public Product() {
		super();
	}

	public Product(int id, String title, String thumbnailUrl, String description, String regularPrice, String salePrice,
			String category, String stockStatus, String stockCount, int sellerId, String status) {
		super();
		this.id = id;
		this.title = title;
		this.thumbnailUrl = thumbnailUrl;
		this.description = description;
		this.regularPrice = regularPrice;
		this.salePrice = salePrice;
		this.category = category;
		this.stockStatus = stockStatus;
		this.stockCount = stockCount;
		this.sellerId = sellerId;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getThumbnailUrl() {
		return thumbnailUrl;
	}

	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRegularPrice() {
		return regularPrice;
	}

	public void setRegularPrice(String regularPrice) {
		this.regularPrice = regularPrice;
	}

	public String getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(String salePrice) {
		this.salePrice = salePrice;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getStockStatus() {
		return stockStatus;
	}

	public void setStockStatus(String stockStatus) {
		this.stockStatus = stockStatus;
	}

	public String getStockCount() {
		return stockCount;
	}

	public void setStockCount(String stockCount) {
		this.stockCount = stockCount;
	}

	public int getSellerId() {
		return sellerId;
	}

	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", title=" + title + ", thumbnailUrl=" + thumbnailUrl + ", description="
				+ description + ", regularPrice=" + regularPrice + ", salePrice=" + salePrice + ", category=" + category
				+ ", stockStatus=" + stockStatus + ", stockCount=" + stockCount + ", sellerId=" + sellerId + ", status="
				+ status + "]";
	}
}
