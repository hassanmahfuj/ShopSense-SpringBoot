package com.shopsense.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shopsense.dao.SellerDA;
import com.shopsense.model.Order;
import com.shopsense.model.OrderDetails;
import com.shopsense.model.Product;
import com.shopsense.model.Seller;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class SellerController {

	SellerDA da = new SellerDA();
	
	@PostMapping(value = "/seller/login")
	public Seller login(@RequestBody Seller a) {
		SellerDA d = new SellerDA();
		return d.login(a);
	}
	
	@PostMapping(value = "/seller/signup")
	public Seller signup(@RequestBody Seller a) {
		SellerDA d = new SellerDA();
		return d.signup(a);
	}
	
	@GetMapping(value = "/seller/{sellerId}")
	public Seller getSeller(@PathVariable("sellerId") int sellerId) {
		return da.getSeller(sellerId);
	}
	
	@GetMapping(value = "/seller/product/{productId}")
	public Product getProduct(@PathVariable("productId") int productId) {
		SellerDA d = new SellerDA();
		return d.getProduct(productId);
	}
	
	@GetMapping(value = "/seller/products/{sellerId}")
	public List<Product> getProducts(@PathVariable("sellerId") int sellerId) {
		SellerDA d = new SellerDA();
		return d.getProducts(sellerId);
	}
	
	@PostMapping(value = "/seller/product")
	public Product addProduct(@RequestBody Product p) {
		SellerDA d = new SellerDA();
		return d.createProduct(p);
	}
	
	@PutMapping(value = "/seller/product")
	public boolean updateProduct(@RequestBody Product p) {
		SellerDA d = new SellerDA();
		return d.updateProduct(p);
	}

	@DeleteMapping(value = "/seller/product/{id}")
	public boolean deleteProduct(@PathVariable("id") int id) {
		SellerDA d = new SellerDA();
		return d.deleteProduct(id);
	}
	
	@GetMapping(value = "/seller/orders")
	public List<Order> getOrders(@RequestParam int id) {
		return da.getOrders(id);
	}

	@GetMapping(value = "/seller/order")
	public Order getOrder(@RequestParam("orderid") int orderId, @RequestParam("sellerid") int sellerId) {
		return da.getOrder(orderId, sellerId);
	}
	
	@PutMapping(value = "/seller/order")
	public boolean updateOrder(@RequestBody OrderDetails p) {
		return da.updateOrder(p);
	}
}
