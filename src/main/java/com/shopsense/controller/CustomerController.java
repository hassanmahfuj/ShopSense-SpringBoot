package com.shopsense.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shopsense.dao.CustomerDA;
import com.shopsense.dto.AuthRequest;
import com.shopsense.dto.AuthResponse;
import com.shopsense.model.CartItem;
import com.shopsense.model.Customer;
import com.shopsense.model.Order;
import com.shopsense.model.OrderDetails;
import com.shopsense.model.Product;
import com.shopsense.service.AuthService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class CustomerController {

	@Autowired
	CustomerDA da;
	
	@Autowired
	AuthService authService;

	@PostMapping(value = "/customer/login")
	public AuthResponse login(@RequestBody AuthRequest a) {
		return authService.customerLogin(a);
	}

	@PostMapping(value = "/customer/signup")
	public Customer signup(@RequestBody Customer a) {
		CustomerDA d = new CustomerDA();
		return d.signup(a);
	}

	@GetMapping(value = "/customer/{customerId}")
	public Customer getCustomer(@PathVariable int customerId) {
		return da.getCustomer(customerId);
	}

	@GetMapping(value = "/product/{productId}")
	public Product getProduct(@PathVariable("productId") int productId) {
		CustomerDA d = new CustomerDA();
		return d.getProduct(productId);
	}

	@GetMapping(value = "/products")
	public List<Product> getProducts() {
		CustomerDA d = new CustomerDA();
		return d.getProducts();
	}

	@PostMapping(value = "/customer/cart")
	public CartItem addToCart(@RequestBody CartItem a) {
		CustomerDA d = new CustomerDA();
		return d.addToCart(a);
	}

	@PutMapping(value = "/customer/cart")
	public boolean updateCart(@RequestBody CartItem a) {
		CustomerDA d = new CustomerDA();
		return d.updateCart(a);
	}

	@DeleteMapping(value = "/customer/cart")
	public boolean removeFromCart(@RequestParam int id) {
		return da.removeFromCart(id);
	}

	@GetMapping(value = "/customer/cart")
	public List<CartItem> getCartItems(@RequestParam int id) {
		return da.getCartItems(id);
	}

	@PostMapping(value = "/customer/order")
	public Order placeOrder(@RequestBody Order a) {
		return da.placeOrder(a);
	}

	@GetMapping(value = "/customer/orders")
	public List<Order> getOrders(@RequestParam int id) {
		return da.getOrders(id);
	}

	@GetMapping(value = "/customer/order")
	public Order getOrder(@RequestParam int id) {
		return da.getOrder(id);
	}

	@GetMapping(value = "/customer/track")
	public OrderDetails trackOrder(@RequestParam int id) {
		return da.trackOrder(id);
	}

	@GetMapping(value = "/customer/check-purchased")
	public boolean isProductPurchased(@RequestParam int customerId, @RequestParam int productId) {
		return da.isProductPurchased(customerId, productId);
	}

	@GetMapping(value = "/search")
	public List<Product> searchProducts(@RequestParam String q) {
		return da.searchProducts(q);
	}

	@PostMapping(value = "/customer/send-code")
	public boolean sendVerificationCode(@RequestBody Customer a) {
		return da.sendVerificationCode(a);
	}

	@GetMapping(value = "/customer/verify-code")
	public boolean verifyCode(@RequestParam int userId, @RequestParam int code) {
		return da.verifyCode(userId, code);
	}
}
