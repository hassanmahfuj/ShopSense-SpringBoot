package com.shopsense.admin;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class AdminController {

	@PostMapping(value = "/admin/login")
	public Admin login(@RequestBody Admin a) {
		AdminDA d = new AdminDA();
		return d.login(a);
	}
}
