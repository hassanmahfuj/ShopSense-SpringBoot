package com.shopsense.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.shopsense.db;
import com.shopsense.dto.WithdrawalAdmin;
import com.shopsense.model.Withdrawal;

public class WithdrawalDA {
	PreparedStatement pst;

	// withdraw request for seller
	public Withdrawal requestWithdraw(Withdrawal w) {
		try {
			pst = db.get().prepareStatement(
					"INSERT INTO seller_withdrawals (seller_id, request_date, amount, status) VALUES (?, ?, ?, ?)");
			pst.setInt(1, w.getSellerId());
			pst.setDate(2, w.getRequestDate());
			pst.setDouble(3, w.getAmount());
			pst.setString(4, "Pending");
			int x = pst.executeUpdate();
			if (x != -1) {

				// then update reduce seller account balance
				pst = db.get().prepareStatement("UPDATE sellers SET balance = balance - ? WHERE seller_id = ?");
				pst.setDouble(1, w.getAmount());
				pst.setInt(2, w.getSellerId());
				pst.executeUpdate();

				return w;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	// paid and decline a withdraw request by admin
	public boolean updateWithdraw(WithdrawalAdmin w) {
		boolean success = false;
		try {
			pst = db.get()
					.prepareStatement("UPDATE seller_withdrawals SET payment_date = ?, status = ? WHERE sw_id = ?");
			pst.setDate(1, w.getPaymentDate());
			pst.setString(2, w.getStatus());
			pst.setInt(3, w.getId());
			int x = pst.executeUpdate();
			if (x != -1) {

				// if withdraw request is declined then deposit that amount to seller account
				if (w.getStatus().equals("Declined")) {
					pst = db.get().prepareStatement("UPDATE sellers SET balance = balance + ? WHERE seller_id = ?");
					pst.setDouble(1, w.getAmount());
					pst.setInt(2, w.getSellerId());
					pst.executeUpdate();
				}

				success = true;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return success;
	}

	// get all withdrawal requests for admin
	public List<WithdrawalAdmin> getAllWithdrawals() {
		List<WithdrawalAdmin> withdrawals = new ArrayList<>();
		try {
			pst = db.get().prepareStatement(
					"SELECT sw_id, seller_id, store_name, holder_name, account_number, bank_name, branch_name, request_date, amount, payment_date, seller_withdrawals.status FROM seller_withdrawals JOIN sellers USING(seller_id) ORDER BY sw_id DESC");
			ResultSet rs = pst.executeQuery();
			WithdrawalAdmin w;
			while (rs.next()) {
				w = new WithdrawalAdmin();
				w.setId(rs.getInt(1));
				w.setSellerId(rs.getInt(2));
				w.setStoreName(rs.getString(3));
				w.setHolderName(rs.getString(4));
				w.setAccountNumber(rs.getString(5));
				w.setBankName(rs.getString(6));
				w.setBranchName(rs.getString(7));
				w.setRequestDate(rs.getDate(8));
				w.setAmount(rs.getDouble(9));
				w.setPaymentDate(rs.getDate(10));
				w.setStatus(rs.getString(11));
				withdrawals.add(w);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return withdrawals;
	}

	// get all withdrawals for a seller
	public List<Withdrawal> getWithdrawals(int sellerId) {
		List<Withdrawal> withdrawals = new ArrayList<>();
		try {
			pst = db.get().prepareStatement(
					"SELECT sw_id, seller_id, request_date, amount, payment_date, status FROM seller_withdrawals WHERE seller_id = ? ORDER BY sw_id DESC");
			pst.setInt(1, sellerId);
			ResultSet rs = pst.executeQuery();
			Withdrawal w;
			while (rs.next()) {
				w = new Withdrawal();
				w.setId(rs.getInt(1));
				w.setSellerId(rs.getInt(2));
				w.setRequestDate(rs.getDate(3));
				w.setAmount(rs.getDouble(4));
				w.setPaymentDate(rs.getDate(5));
				w.setStatus(rs.getString(6));
				withdrawals.add(w);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return withdrawals;
	}
}
