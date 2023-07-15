package com.shopsense.seller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.shopsense.db;
import com.shopsense.models.Order;
import com.shopsense.models.OrderDetails;
import com.shopsense.models.Product;

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

	public Seller signup(Seller a) {
		try {
			pst = db.get().prepareStatement(
					"INSERT INTO sellers (name, store_name, office_address, email, password, role) VALUES (?, ?, ?, ?, ?, ?)");
			pst.setString(1, a.getName());
			pst.setString(2, a.getStoreName());
			pst.setString(3, a.getOfficeAddress());
			pst.setString(4, a.getEmail());
			pst.setString(5, a.getPassword());
			pst.setString(6, a.getRole());
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

	public List<Product> getProducts(int sellerId) {
		List<Product> list = new ArrayList<>();
		try {
			pst = db.get().prepareStatement(
					"SELECT products.title, thumbnail_url, products.description, regular_price, sale_price, categories.title, stock_status, stock_count, status, product_id FROM products JOIN categories ON category = category_id WHERE seller_id = ?");
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

	// get a seller all orders by seller id
	public List<Order> getOrders(int id) {
		try {
			pst = db.get().prepareStatement(
					"SELECT DISTINCT order_id, order_date, order_total, customer_id, discount, shipping_charge, tax, shipping_street, shipping_city, shipping_post_code, shipping_state, shipping_country, orders.status, orders.sub_total, payment_status, payment_method, card_number, card_cvv, card_holder_name, card_expiry_date FROM orders JOIN order_details USING(order_id) WHERE seller_id = ? ORDER BY order_id DESC");
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			List<Order> o = new ArrayList<>();
			Order a;
			while (rs.next()) {
				a = new Order();
				a.setId(rs.getInt(1));
				a.setOrderDate(rs.getDate(2));
				a.setOrderTotal(rs.getDouble(3));
				a.setCustomerId(rs.getInt(4));
				a.setDiscount(rs.getDouble(5));
				a.setShippingCharge(rs.getDouble(6));
				a.setTax(rs.getDouble(7));
				a.setShippingStreet(rs.getString(8));
				a.setShippingCity(rs.getString(9));
				a.setShippingPostCode(rs.getString(10));
				a.setShippingState(rs.getString(11));
				a.setShippingCountry(rs.getString(12));
				a.setStatus(rs.getString(13));
				a.setSubTotal(rs.getDouble(14));
				a.setPaymentStatus(rs.getString(15));
				a.setPaymentMethod(rs.getString(16));
				a.setCardNumber(rs.getString(17));
				a.setCardCvv(rs.getString(18));
				a.setCardHolderName(rs.getString(19));
				a.setCardExpiryDate(rs.getString(20));
				o.add(a);
			}
			return o;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	// get a order by order id and seller id have in order details
	public Order getOrder(int orderId, int sellerId) {
		try {
			pst = db.get().prepareStatement(
					"SELECT order_id, order_date, order_total, customer_id, discount, shipping_charge, tax, shipping_street, shipping_city, shipping_post_code, shipping_state, shipping_country, status, sub_total, payment_status, payment_method, card_number, card_cvv, card_holder_name, card_expiry_date FROM orders WHERE order_id = ?");
			pst.setInt(1, orderId);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				Order a = new Order();
				a.setId(rs.getInt(1));
				a.setOrderDate(rs.getDate(2));
				a.setOrderTotal(rs.getDouble(3));
				a.setCustomerId(rs.getInt(4));
				a.setDiscount(rs.getDouble(5));
				a.setShippingCharge(rs.getDouble(6));
				a.setTax(rs.getDouble(7));
				a.setShippingStreet(rs.getString(8));
				a.setShippingCity(rs.getString(9));
				a.setShippingPostCode(rs.getString(10));
				a.setShippingState(rs.getString(11));
				a.setShippingCountry(rs.getString(12));
				a.setStatus(rs.getString(13));
				a.setSubTotal(rs.getDouble(14));
				a.setPaymentStatus(rs.getString(15));
				a.setPaymentMethod(rs.getString(16));
				a.setCardNumber(rs.getString(17));
				a.setCardCvv(rs.getString(18));
				a.setCardHolderName(rs.getString(19));
				a.setCardExpiryDate(rs.getString(20));

				PreparedStatement pst2 = db.get().prepareStatement(
						"SELECT order_details_id, order_id, product_id, seller_id, store_name, product_name, product_unit_price, product_thumbnail_url, status, quantity, sub_total, delivery_date FROM order_details WHERE order_id = ? AND seller_id = ?");
				pst2.setInt(1, orderId);
				pst2.setInt(2, sellerId);
				ResultSet rs2 = pst2.executeQuery();
				List<OrderDetails> orderDetails = new ArrayList<>();
				OrderDetails o;
				while (rs2.next()) {
					o = new OrderDetails();
					o.setId(rs2.getInt(1));
					o.setOrderId(rs2.getInt(2));
					o.setProductId(rs2.getInt(3));
					o.setSellerId(rs2.getInt(4));
					o.setStoreName(rs2.getString(5));
					o.setProductName(rs2.getString(6));
					o.setProductUnitPrice(rs2.getDouble(7));
					o.setProductThumbnailUrl(rs2.getString(8));
					o.setStatus(rs2.getString(9));
					o.setQuantity(rs2.getInt(10));
					o.setSubTotal(rs2.getDouble(11));
					o.setDeliveryDate(rs2.getDate(12));
					orderDetails.add(o);
				}
				a.setOrderDetails(orderDetails);
				return a;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	public boolean updateOrder(OrderDetails o) {
		boolean success = false;
		try {
			pst = db.get().prepareStatement("UPDATE order_details SET status = ? WHERE order_details_id = ?");
			pst.setString(1, o.getStatus());
			pst.setInt(2, o.getId());
			int r = pst.executeUpdate();
			if (r != -1) {
				success = true;
			}

			// checking any order-details table order is processing or not
			pst = db.get().prepareStatement("SELECT status FROM order_details WHERE order_id = ?");
			pst.setInt(1, o.getOrderId());
			ResultSet rs = pst.executeQuery();
			String status = "Completed";
			while (rs.next()) {
				String s = rs.getString(1);
				if (s.equals("Pending") || s.equals("Processing") || s.equals("Shipped")) {
					status = "Processing";
				}
			}
			// update main order table status
			pst = db.get().prepareStatement("UPDATE orders SET status = ? WHERE order_id = ?");
			pst.setString(1, status);
			pst.setInt(2, o.getOrderId());
			pst.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
		return success;
	}
}
