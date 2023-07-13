package com.shopsense.customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.shopsense.db;
import com.shopsense.models.Product;

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

	public Customer signup(Customer a) {
		try {
			pst = db.get().prepareStatement(
					"INSERT INTO customers (name, email, password, role, address) VALUES (?, ?, ?, ?, ?)");
			pst.setString(1, a.getName());
			pst.setString(2, a.getEmail());
			pst.setString(3, a.getPassword());
			pst.setString(4, a.getRole());
			pst.setString(5, a.getAddress());
			int x = pst.executeUpdate();
			if (x != -1) {
				return a;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
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

	public List<Product> getProducts() {
		List<Product> list = new ArrayList<>();
		try {
			pst = db.get().prepareStatement(
					"SELECT title, thumbnail_url, description, regular_price, sale_price, category, stock_status, stock_count, status, product_id FROM products");
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

	public CartItem addToCart(CartItem a) {
		try {
			pst = db.get().prepareStatement(
					"INSERT INTO carts (customer_id, product_id, product_name, product_thumbnail_url, product_unit_price, quantity, sub_total) VALUES (?, ?, ?, ?, ?, ?, ?)");
			pst.setInt(1, a.getCustomerId());
			pst.setInt(2, a.getProductId());
			pst.setString(3, a.getProductName());
			pst.setString(4, a.getProductThumbnailUrl());
			pst.setDouble(5, a.getProductUnitPrice());
			pst.setInt(5, a.getProductQuantity());
			pst.setDouble(6, a.getSubTotal());
			int x = pst.executeUpdate();
			if (x != -1) {
				return a;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	public boolean updateCart(CartItem a) {
		try {
			pst = db.get().prepareStatement("UPDATE carts SET quantity = ?, sub_total = ? WHERE cart_id = ?");
			pst.setInt(1, a.getProductQuantity());
			pst.setDouble(2, a.getSubTotal());
			pst.setInt(3, a.getId());
			int x = pst.executeUpdate();
			if (x != -1) {
				return true;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	public boolean removeFromCart(int id) {
		try {
			pst = db.get().prepareStatement("DELETE FROM carts WHERE cart_id = ?");
			pst.setInt(1, id);
			int x = pst.executeUpdate();
			if (x != -1) {
				return true;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	public List<CartItem> getCartItems(int customerId) {
		List<CartItem> list = new ArrayList<>();
		try {
			pst = db.get().prepareStatement(
					"SELECT cart_id, customer_id, product_id, product_name, product_thumbnail_url, product_unit_price, quantity, sub_total FROM carts WHERE customer_id = ?");
			pst.setInt(1, customerId);
			ResultSet rs = pst.executeQuery();
			CartItem p;
			while (rs.next()) {
				p = new CartItem();
				p.setId(rs.getInt(1));
				p.setCustomerId(rs.getInt(2));
				p.setProductId(rs.getInt(3));
				p.setProductName(rs.getString(4));
				p.setProductThumbnailUrl(rs.getString(5));
				p.setProductUnitPrice(rs.getDouble(6));
				p.setProductQuantity(rs.getInt(7));
				p.setSubTotal(rs.getDouble(8));
				list.add(p);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return list;
	}
}
