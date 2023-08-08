package com.shopsense.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopsense.db;
import com.shopsense.model.CartItem;
import com.shopsense.model.Customer;
import com.shopsense.model.Order;
import com.shopsense.model.OrderDetails;
import com.shopsense.model.Product;
import com.shopsense.service.EmailService;

@Service
public class CustomerDA {
	PreparedStatement pst;

	@Autowired
	EmailService mailer;

	public Customer login(Customer a) {
		Customer customer = null;
		try {
			pst = db.get().prepareStatement(
					"SELECT customer_id, name, email, role, address FROM customers WHERE email = ? AND password = ? AND status = 'Active'");
			pst.setString(1, a.getEmail());
			pst.setString(2, a.getPassword());
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				customer = new Customer();
				customer.setId(rs.getInt(1));
				customer.setName(rs.getString(2));
				customer.setEmail(rs.getString(3));
				customer.setRole(rs.getString(4));
				customer.setAddress(rs.getString(5));
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
					"SELECT product_id, title, thumbnail_url, description, regular_price, sale_price, category, stock_status, stock_count, seller_id, store_name, products.status FROM products JOIN sellers USING(seller_id) WHERE product_id = ?");
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
					"SELECT product_id, title, thumbnail_url, description, regular_price, sale_price, category, stock_status, stock_count, products.status "
							+ "FROM products JOIN sellers USING(seller_id)"
							+ "WHERE products.status = 'Active' AND sellers.status = 'Active'");
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
					"INSERT INTO orders (order_date, order_total, customer_id, discount, shipping_charge, tax, shipping_street, shipping_city, shipping_post_code, shipping_state, shipping_country, status, sub_total, payment_status, payment_method, card_number, card_cvv, card_holder_name, card_expiry_date, gateway_fee) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			pst.setDate(1, a.getOrderDate());
//			pst.setDate(1, new Date(System.currentTimeMillis()));
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
			pst.setDouble(20, a.getGatewayFee());
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

				PreparedStatement pst3 = db.get().prepareStatement("DELETE FROM carts WHERE customer_id = ?");
				pst3.setInt(1, a.getCustomerId());
				pst3.executeUpdate();

				// send email
				StringBuilder orderDetailString = new StringBuilder();
				for (OrderDetails o : orderDetails) {
					orderDetailString.append("<tr>");
					orderDetailString.append("<td>" + o.getProductName() + "</td>");
					orderDetailString.append("<td>" + o.getQuantity() + "</td>");
					orderDetailString.append("<td>" + o.getSubTotal() + "</td>");
					orderDetailString.append("</tr>");
				}
				
				String customerEmail = String.format("""
						<html>
						<head>
						<style>
							.header {
								width: 400px;
								background-color: #04AA6D;
								color: white;
								padding: 10px 20px;
							}
							table {
							  border-collapse: collapse;
							  width: 400px;
							}

							td, th {
							  border: 1px solid #ddd;
							  padding: 8px;
							}

							th {
							  padding-top: 12px;
							  padding-bottom: 12px;
							  text-align: left;
							  background-color: #04AA6D;
							  color: white;
							}
						</style>
						</head>
						<body>
							<h1 class="header">Order Placed</h1>
							<p>Your order is placed successfully. The order is as follows:</p>
							<table>
								<tr>
									<th>Product</th>
									<th>Quantity</th>
									<th>Price</th>
								</tr>
								%s
							</table>
						</body>
						</html>
						""", orderDetailString.toString());
				
				String sellerEmail = String.format("""
						<html>
						<head>
						<style>
							.header {
								width: 400px;
								background-color: #04AA6D;
								color: white;
								padding: 10px 20px;
							}
							table {
							  border-collapse: collapse;
							  width: 400px;
							}

							td, th {
							  border: 1px solid #ddd;
							  padding: 8px;
							}

							th {
							  padding-top: 12px;
							  padding-bottom: 12px;
							  text-align: left;
							  background-color: #04AA6D;
							  color: white;
							}
						</style>
						</head>
						<body>
							<h1 class="header">New Order</h1>
							<p>You got a new order. The order is as follows:</p>
							<table>
								<tr>
									<th>Product</th>
									<th>Quantity</th>
									<th>Price</th>
								</tr>
								%s
							</table>
						</body>
						</html>
						""", orderDetailString.toString());
				
				mailer.sendContentEmail("humahfuj@gmail.com", "Order Placed", customerEmail);
				mailer.sendContentEmail("humahfuj@gmail.com", "New Order", sellerEmail);
				return a;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	public Order getOrder(int id) {
		try {
			pst = db.get().prepareStatement(
					"SELECT order_id, order_date, order_total, customer_id, discount, shipping_charge, tax, shipping_street, shipping_city, shipping_post_code, shipping_state, shipping_country, status, sub_total, payment_status, payment_method, card_number, card_cvv, card_holder_name, card_expiry_date, gateway_fee FROM orders WHERE order_id = ?");
			pst.setInt(1, id);
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
				pst2.setInt(1, id);
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

	// get a customers all orders by customer id
	public List<Order> getOrders(int id) {
		try {
			pst = db.get().prepareStatement(
					"SELECT order_id, order_date, order_total, customer_id, discount, shipping_charge, tax, shipping_street, shipping_city, shipping_post_code, shipping_state, shipping_country, status, sub_total, payment_status, payment_method, card_number, card_cvv, card_holder_name, card_expiry_date, gateway_fee FROM orders WHERE customer_id = ? ORDER BY order_id DESC");
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
				a.setGatewayFee(rs.getDouble(21));
				o.add(a);
			}
			return o;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	public OrderDetails trackOrder(int id) {
		try {
			pst = db.get().prepareStatement(
					"SELECT order_details_id, order_id, product_id, seller_id, store_name, product_name, product_unit_price, product_thumbnail_url, status, quantity, sub_total, delivery_date FROM order_details WHERE order_details_id = ?");
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				OrderDetails a = new OrderDetails();
				a.setId(rs.getInt(1));
				a.setOrderId(rs.getInt(2));
				a.setProductId(rs.getInt(3));
				a.setSellerId(rs.getInt(4));
				a.setStoreName(rs.getString(5));
				a.setProductName(rs.getString(6));
				a.setProductUnitPrice(rs.getDouble(7));
				a.setProductThumbnailUrl(rs.getString(8));
				a.setStatus(rs.getString(9));
				a.setQuantity(rs.getInt(10));
				a.setSubTotal(rs.getDouble(11));
				a.setDeliveryDate(rs.getDate(12));
				return a;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	public boolean isProductPurchased(int customerId, int productId) {
		try {
			pst = db.get().prepareStatement("""
						SELECT COUNT(*)
						FROM order_details
						JOIN orders USING(order_id)
						WHERE product_id = ? AND customer_id = ?
					""");
			pst.setInt(1, productId);
			pst.setInt(2, customerId);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				if (rs.getInt(1) > 0) {
					return true;
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	public List<Product> searchProducts(String q) {
		List<Product> list = new ArrayList<>();
		try {
			pst = db.get().prepareStatement(
					"SELECT product_id, title, thumbnail_url, description, regular_price, sale_price, category, stock_status, stock_count, products.status "
							+ "FROM products JOIN sellers USING(seller_id)"
							+ "WHERE products.status = 'Active' AND sellers.status = 'Active' AND title LIKE ?");
			pst.setString(1, "%".concat(q).concat("%"));
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
}
