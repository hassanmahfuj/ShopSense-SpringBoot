package com.shopsense.model;

import java.sql.Date;

public class RevenueProfit {

	private int id;
	private int sellerId;
	private int orderId;
	private Date deliveryDate;
	private int orderDetailsId;
	private double revenue;
	private double costs;
	private double platformProfit;
	private double sellerProfit;

	public RevenueProfit() {
		super();
	}

	public RevenueProfit(int id, int sellerId, int orderId, Date deliveryDate, int orderDetailsId, double revenue,
			double costs, double platformProfit, double sellerProfit) {
		super();
		this.id = id;
		this.sellerId = sellerId;
		this.orderId = orderId;
		this.deliveryDate = deliveryDate;
		this.orderDetailsId = orderDetailsId;
		this.revenue = revenue;
		this.costs = costs;
		this.platformProfit = platformProfit;
		this.sellerProfit = sellerProfit;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSellerId() {
		return sellerId;
	}

	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public int getOrderDetailsId() {
		return orderDetailsId;
	}

	public void setOrderDetailsId(int orderDetailsId) {
		this.orderDetailsId = orderDetailsId;
	}

	public double getRevenue() {
		return revenue;
	}

	public void setRevenue(double revenue) {
		this.revenue = revenue;
	}

	public double getCosts() {
		return costs;
	}

	public void setCosts(double costs) {
		this.costs = costs;
	}

	public double getPlatformProfit() {
		return platformProfit;
	}

	public void setPlatformProfit(double platformProfit) {
		this.platformProfit = platformProfit;
	}

	public double getSellerProfit() {
		return sellerProfit;
	}

	public void setSellerProfit(double sellerProfit) {
		this.sellerProfit = sellerProfit;
	}

}
