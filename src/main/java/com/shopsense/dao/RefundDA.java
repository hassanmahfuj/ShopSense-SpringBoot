package com.shopsense.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopsense.db;
import com.shopsense.model.OrderDetails;
import com.shopsense.model.RefundDetails;

@Service
public class RefundDA {
	PreparedStatement pst;
	ResultSet rs;

	@Autowired
	AdminDA adminDA;

	public RefundDetails createRefund(RefundDetails a) {
		try {
			pst = db.get().prepareStatement(
					"INSERT INTO refund_history (order_id, order_details_id, seller_id, reason, bank_number, bank_name, amount) VALUES (?, ?, ?, ?, ?, ?, ?)");
			pst.setInt(1, a.getOrderId());
			pst.setInt(2, a.getOrderDetailsId());
			pst.setInt(3, a.getSellerId());
			pst.setString(4, a.getReason());
			pst.setString(5, a.getBankNumber());
			pst.setString(6, a.getBankName());
			pst.setDouble(7, a.getAmount());
			int x = pst.executeUpdate();
			if (x != -1) {
				// updating order status
				OrderDetails o = new OrderDetails();
				o.setOrderId(a.getOrderId());
				o.setId(a.getOrderDetailsId());
				o.setStatus("Refund Requested");
				adminDA.updateOrder(o);
				return a;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	public RefundDetails updateRefund(RefundDetails a) {
		try {
			OrderDetails o = new OrderDetails();
			o.setOrderId(a.getOrderId());
			o.setId(a.getOrderDetailsId());
			o.setStatus(a.getStatus());
			adminDA.updateOrder(o);
			// if order refunded then update seller account balance
			if (a.getStatus().equals("Refunded")) {
				pst = db.get().prepareStatement("UPDATE sellers SET balance = balance - ? WHERE seller_id = ?");
				pst.setDouble(1, a.getAmount());
				pst.setInt(2, a.getSellerId());
				pst.executeUpdate();
			}
			return a;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	public List<RefundDetails> getAllRefund() {
		List<RefundDetails> l = new ArrayList<>();
		try {
			pst = db.get().prepareStatement(
					"SELECT refund_id, r.order_id, r.order_details_id, r.seller_id, reason, bank_number, bank_name, amount, status FROM refund_history r JOIN order_details USING(order_details_id) ORDER BY refund_id DESC");
			rs = pst.executeQuery();
			RefundDetails rd;
			while (rs.next()) {
				rd = new RefundDetails();
				rd.setRefundId(rs.getInt(1));
				rd.setOrderId(rs.getInt(2));
				rd.setOrderDetailsId(rs.getInt(3));
				rd.setSellerId(rs.getInt(4));
				rd.setReason(rs.getString(5));
				rd.setBankNumber(rs.getString(6));
				rd.setBankName(rs.getString(7));
				rd.setAmount(rs.getDouble(8));
				rd.setStatus(rs.getString(9));
				l.add(rd);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return l;
	}
	
	public List<RefundDetails> getSellerRefund(int sellerId) {
		List<RefundDetails> l = new ArrayList<>();
		try {
			pst = db.get().prepareStatement(
					"SELECT refund_id, r.order_id, r.order_details_id, r.seller_id, reason, bank_number, bank_name, amount, status FROM refund_history r JOIN order_details USING(order_details_id) WHERE r.seller_id = ? ORDER BY refund_id DESC");
			pst.setInt(1, sellerId);
			rs = pst.executeQuery();
			RefundDetails rd;
			while (rs.next()) {
				rd = new RefundDetails();
				rd.setRefundId(rs.getInt(1));
				rd.setOrderId(rs.getInt(2));
				rd.setOrderDetailsId(rs.getInt(3));
				rd.setSellerId(rs.getInt(4));
				rd.setReason(rs.getString(5));
				rd.setBankNumber(rs.getString(6));
				rd.setBankName(rs.getString(7));
				rd.setAmount(rs.getDouble(8));
				rd.setStatus(rs.getString(9));
				l.add(rd);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return l;
	}
}
