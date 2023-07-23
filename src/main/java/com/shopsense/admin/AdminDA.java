package com.shopsense.admin;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.shopsense.db;
import com.shopsense.models.Product;
import com.shopsense.seller.Seller;

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
	
	public List<Product> getAllProducts() {
		List<Product> list = new ArrayList<>();
		try {
			pst = db.get().prepareStatement(
					"SELECT product_id, title, thumbnail_url, description, regular_price, sale_price, category, stock_status, stock_count, products.status, store_name FROM products JOIN sellers USING(seller_id)");
			ResultSet rs = pst.executeQuery();
			Product p;
			while (rs.next()) {
				p = new Product();
				p.setId(rs.getInt(1));
				p.setTitle(rs.getString(2));
				p.setThumbnailUrl(rs.getString(3));
				p.setDescription(rs.getString(4));
				p.setRegularPrice(rs.getString(5));
				p.setSalePrice(rs.getString(6));
				p.setCategory(rs.getString(7));
				p.setStockStatus(rs.getString(8));
				p.setStockCount(rs.getString(9));
				p.setStatus(rs.getString(10));
				p.setStoreName(rs.getString(11));
				list.add(p);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return list;
	}
	
	public Product updateProduct(Product a) {
		try {
			pst = db.get().prepareStatement(
					"UPDATE products SET status = ? WHERE product_id = ?");
			pst.setString(1, a.getStatus());
			pst.setInt(2, a.getId());
			int x = pst.executeUpdate();
			if (x != -1) {
				return a;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
	public List<Seller> getAllSellers() {
		List<Seller> list = new ArrayList<>();
		try {
			pst = db.get().prepareStatement(
					"SELECT seller_id, name, email, store_name, balance, status FROM sellers");
			ResultSet rs = pst.executeQuery();
			Seller a;
			while (rs.next()) {
				a = new Seller();
				a.setId(rs.getInt("seller_id"));
				a.setName(rs.getString("name"));
				a.setEmail(rs.getString("email"));
				a.setStoreName(rs.getString("store_name"));
				a.setBalance(rs.getDouble("balance"));
				a.setStatus(rs.getString("status"));
				list.add(a);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return list;
	}
	
	public Seller updateSeller(Seller a) {
		try {
			pst = db.get().prepareStatement(
					"UPDATE sellers SET status = ? WHERE seller_id = ?");
			pst.setString(1, a.getStatus());
			pst.setInt(2, a.getId());
			int x = pst.executeUpdate();
			if (x != -1) {
				return a;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
	
}
