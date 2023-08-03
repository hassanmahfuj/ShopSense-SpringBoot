package com.shopsense.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.shopsense.db;
import com.shopsense.dto.AdminStat;
import com.shopsense.dto.SalesReportDto;
import com.shopsense.dto.SellerStat;

public class ReportDA {

	PreparedStatement pst;
	ResultSet rs;

	public AdminStat getAdminStat() {
		AdminStat a = new AdminStat();
		List<HashMap<String, String>> tList;
		HashMap<String, String> t;

		try {
			// getting total revenue, total profit
			pst = db.get().prepareStatement("SELECT SUM(revenue), SUM(platform_profit) FROM revenue_profit");
			rs = pst.executeQuery();
			while (rs.next()) {
				a.setRevenue(rs.getDouble(1));
				a.setProfit(rs.getDouble(2));
			}
			rs.close();
			pst.close();

			// total sellers
			pst = db.get().prepareStatement("SELECT COUNT(*) FROM sellers");
			rs = pst.executeQuery();
			while (rs.next()) {
				a.setSellers(rs.getInt(1));
			}
			rs.close();
			pst.close();

			// total customer
			pst = db.get().prepareStatement("SELECT COUNT(*) FROM customers");
			rs = pst.executeQuery();
			while (rs.next()) {
				a.setCustomers(rs.getInt(1));
			}
			rs.close();
			pst.close();

			// getting weekly revenue
			pst = db.get().prepareStatement(
					"SELECT DATE_FORMAT(date_list.date, '%a') AS day_name, COALESCE(SUM(revenue), 0) AS total_revenue\r\n"
							+ "FROM (\r\n" + "    SELECT CURDATE() AS date\r\n"
							+ "    UNION SELECT DATE_SUB(CURDATE(), INTERVAL 1 DAY)\r\n"
							+ "    UNION SELECT DATE_SUB(CURDATE(), INTERVAL 2 DAY)\r\n"
							+ "    UNION SELECT DATE_SUB(CURDATE(), INTERVAL 3 DAY)\r\n"
							+ "    UNION SELECT DATE_SUB(CURDATE(), INTERVAL 4 DAY)\r\n"
							+ "    UNION SELECT DATE_SUB(CURDATE(), INTERVAL 5 DAY)\r\n"
							+ "    UNION SELECT DATE_SUB(CURDATE(), INTERVAL 6 DAY)\r\n" + ") date_list\r\n"
							+ "LEFT JOIN revenue_profit ON date_list.date = revenue_profit.order_date\r\n"
							+ "GROUP BY date_list.date\r\n" + "ORDER BY date_list.date;");
			rs = pst.executeQuery();
			tList = new ArrayList<>();
			while (rs.next()) {
				t = new HashMap<>();
				t.put("dayName", rs.getString(1));
				t.put("revenue", rs.getString(2));
				tList.add(t);
			}
			a.setWeeklyRevenue(tList);
			rs.close();
			pst.close();

			// getting best seller
			pst = db.get().prepareStatement(
					"SELECT store_name, SUM(revenue) FROM revenue_profit JOIN sellers USING(seller_id) GROUP BY store_name ORDER BY SUM(revenue) DESC");
			rs = pst.executeQuery();
			tList = new ArrayList<>();
			while (rs.next()) {
				t = new HashMap<>();
				t.put("storeName", rs.getString(1));
				t.put("revenue", rs.getString(2));
				tList.add(t);
			}
			a.setBestSeller(tList);
			rs.close();
			pst.close();

			// getting order status
			pst = db.get().prepareStatement(
					"SELECT od.status, COUNT(od.status) FROM order_details AS od JOIN orders USING(order_id) WHERE order_date > DATE_SUB(CURDATE(), INTERVAL 7 DAY) GROUP BY od.status");
			rs = pst.executeQuery();
			tList = new ArrayList<>();
			while (rs.next()) {
				t = new HashMap<>();
				t.put("status", rs.getString(1));
				t.put("count", rs.getString(2));
				tList.add(t);
			}
			a.setOrderStatus(tList);
			rs.close();
			pst.close();

		} catch (Exception e) {
			System.out.println(e);
		}
		return a;
	}

