package com.shopsense.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Product {
	int id;
	String title;
	String thumbnailUrl;
	String description;
	String regularPrice;
	String salePrice;
	String category;
	String stockStatus;
	String stockCount;
	int sellerId;
	String storeName;
	String status;
}
