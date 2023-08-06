package com.shopsense.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.shopsense.db;
import com.shopsense.dto.WishlistDetail;

@Service
public class WishlistDA {
	PreparedStatement pst;

	public List<WishlistDetail> findAllByCustomerId(int customerId) {
		List<WishlistDetail> l = new ArrayList<>();
		try {
			pst = db.get().prepareStatement(
					"SELECT customer_id, product_id, title, thumbnail_url, sale_price, stock_status FROM wishlist JOIN products USING(product_id) WHERE customer_id = ?");
			pst.setInt(1, customerId);
			ResultSet rs = pst.executeQuery();
			WishlistDetail w;
			while (rs.next()) {
				w = new WishlistDetail();
				w.setCustomerId(rs.getInt(1));
				w.setProductId(rs.getInt(2));
				w.setTitle(rs.getString(3));
				w.setThumbnailUrl(rs.getString(4));
				w.setSalePrice(rs.getInt(5));
				w.setStockStatus(rs.getString(6));
				l.add(w);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return l;
	}
}
