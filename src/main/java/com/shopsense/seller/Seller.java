package com.shopsense.seller;

public class Seller {
	private int id;
	private String name;
	private String storeName;
	private String officeAddress;
	private String email;
	private String password;
	private String role;
	private double balance;
	private String holderName;
	private String accountNumber;
	private String bankName;
	private String branchName;

	public Seller() {
		super();
	}

	public Seller(int id, String name, String storeName, String officeAddress, String email, String password,
			String role, double balance, String holderName, String accountNumber, String bankName, String branchName) {
		super();
		this.id = id;
		this.name = name;
		this.storeName = storeName;
		this.officeAddress = officeAddress;
		this.email = email;
		this.password = password;
		this.role = role;
		this.balance = balance;
		this.holderName = holderName;
		this.accountNumber = accountNumber;
		this.bankName = bankName;
		this.branchName = branchName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getOfficeAddress() {
		return officeAddress;
	}

	public void setOfficeAddress(String officeAddress) {
		this.officeAddress = officeAddress;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
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

}
