package com.shopsense.report;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ReportController {
	
	ReportDA da = new ReportDA();

	@GetMapping(value = "/admin/stat")
	public AdminStat getAdminStat() {
		return da.getAdminStat();
	}
	
	@GetMapping(value = "/seller/stat")
	public SellerStat getSellerStat(@RequestParam(name="sellerId") int sellerId) {
		return da.getSellerStat(sellerId);
	}
}
