package com.shopsense.customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.shopsense.db;
import com.shopsense.models.Order;
import com.shopsense.models.OrderDetails;
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
					"SELECT product_id, title, thumbnail_url, description, regular_price, sale_price, category, stock_status, stock_count, seller_id, store_name, status FROM products JOIN sellers USING(seller_id) WHERE product_id = ?");
			pst.setInt(1, productId);
			ResultSet rs = pst.executeQuery();
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
				p.setSellerId(rs.getInt(10));
				p.setStoreName(rs.getString(11));
				p.setStatus(rs.getString(12));
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
					"SELECT product_id, title, thumbnail_url, description, regular_price, sale_price, category, stock_status, stock_count, status FROM products");
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
					"INSERT INTO carts (customer_id, product_id, seller_id, store_name, product_name, product_thumbnail_url, product_unit_price, quantity, sub_total) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
			pst.setInt(1, a.getCustomerId());
			pst.setInt(2, a.getProductId());
			pst.setInt(3, a.getSellerId());
			pst.setString(4, a.getStoreName());
			pst.setString(5, a.getProductName());
			pst.setString(6, a.getProductThumbnailUrl());
			pst.setDouble(7, a.getProductUnitPrice());
			pst.setInt(8, a.getProductQuantity());
			pst.setDouble(9, a.getSubTotal());
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
					"SELECT cart_id, customer_id, product_id, seller_id, store_name, product_name, product_thumbnail_url, product_unit_price, quantity, sub_total FROM carts WHERE customer_id = ?");
			pst.setInt(1, customerId);
			ResultSet rs = pst.executeQuery();
			CartItem p;
			while (rs.next()) {
				p = new CartItem();
				p.setId(rs.getInt(1));
				p.setCustomerId(rs.getInt(2));
				p.setProductId(rs.getInt(3));
				p.setSellerId(rs.getInt(4));
				p.setStoreName(rs.getString(5));
				p.setProductName(rs.getString(6));
				p.setProductThumbnailUrl(rs.getString(7));
				p.setProductUnitPrice(rs.getDouble(8));
				p.setProductQuantity(rs.getInt(9));
				p.setSubTotal(rs.getDouble(10));
				list.add(p);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return list;
	}

	public Order placeOrder(Order a) {
		try {
			pst = db.get().prepareStatement(
					"INSERT INTO orders (order_date, order_total, customer_id, discount, shipping_charge, tax, shipping_street, shipping_city, shipping_post_code, shipping_state, shipping_country, status, sub_total, payment_status, payment_method, card_number, card_cvv, card_holder_name, card_expiry_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			pst.setDate(1, a.getOrderDate());
			pst.setDouble(2, a.getOrderTotal());
			pst.setInt(3, a.getCustomerId());
			pst.setDouble(4, a.getDiscount());
			pst.setDouble(5, a.getShippingCharge());
			pst.setDouble(6, a.getTax());
			pst.setString(7, a.getShippingStreet());
			pst.setString(8, a.getShippingCity());
			pst.setString(9, a.getShippingPostCode());
			pst.setString(10, a.getShippingState());
			pst.setString(11, a.getShippingCountry());
			pst.setString(12, a.getStatus());
			pst.setDouble(13, a.getSubTotal());
			pst.setString(14, a.getPaymentStatus());
			pst.setString(15, a.getPaymentMethod());
			pst.setString(16, a.getCardNumber());
			pst.setString(17, a.getCardCvv());
			pst.setString(18, a.getCardHolderName());
			pst.setString(19, a.getCardExpiryDate());
			int x = pst.executeUpdate();
			if (x != -1) {
				ResultSet generatedKeys = pst.getGeneratedKeys();
				int generatedId = 0;
				if (generatedKeys.next()) {
					generatedId = generatedKeys.getInt(1);
					a.setId(generatedId);
				}

				List<OrderDetails> orderDetails = a.getOrderDetails();
				PreparedStatement pst2 = db.get().prepareStatement(
						"INSERT INTO order_details (order_id, product_id, seller_id, store_name, product_name, product_unit_price, product_thumbnail_url, status, quantity, sub_total, delivery_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
				for (OrderDetails o : orderDetails) {
					pst2.setInt(1, generatedId);
					pst2.setInt(2, o.getProductId());
					pst2.setInt(3, o.getSellerId());
					pst2.setString(4, o.getStoreName());
					pst2.setString(5, o.getProductName());
					pst2.setDouble(6, o.getProductUnitPrice());
					pst2.setString(7, o.getProductThumbnailUrl());
					pst2.setString(8, o.getStatus());
					pst2.setInt(9, o.getQuantity());
					pst2.setDouble(10, o.getSubTotal());
					pst2.setDate(11, o.getDeliveryDate());

					pst2.addBatch();
				}
				pst2.executeBatch();

				return a;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
}
