package com.shopsense.customer;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class CustomerController {

	@PostMapping(value = "/customer/login")
	public Customer login(@RequestBody Customer a) {
		CustomerDA d = new CustomerDA();
		return d.login(a);
	}
}
