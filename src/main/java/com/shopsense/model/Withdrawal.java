package com.shopsense.model;

import java.sql.Date;

public class Withdrawal {

	private int id;
	private int sellerId;
	private Date requestDate;
	private double amount;
	private Date paymentDate;
	private String status;

	public Withdrawal() {
		super();
	}

	public Withdrawal(int id, int sellerId, Date requestDate, double amount, Date paymentDate, String status) {
		super();
		this.id = id;
		this.sellerId = sellerId;
		this.requestDate = requestDate;
		this.amount = amount;
		this.paymentDate = paymentDate;
		this.status = status;
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

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Withdrawal [id=" + id + ", sellerId=" + sellerId + ", requestDate=" + requestDate + ", amount=" + amount
				+ ", paymentDate=" + paymentDate + ", status=" + status + "]";
	}

}
