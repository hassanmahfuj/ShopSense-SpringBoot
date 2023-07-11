package com.shopsense.admin;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.shopsense.db;

public class AdminDA {
	PreparedStatement pst;

	public Admin login(Admin a) {
		Admin admin = null;
		try {
			pst = db.get().prepareStatement(
					"SELECT admin_id, name, email, role  FROM admins WHERE email = ? AND password = ?");
			pst.setString(1, a.getEmail());
			pst.setString(2, a.getPassword());
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				admin = new Admin();
				admin.setId(rs.getInt(1));
				admin.setName(rs.getString(2));
				admin.setEmail(rs.getString(3));
				admin.setRole(rs.getString(4));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return admin;
	}
}
