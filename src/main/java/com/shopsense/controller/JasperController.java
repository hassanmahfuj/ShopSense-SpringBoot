package com.shopsense.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shopsense.dao.JasperDA;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Controller
public class JasperController {

	@Autowired
	JasperDA da;

	@GetMapping(value = "/reports/vendor-sales", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<byte[]> getAdminSalesReportGroupBySeller(@RequestParam String startDate, @RequestParam String endDate) {
		try {
			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(da.getAdminSalesReportGroupBySeller(startDate, endDate), false);
			Map<String, Object> param = new HashMap<>();
			JasperReport compileReport = JasperCompileManager
					.compileReport(new FileInputStream("src/main/resources/jasper/VendorSales.jrxml"));
			JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, param, dataSource);
			byte[] data = JasperExportManager.exportReportToPdf(jasperPrint);
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Disposition", "inline; filename=citiesreport.pdf");
			return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
		} catch (FileNotFoundException | JRException e) {
			System.out.println(e);
		}
		return null;
	}
	
	@GetMapping(value = "/reports/product-details", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<byte[]> getProductDetailsReport() {
		try {
			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(da.getProductDetailsReport(), false);
			Map<String, Object> param = new HashMap<>();
			JasperReport compileReport = JasperCompileManager
					.compileReport(new FileInputStream("src/main/resources/jasper/ProductDetails.jrxml"));
			JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, param, dataSource);
			byte[] data = JasperExportManager.exportReportToPdf(jasperPrint);
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Disposition", "inline; filename=citiesreport.pdf");
			return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
		} catch (FileNotFoundException | JRException e) {
			System.out.println(e);
		}
		return null;
	}
	
	@GetMapping(value = "/reports/favorite-item", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<byte[]> getFavoriteItemReport() {
		try {
			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(da.getFavoriteItemReport(), false);
			Map<String, Object> param = new HashMap<>();
			JasperReport compileReport = JasperCompileManager
					.compileReport(new FileInputStream("src/main/resources/jasper/FavoriteItem.jrxml"));
			JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, param, dataSource);
			byte[] data = JasperExportManager.exportReportToPdf(jasperPrint);
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Disposition", "inline; filename=citiesreport.pdf");
			return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
		} catch (FileNotFoundException | JRException e) {
			System.out.println(e);
		}
		return null;
	}
	
	@GetMapping(value = "/reports/customer", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<byte[]> getCustomerReport() {
		try {
			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(da.getCustomerReport(), false);
			Map<String, Object> param = new HashMap<>();
			JasperReport compileReport = JasperCompileManager
					.compileReport(new FileInputStream("src/main/resources/jasper/CustomerReport.jrxml"));
			JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, param, dataSource);
			byte[] data = JasperExportManager.exportReportToPdf(jasperPrint);
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Disposition", "inline; filename=citiesreport.pdf");
			return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
		} catch (FileNotFoundException | JRException e) {
			System.out.println(e);
		}
		return null;
	}
	
	@GetMapping(value = "/reports/admin-profit", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<byte[]> getAdminProfitReport() {
		try {
			List<HashMap<String, String>> l = da.getAdminProfitReport();
			double totalSale = 0;
			double totalProfit = 0;
			for(HashMap<String, String> a : l) {
				totalSale += Double.parseDouble(a.get("sales"));
				totalProfit += Double.parseDouble(a.get("platformProfit"));
			}
			
			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(l, false);
			
			Map<String, Object> param = new HashMap<>();
			param.put("totalSale", String.valueOf(totalSale));
			param.put("totalProfit", String.valueOf(totalProfit));
			
			JasperReport compileReport = JasperCompileManager
					.compileReport(new FileInputStream("src/main/resources/jasper/AdminProfits.jrxml"));
			JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, param, dataSource);
			byte[] data = JasperExportManager.exportReportToPdf(jasperPrint);
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Disposition", "inline; filename=citiesreport.pdf");
			return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
		} catch (FileNotFoundException | JRException e) {
			System.out.println(e);
		}
		return null;
	}
	
	@GetMapping(value = "/reports/seller", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<byte[]> getSellerReport() {
		try {
			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(da.getSellerReport(), false);
			Map<String, Object> param = new HashMap<>();
			JasperReport compileReport = JasperCompileManager
					.compileReport(new FileInputStream("src/main/resources/jasper/SellerReport.jrxml"));
			JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, param, dataSource);
			byte[] data = JasperExportManager.exportReportToPdf(jasperPrint);
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Disposition", "inline; filename=citiesreport.pdf");
			return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
		} catch (FileNotFoundException | JRException e) {
			System.out.println(e);
		}
		return null;
	}
	
	@GetMapping(value = "/reports/customer-order", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<byte[]> getCustomerOrderById(@RequestParam int id) {
		try {
			HashMap<String, Object> m = da.getCustomerOrderById(id);
			@SuppressWarnings("unchecked")
			List<HashMap<String, String>> orders = (List<HashMap<String, String>>) m.get("orders");
			
			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(orders, false);
			Map<String, Object> param = new HashMap<>();
			param.put("name", m.get("name"));
			param.put("email", m.get("email"));
			param.put("address", m.get("address"));
			
			JasperReport compileReport = JasperCompileManager
					.compileReport(new FileInputStream("src/main/resources/jasper/CustomerOrders.jrxml"));
			JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, param, dataSource);
			byte[] data = JasperExportManager.exportReportToPdf(jasperPrint);
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Disposition", "inline; filename=citiesreport.pdf");
			return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
		} catch (FileNotFoundException | JRException e) {
			System.out.println(e);
		}
		return null;
	}
	
	@GetMapping(value = "/reports/stock-alert", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<byte[]> getStockAlertReport(@RequestParam int id) {
		try {
			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(da.getStockAlertReport(id), false);
			Map<String, Object> param = new HashMap<>();
			JasperReport compileReport = JasperCompileManager
					.compileReport(new FileInputStream("src/main/resources/jasper/StockAlert.jrxml"));
			JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, param, dataSource);
			byte[] data = JasperExportManager.exportReportToPdf(jasperPrint);
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Disposition", "inline; filename=citiesreport.pdf");
			return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
		} catch (FileNotFoundException | JRException e) {
			System.out.println(e);
		}
		return null;
	}
	
	@GetMapping(value = "/reports/top-selling", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<byte[]> getTopSellingReport(@RequestParam int id) {
		try {
			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(da.getTopSellingReport(id), false);
			Map<String, Object> param = new HashMap<>();
			JasperReport compileReport = JasperCompileManager
					.compileReport(new FileInputStream("src/main/resources/jasper/TopSelling.jrxml"));
			JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, param, dataSource);
			byte[] data = JasperExportManager.exportReportToPdf(jasperPrint);
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Disposition", "inline; filename=citiesreport.pdf");
			return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
		} catch (FileNotFoundException | JRException e) {
			System.out.println(e);
		}
		return null;
	}
}
