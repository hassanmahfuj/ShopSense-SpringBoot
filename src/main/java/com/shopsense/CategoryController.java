package com.shopsense;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopsense.models.Category;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class CategoryController {

	CategoryDA da = new CategoryDA();

	@GetMapping(value = "/categories")
	public List<Category> getCategories() {
		return da.getCategories();
	}
}
