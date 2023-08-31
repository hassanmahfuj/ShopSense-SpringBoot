package com.shopsense.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.shopsense.db;

@Service
public class JasperDA {
	PreparedStatement pst;
	ResultSet rs;
	
	public List<HashMap<String, String>> getAdminSalesReportGroupBySeller(String startDate, String endDate) {
		List<HashMap<String, String>> l = new ArrayList<>();
		HashMap<String, String> m;
		try {
			pst = db.get().prepareStatement(
					"SELECT store_name, COUNT(*), SUM(revenue), SUM(seller_profit), SUM(platform_profit) FROM revenue_profit JOIN sellers USING(seller_id) WHERE order_date BETWEEN ? AND ? GROUP BY seller_id");
			pst.setString(1, startDate);
			pst.setString(2, endDate);
			rs = pst.executeQuery();
			while (rs.next()) {
				m = new HashMap<>();
				m.put("storeName", rs.getString(1));
				m.put("items", rs.getString(2));
				m.put("revenue", rs.getString(3));
				m.put("sellerProfit", rs.getString(4));
				m.put("platformProfit", rs.getString(5));
				l.add(m);
			}
			rs.close();
			pst.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return l;
	}
	
	// admin product details report
	public List<HashMap<String, String>> getProductDetailsReport() {
		List<HashMap<String, String>> l = new ArrayList<>();
		HashMap<String, String> m;
		try {
			pst = db.get().prepareStatement(
					"SELECT title, regular_price, sale_price, stock_count, COUNT(product_id) FROM products JOIN order_details USING(product_id) GROUP BY product_id ORDER BY COUNT(product_id) DESC");
			rs = pst.executeQuery();
			while (rs.next()) {
				m = new HashMap<>();
				m.put("title", rs.getString(1));
				m.put("regPrice", rs.getString(2));
				m.put("salePrice", rs.getString(3));
				m.put("stock", rs.getString(4));
				m.put("sales", rs.getString(5));
				l.add(m);
			}
			rs.close();
			pst.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return l;
	}
	
	public List<HashMap<String, String>> getFavoriteItemReport() {
		List<HashMap<String, String>> l = new ArrayList<>();
		HashMap<String, String> m;
		try {
			pst = db.get().prepareStatement(
					"SELECT title, regular_price, sale_price, COUNT(product_id) FROM products JOIN wishlist USING(product_id) GROUP BY product_id ORDER BY COUNT(product_id) DESC");
			rs = pst.executeQuery();
			while (rs.next()) {
				m = new HashMap<>();
				m.put("title", rs.getString(1));
				m.put("regPrice", rs.getString(2));
				m.put("salePrice", rs.getString(3));
				m.put("favorite", rs.getString(4));
				l.add(m);
			}
			rs.close();
			pst.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return l;
	}
	
	public List<HashMap<String, String>> getCustomerReport() {
		List<HashMap<String, String>> l = new ArrayList<>();
		HashMap<String, String> m;
		try {
			pst = db.get().prepareStatement(
					"SELECT name, email, COUNT(customer_id), SUM(order_total) FROM customers JOIN orders USING(customer_id) GROUP BY customer_id");
			rs = pst.executeQuery();
			while (rs.next()) {
				m = new HashMap<>();
				m.put("name", rs.getString(1));
				m.put("email", rs.getString(2));
				m.put("order", rs.getString(3));
				m.put("amount", rs.getString(4));
				l.add(m);
			}
			rs.close();
			pst.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return l;
	}
	
	public List<HashMap<String, String>> getAdminProfitReport() {
		List<HashMap<String, String>> l = new ArrayList<>();
		HashMap<String, String> m;
		try {
			pst = db.get().prepareStatement(
					"SELECT order_date, revenue, platform_profit FROM revenue_profit");
			rs = pst.executeQuery();
			while (rs.next()) {
				m = new HashMap<>();
				m.put("date", rs.getString(1));
				m.put("sales", rs.getString(2));
				m.put("platformProfit", rs.getString(3));
				l.add(m);
			}
			rs.close();
			pst.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return l;
	}
	
	public List<HashMap<String, String>> getSellerReport() {
		List<HashMap<String, String>> l = new ArrayList<>();
		HashMap<String, String> m;
		try {
			pst = db.get().prepareStatement(
					"SELECT sellers.store_name, email, SUM(sub_total), SUM(sub_total)-balance, balance FROM sellers JOIN order_details USING(seller_id) GROUP BY seller_id");
			rs = pst.executeQuery();
			while (rs.next()) {
				m = new HashMap<>();
				m.put("storeName", rs.getString(1));
				m.put("email", rs.getString(2));
				m.put("totalSale", rs.getString(3));
				m.put("totalWithdraw", rs.getString(4));
				m.put("balance", rs.getString(5));
				l.add(m);
			}
			rs.close();
			pst.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return l;
	}
	
	public HashMap<String, Object> getCustomerOrderById(int id) {
		HashMap<String, Object> map = new HashMap<>();
		List<HashMap<String, String>> l = new ArrayList<>();
		HashMap<String, String> m;
		
		try {
			pst = db.get().prepareStatement(
					"SELECT name, email, address FROM customers WHERE customer_id = ?");
			pst.setInt(1, id);
			rs = pst.executeQuery();
			while (rs.next()) {
				map.put("name", rs.getString(1));
				map.put("email", rs.getString(2));
				map.put("address", rs.getString(3));
			}
			rs.close();
			pst.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		
		try {
			pst = db.get().prepareStatement(
					"SELECT order_id, order_date, shipping_charge+tax+gateway_fee, discount, order_total, status FROM orders WHERE customer_id = ?");
			pst.setInt(1, id);
			rs = pst.executeQuery();
			while (rs.next()) {
				m = new HashMap<>();
				m.put("id", rs.getString(1));
				m.put("date", rs.getString(2));
				m.put("cost", rs.getString(3));
				m.put("discount", rs.getString(4));
				m.put("orderTotal", rs.getString(5));
				m.put("status", rs.getString(6));
				l.add(m);
			}
			map.put("orders", l);
			rs.close();
			pst.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return map;
	}
	
	public List<HashMap<String, String>> getStockAlertReport(int id) {
		List<HashMap<String, String>> l = new ArrayList<>();
		HashMap<String, String> m;
		try {
			pst = db.get().prepareStatement(
					"SELECT product_id, products.title, categories.title, stock_count FROM products JOIN categories ON category = category_id WHERE seller_id = ? ORDER BY stock_count");
			pst.setInt(1, id);
			rs = pst.executeQuery();
			while (rs.next()) {
				m = new HashMap<>();
				m.put("sku", rs.getString(1));
				m.put("title", rs.getString(2));
				m.put("category", rs.getString(3));
				m.put("stock", rs.getString(4));
				l.add(m);
			}
			rs.close();
			pst.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return l;
	}
	
	public List<HashMap<String, String>> getTopSellingReport(int id) {
		List<HashMap<String, String>> l = new ArrayList<>();
		HashMap<String, String> m;
		try {
			pst = db.get().prepareStatement(
					"""
						SELECT products.title, categories.title, COUNT(product_id)
						FROM order_details
						JOIN products USING(product_id)
						JOIN categories ON category = category_id
						WHERE products.seller_id = ?
						GROUP BY product_id
						ORDER BY COUNT(product_id) DESC
					""");
			pst.setInt(1, id);
			rs = pst.executeQuery();
			while (rs.next()) {
				m = new HashMap<>();
				m.put("title", rs.getString(1));
				m.put("category", rs.getString(2));
				m.put("sales", rs.getString(3));
				l.add(m);
			}
			rs.close();
			pst.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return l;
	}
	
	public HashMap<String, Object> getInvoiceByOrderId(int id) {
		HashMap<String, Object> map = new HashMap<>();
		List<HashMap<String, String>> l = new ArrayList<>();
		HashMap<String, String> m;
		
		try {
			pst = db.get().prepareStatement(
					"SELECT order_id, order_date, order_total, customer_id, discount, shipping_charge, tax, shipping_street, shipping_city, shipping_post_code, shipping_state, shipping_country, status, sub_total, payment_status, payment_method, card_number, card_cvv, card_holder_name, card_expiry_date, gateway_fee FROM orders WHERE order_id = ?");
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if (rs.next()) {
				map.put("id", rs.getString(1));
				map.put("street", rs.getString("shipping_street"));
				map.put("city", rs.getString("shipping_city"));
				map.put("state", rs.getString("shipping_state"));
				map.put("subTotal", rs.getString("sub_total"));
				map.put("gatewayFee", rs.getString("gateway_fee"));
				map.put("shippingCharge", rs.getString("shipping_charge"));
				map.put("discount", rs.getString("discount"));
				map.put("tax", rs.getString("tax"));
				map.put("orderTotal", rs.getString("order_total"));
			}
			rs.close();
			pst.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		
		try {
			pst = db.get().prepareStatement(
					"SELECT order_details_id, order_id, product_id, seller_id, store_name, product_name, product_unit_price, product_thumbnail_url, status, quantity, sub_total, delivery_date FROM order_details WHERE order_id = ?");
			pst.setInt(1, id);
			rs = pst.executeQuery();
			int serial = 1;
			while (rs.next()) {
				m = new HashMap<>();
				m.put("serial", String.valueOf(serial++));
				m.put("productName", rs.getString("product_name"));
				m.put("unitPrice", rs.getString("product_unit_price"));
				m.put("qty", rs.getString("quantity"));
				m.put("subTotal", rs.getString("sub_total"));
				l.add(m);
			}
			map.put("items", l);
			rs.close();
			pst.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return map;
	}
}