	public SellerStat getSellerStat(int sellerId) {
		SellerStat a = new SellerStat();

		try {
			// total order
			pst = db.get().prepareStatement("SELECT COUNT(*) FROM order_details WHERE seller_id = ?");
			pst.setInt(1, sellerId);
			rs = pst.executeQuery();
			while (rs.next()) {
				a.setTotalOrder(rs.getInt(1));
			}
			rs.close();
			pst.close();

			// new order
			pst = db.get()
					.prepareStatement("SELECT COUNT(*) FROM order_details WHERE seller_id = ? AND status = 'Pending'");
			pst.setInt(1, sellerId);
			rs = pst.executeQuery();
			while (rs.next()) {
				a.setNewOrder(rs.getInt(1));
			}
			rs.close();
			pst.close();

			// processing
			pst = db.get().prepareStatement(
					"SELECT COUNT(*) FROM order_details WHERE seller_id = ? AND status IN('Processing', 'Shipped')");
			pst.setInt(1, sellerId);
			rs = pst.executeQuery();
			while (rs.next()) {
				a.setProcessing(rs.getInt(1));
			}
			rs.close();
			pst.close();

			// sold items
			pst = db.get().prepareStatement(
					"SELECT COUNT(*) FROM order_details WHERE seller_id = ? AND status = 'Delivered'");
			pst.setInt(1, sellerId);
			rs = pst.executeQuery();
			while (rs.next()) {
				a.setSoldItems(rs.getInt(1));
			}
			rs.close();
			pst.close();

			// getting total revenue, total profit
			pst = db.get().prepareStatement(
					"SELECT SUM(revenue), SUM(seller_profit) FROM revenue_profit WHERE seller_id = ?");
			pst.setInt(1, sellerId);
			rs = pst.executeQuery();
			while (rs.next()) {
				a.setRevenue(rs.getDouble(1));
				a.setProfit(rs.getDouble(2));
			}
			rs.close();
			pst.close();

		} catch (Exception e) {
			System.out.println(e);
		}
		return a;
	}

	public List<SalesReportDto> getSellerSalesReport(int sellerId, String startDate, String endDate) {
		List<SalesReportDto> l = new ArrayList<>();
		SalesReportDto a;
		try {
			pst = db.get().prepareStatement(
					"SELECT order_date, COUNT(*), SUM(revenue), SUM(revenue)-SUM(seller_profit), SUM(seller_profit) FROM revenue_profit WHERE seller_id = ? AND order_date BETWEEN ? AND ? GROUP BY order_date ORDER BY order_date");
			pst.setInt(1, sellerId);
			pst.setString(2, startDate);
			pst.setString(3, endDate);
			rs = pst.executeQuery();
			while (rs.next()) {
				a = new SalesReportDto();
				a.setDate(rs.getString(1));
				a.setItems(rs.getInt(2));
				a.setRevenue(rs.getDouble(3));
				a.setCosts(rs.getDouble(4));
				a.setProfit(rs.getDouble(5));
				l.add(a);
			}
			rs.close();
			pst.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return l;
	}
	
	public List<SalesReportDto> getAdminSalesReport(String startDate, String endDate) {
		List<SalesReportDto> l = new ArrayList<>();
		SalesReportDto a;
		try {
			pst = db.get().prepareStatement(
					"SELECT order_date, COUNT(*), SUM(revenue), SUM(platform_profit) FROM revenue_profit WHERE order_date BETWEEN ? AND ? GROUP BY order_date ORDER BY order_date");
			pst.setString(1, startDate);
			pst.setString(2, endDate);
			rs = pst.executeQuery();
			while (rs.next()) {
				a = new SalesReportDto();
				a.setDate(rs.getString(1));
				a.setItems(rs.getInt(2));
				a.setRevenue(rs.getDouble(3));
				a.setProfit(rs.getDouble(4));
				l.add(a);
			}
			rs.close();
			pst.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return l;
	}
}
