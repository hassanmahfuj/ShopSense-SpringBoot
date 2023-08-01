package com.shopsense.model;

public class Category {
	int id;
	String title;
	String description;
	String icon;
	int parent;

	public Category() {
		super();
	}

	public Category(int id, String title, String description, String icon, int parent) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.icon = icon;
		this.parent = parent;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public int getParent() {
		return parent;
	}

	public void setParent(int parent) {
		this.parent = parent;
	}

}
