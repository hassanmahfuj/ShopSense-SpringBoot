package com.shopsense.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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
	private String status;
}
