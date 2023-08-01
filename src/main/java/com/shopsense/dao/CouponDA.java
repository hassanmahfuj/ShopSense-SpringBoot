package com.shopsense.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.shopsense.db;
import com.shopsense.model.Coupon;

public class CouponDA {
	PreparedStatement pst;

	public Coupon checkCoupon(String code) {
		Coupon c = null;
		try {
			pst = db.get().prepareStatement(
					"SELECT coupon_id, coupon_code, coupon_value, coupon_type FROM coupons WHERE coupon_code = ? AND status = 'Active'");
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

	public List<Coupon> getAllCoupons() {
		List<Coupon> list = new ArrayList<>();
		try {
			pst = db.get()
					.prepareStatement("SELECT coupon_id, coupon_code, coupon_value, coupon_type, status FROM coupons");
			ResultSet rs = pst.executeQuery();
			Coupon c;
			while (rs.next()) {
				c = new Coupon();
				c.setId(rs.getInt(1));
				c.setCouponCode(rs.getString(2));
				c.setCouponValue(rs.getInt(3));
				c.setCouponType(rs.getString(4));
				c.setStatus(rs.getString(5));
				list.add(c);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return list;
	}

	public Coupon createCoupon(Coupon c) {
		try {
			pst = db.get()
					.prepareStatement("INSERT INTO coupons (coupon_code, coupon_value, coupon_type, status) VALUES (?, ?, ?, ?)");
			pst.setString(1, c.getCouponCode());
			pst.setDouble(2, c.getCouponValue());
			pst.setString(3, c.getCouponType());
			pst.setString(4, c.getStatus());
			int x = pst.executeUpdate();
			if (x != -1) {
				return c;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	public boolean updateCoupon(Coupon c) {
		try {
			pst = db.get().prepareStatement(
					"UPDATE coupons SET coupon_code = ?, coupon_value = ?, coupon_type = ?, status = ? WHERE coupon_id = ?");
			pst.setString(1, c.getCouponCode());
			pst.setDouble(2, c.getCouponValue());
			pst.setString(3, c.getCouponType());
			pst.setString(4, c.getStatus());
			pst.setInt(5, c.getId());
			int x = pst.executeUpdate();
			if (x != -1) {
				return true;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	public boolean deleteCoupon(int couponId) {
		try {
			pst = db.get().prepareStatement("DELETE FROM coupons WHERE coupon_id = ?");
			pst.setInt(1, couponId);
			int x = pst.executeUpdate();
			if (x != -1) {
				return true;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}
}
