package com.shopsense.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shopsense.dao.WithdrawalDA;
import com.shopsense.dto.WithdrawalAdmin;
import com.shopsense.model.Withdrawal;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class WithdrawalController {

	WithdrawalDA da = new WithdrawalDA();

	@PostMapping(value = "/seller/withdraw")
	public Withdrawal requestWithdraw(@RequestBody Withdrawal a) {
		return da.requestWithdraw(a);
	}

	@GetMapping(value = "/seller/withdrawals/{sellerId}")
	public List<Withdrawal> getWithdrawals(@PathVariable int sellerId) {
		return da.getWithdrawals(sellerId);
	}

	@PostMapping(value = "/admin/withdraw")
	public boolean updateWithdraw(@RequestBody WithdrawalAdmin a) {
		return da.updateWithdraw(a);
	}

	@GetMapping(value = "/admin/withdrawals")
	public List<WithdrawalAdmin> getAllWithdrawals() {
		return da.getAllWithdrawals();
	}
}
