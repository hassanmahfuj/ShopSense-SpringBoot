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

	public Category create(Category c) {
		try {
			pst = db.get().prepareStatement(
					"INSERT INTO categories (title, description, icon, parent_id) VALUES (?, ?, ?, ?)");
			pst.setString(1, c.getTitle());
			pst.setString(2, c.getDescription());
			pst.setString(3, c.getIcon());
			pst.setInt(4, c.getParent());
			int x = pst.executeUpdate();
			if (x != -1) {
				return c;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	public boolean update(Category c) {
		try {
			pst = db.get().prepareStatement(
					"UPDATE categories SET title = ?, description = ?, icon = ?, parent_id = ? WHERE category_id = ?");
			pst.setString(1, c.getTitle());
			pst.setString(2, c.getDescription());
			pst.setString(3, c.getIcon());
			pst.setInt(4, c.getParent());
			pst.setInt(5, c.getId());
			int x = pst.executeUpdate();
			if (x != -1) {
				return true;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	public boolean delete(int id) {
		try {
			pst = db.get().prepareStatement("DELETE FROM categories WHERE category_id = ?");
			pst.setInt(1, id);
			int x = pst.executeUpdate();
			if (x != -1) {
				return true;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}
}
