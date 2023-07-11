package com.shopsense.seller;

public class Seller {
	int id;
	String name;
	String storeName;
	String officeAddress;
	String email;
	String password;
	String role;

	public Seller() {
		super();
	}

	public Seller(int id, String name, String storeName, String officeAddress, String email, String password,
			String role) {
		super();
		this.id = id;
		this.name = name;
		this.storeName = storeName;
		this.officeAddress = officeAddress;
		this.email = email;
		this.password = password;
		this.role = role;
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
}
