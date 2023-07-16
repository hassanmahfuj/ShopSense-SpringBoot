package com.shopsense;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.shopsense.models.Coupon;

public class CouponDA {
	PreparedStatement pst;

	public Coupon checkCoupon(String code) {
		Coupon c = null;
		try {
			pst = db.get().prepareStatement(
					"SELECT coupon_id, coupon_code, coupon_value, coupon_type FROM coupons WHERE coupon_code = ?");
			pst.setString(1, code);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				c = new Coupon();
				c.setId(rs.getInt(1));
				c.setCouponCode(rs.getString(2));
				c.setCouponValue(rs.getInt(3));
				c.setCouponType(rs.getString(4));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return c;
	}
}
