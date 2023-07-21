package com.shopsense.dto;

import java.sql.Date;

public class WithdrawalAdmin {

	private int id;
	private int sellerId;
	private String storeName;
	private String holderName;
	private String accountNumber;
	private String bankName;
	private String branchName;
	private Date requestDate;
	private double amount;
	private Date paymentDate;
	private String status;

	public WithdrawalAdmin() {
		super();
	}

	public WithdrawalAdmin(int id, int sellerId, String storeName, String holderName, String accountNumber,
			String bankName, String branchName, Date requestDate, double amount, Date paymentDate, String status) {
		super();
		this.id = id;
		this.sellerId = sellerId;
		this.storeName = storeName;
		this.holderName = holderName;
		this.accountNumber = accountNumber;
		this.bankName = bankName;
		this.branchName = branchName;
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

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getHolderName() {
		return holderName;
	}

	public void setHolderName(String holderName) {
		this.holderName = holderName;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
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

}
