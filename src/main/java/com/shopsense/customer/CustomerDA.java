package com.shopsense.customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.shopsense.db;

public class CustomerDA {
	PreparedStatement pst;

	public Customer login(Customer a) {
		Customer customer = null;
		try {
			pst = db.get().prepareStatement(
					"SELECT customer_id, name, email, role  FROM customers WHERE email = ? AND password = ?");
			pst.setString(1, a.getEmail());
			pst.setString(2, a.getPassword());
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				customer = new Customer();
				customer.setId(rs.getInt(1));
				customer.setName(rs.getString(2));
				customer.setEmail(rs.getString(3));
				customer.setRole(rs.getString(4));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return customer;
	}
}
