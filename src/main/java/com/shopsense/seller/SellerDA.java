package com.shopsense.seller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.shopsense.db;

public class SellerDA {
	PreparedStatement pst;

	public Seller login(Seller a) {
		Seller seller = null;
		try {
			pst = db.get().prepareStatement(
					"SELECT seller_id, name, email, role  FROM sellers WHERE email = ? AND password = ?");
			pst.setString(1, a.getEmail());
			pst.setString(2, a.getPassword());
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				seller = new Seller();
				seller.setId(rs.getInt(1));
				seller.setName(rs.getString(2));
				seller.setEmail(rs.getString(3));
				seller.setRole(rs.getString(4));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return seller;
	}

	public Product getProduct(int productId) {
		Product p = null;
		try {
			pst = db.get().prepareStatement(
					"SELECT title, thumbnail_url, description, regular_price, sale_price, category, stock_status, stock_count, status, product_id FROM products WHERE product_id = ?");
			pst.setInt(1, productId);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				p = new Product();
				p.setId(rs.getInt(10));
				p.setTitle(rs.getString(1));
				p.setThumbnailUrl(rs.getString(2));
				p.setDescription(rs.getString(3));
				p.setRegularPrice(rs.getString(4));
				p.setSalePrice(rs.getString(5));
				p.setCategory(rs.getString(6));
				p.setStockStatus(rs.getString(7));
				p.setStockCount(rs.getString(8));
				p.setStatus(rs.getString(9));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return p;
	}

	public List<Product> getProducts(int sellerId) {
		List<Product> list = new ArrayList<>();
		try {
			pst = db.get().prepareStatement(
					"SELECT title, thumbnail_url, description, regular_price, sale_price, category, stock_status, stock_count, status, product_id FROM products WHERE seller_id = ?");
			pst.setInt(1, sellerId);
			ResultSet rs = pst.executeQuery();
			Product p;
			while (rs.next()) {
				p = new Product();
				p.setId(rs.getInt(10));
				p.setTitle(rs.getString(1));
				p.setThumbnailUrl(rs.getString(2));
				p.setDescription(rs.getString(3));
				p.setRegularPrice(rs.getString(4));
				p.setSalePrice(rs.getString(5));
				p.setCategory(rs.getString(6));
				p.setStockStatus(rs.getString(7));
				p.setStockCount(rs.getString(8));
				p.setStatus(rs.getString(9));
				list.add(p);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return list;
	}

	public Product createProduct(Product a) {
		try {
			pst = db.get().prepareStatement(
					"INSERT INTO products (title, thumbnail_url, description, regular_price, sale_price, category, stock_status, stock_count, seller_id, status)"
							+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			pst.setString(1, a.getTitle());
			pst.setString(2, a.getThumbnailUrl());
			pst.setString(3, a.getDescription());
			pst.setString(4, a.getRegularPrice());
			pst.setString(5, a.getSalePrice());
			pst.setString(6, a.getCategory());
			pst.setString(7, a.getStockStatus());
			pst.setString(8, a.getStockCount());
			pst.setInt(9, a.getSellerId());
			pst.setString(10, a.getStatus());
			int x = pst.executeUpdate();
			if (x != -1) {
				return a;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	public boolean updateProduct(Product p) {
		boolean success = false;
		try {
			pst = db.get().prepareStatement(
					"UPDATE products SET title = ?, thumbnail_url = ?, description = ?, regular_price = ?, sale_price = ?, category = ?, stock_status = ?, stock_count = ? WHERE product_id = ?");
			pst.setString(1, p.getTitle());
			pst.setString(2, p.getThumbnailUrl());
			pst.setString(3, p.getDescription());
			pst.setString(4, p.getRegularPrice());
			pst.setString(5, p.getSalePrice());
			pst.setString(6, p.getCategory());
			pst.setString(7, p.getStockStatus());
			pst.setString(8, p.getStockCount());
			pst.setInt(9, p.getId());
			int r = pst.executeUpdate();
			if (r != -1) {
				success = true;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return success;
	}

	public boolean deleteProduct(int id) {
		boolean success = false;
		try {
			pst = db.get().prepareStatement("DELETE FROM products WHERE product_id = ?");
			pst.setInt(1, id);
			int r = pst.executeUpdate();
			if (r != -1) {
				success = true;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return success;
	}
}
