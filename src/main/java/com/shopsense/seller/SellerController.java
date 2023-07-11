package com.shopsense.seller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class SellerController {

	@PostMapping(value = "/seller/login")
	public Seller login(@RequestBody Seller a) {
		SellerDA d = new SellerDA();
		return d.login(a);
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
}
