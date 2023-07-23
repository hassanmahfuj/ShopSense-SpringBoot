package com.shopsense.admin;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shopsense.models.Product;
import com.shopsense.seller.Seller;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class AdminController {
	AdminDA da = new AdminDA();

	@PostMapping(value = "/admin/login")
	public Admin login(@RequestBody Admin a) {
		AdminDA d = new AdminDA();
		return d.login(a);
	}

	@GetMapping(value = "/admin/products")
	public List<Product> getAllProducts() {
		return da.getAllProducts();
	}

	@PutMapping(value = "/admin/product")
	public Product updateProduct(@RequestBody Product a) {
		return da.updateProduct(a);
	}

	@GetMapping(value = "/admin/sellers")
	public List<Seller> getAllSellers() {
		return da.getAllSellers();
	}

	@PutMapping(value = "/admin/seller")
	public Seller updateSeller(@RequestBody Seller a) {
		return da.updateSeller(a);
	}
}
