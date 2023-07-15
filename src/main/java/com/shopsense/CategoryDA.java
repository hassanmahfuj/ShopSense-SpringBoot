package com.shopsense;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.shopsense.models.Category;

public class CategoryDA {
	PreparedStatement pst;

	public List<Category> getCategories() {
		List<Category> list = new ArrayList<>();
		try {
			pst = db.get().prepareStatement("SELECT category_id, title, description, icon, parent_id FROM categories");
			ResultSet rs = pst.executeQuery();
			Category p;
			while (rs.next()) {
				p = new Category();
				p.setId(rs.getInt(1));
				p.setTitle(rs.getString(2));
				p.setDescription(rs.getString(3));
				p.setIcon(rs.getString(4));
				p.setParent(rs.getInt(5));
				list.add(p);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return list;
	}
}
