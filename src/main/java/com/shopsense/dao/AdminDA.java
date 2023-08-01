package com.shopsense.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.shopsense.db;
import com.shopsense.model.Admin;
import com.shopsense.model.Customer;
import com.shopsense.model.Order;
import com.shopsense.model.OrderDetails;
import com.shopsense.model.Product;
import com.shopsense.model.RevenueProfit;
import com.shopsense.model.Seller;

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
			pst = db.get().prepareStatement("UPDATE products SET status = ? WHERE product_id = ?");
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
			pst = db.get().prepareStatement("SELECT seller_id, name, email, store_name, balance, status FROM sellers");
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
			pst = db.get().prepareStatement("UPDATE sellers SET status = ? WHERE seller_id = ?");
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

	public List<Customer> getAllCustomers() {
		List<Customer> list = new ArrayList<>();
		try {
			pst = db.get().prepareStatement("SELECT customer_id, name, email, status FROM customers");
			ResultSet rs = pst.executeQuery();
			Customer a;
			while (rs.next()) {
				a = new Customer();
				a.setId(rs.getInt("customer_id"));
				a.setName(rs.getString("name"));
				a.setEmail(rs.getString("email"));
				a.setStatus(rs.getString("status"));
				list.add(a);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return list;
	}

	public Customer updateCustomer(Customer a) {
		try {
			pst = db.get().prepareStatement("UPDATE customers SET status = ? WHERE customer_id = ?");
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

	public List<Order> getOrders() {
		try {
			pst = db.get().prepareStatement(
					"SELECT order_id, order_date, order_total, customer_id, discount, shipping_charge, tax, shipping_street, shipping_city, shipping_post_code, shipping_state, shipping_country, orders.status, orders.sub_total, payment_status, payment_method, card_number, card_cvv, card_holder_name, card_expiry_date, gateway_fee FROM orders JOIN order_details USING(order_id) ORDER BY order_id DESC");
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
				a.setGatewayFee(rs.getDouble(21));
				o.add(a);
			}
			return o;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	public Order getOrder(int orderId) {
		try {
			pst = db.get().prepareStatement(
					"SELECT order_id, order_date, order_total, customer_id, discount, shipping_charge, tax, shipping_street, shipping_city, shipping_post_code, shipping_state, shipping_country, status, sub_total, payment_status, payment_method, card_number, card_cvv, card_holder_name, card_expiry_date, gateway_fee FROM orders WHERE order_id = ?");
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
				a.setGatewayFee(rs.getDouble(21));

				PreparedStatement pst2 = db.get().prepareStatement(
						"SELECT order_details_id, order_id, product_id, seller_id, store_name, product_name, product_unit_price, product_thumbnail_url, status, quantity, sub_total, delivery_date FROM order_details WHERE order_id = ?");
				pst2.setInt(1, orderId);
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

			// if the orderDetails is delivered then update revenueProfit
			if (o.getStatus().equals("Delivered")) {
				RevenueProfit rp = new RevenueProfit();
				rp.setSellerId(o.getSellerId());
				rp.setOrderId(o.getOrderId());
				rp.setDeliveryDate(o.getDeliveryDate());
				rp.setOrderDetailsId(o.getId());
				rp.setRevenue(o.getSubTotal());
				rp.setCosts(0);
				rp.setPlatformProfit(rp.getRevenue() * .02); // 2% platform commission
				rp.setSellerProfit(rp.getRevenue() - (rp.getCosts() + rp.getPlatformProfit()));

				pst = db.get().prepareStatement(
						"INSERT INTO revenue_profit (seller_id, order_id, order_date, order_details_id, revenue, costs, platform_profit, seller_profit) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
				pst.setInt(1, rp.getSellerId());
				pst.setInt(2, rp.getOrderId());
				pst.setDate(3, rp.getDeliveryDate());
				pst.setInt(4, rp.getOrderDetailsId());
				pst.setDouble(5, rp.getRevenue());
				pst.setDouble(6, rp.getCosts());
				pst.setDouble(7, rp.getPlatformProfit());
				pst.setDouble(8, rp.getSellerProfit());
				pst.executeUpdate();

				// then update seller account balance
				pst = db.get().prepareStatement("UPDATE sellers SET balance = balance + ? WHERE seller_id = ?");
				pst.setDouble(1, rp.getSellerProfit());
				pst.setInt(2, rp.getSellerId());
				pst.executeUpdate();
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		return success;
	}

	public List<Order> getShippedOrders() {
		try {
			pst = db.get().prepareStatement(
					"SELECT order_id, order_date, order_total, customer_id, discount, shipping_charge, tax, shipping_street, shipping_city, shipping_post_code, shipping_state, shipping_country, orders.status, orders.sub_total, payment_status, payment_method, card_number, card_cvv, card_holder_name, card_expiry_date, gateway_fee FROM orders JOIN order_details USING(order_id) ORDER BY order_id DESC");
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
				a.setGatewayFee(rs.getDouble(21));

				// checking any of order details is shipped
				boolean isShipped = false;
				PreparedStatement pst2 = db.get()
						.prepareStatement("SELECT status FROM order_details WHERE order_id = ?");
				pst2.setInt(1, a.getId());
				ResultSet rs2 = pst2.executeQuery();
				while (rs2.next()) {
					if (rs2.getString(1).equals("Shipped")) {
						isShipped = true;
					}
				}

				// if isShipped is true that mean one of order details is shipped
				if (isShipped) {
					o.add(a);
				}
			}
			return o;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
}
