package com.shopsense.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WishlistDetail {
	private int customerId;
	private int productId;
	private String title;
	private String thumbnailUrl;
	private double salePrice;
	private String stockStatus;
}
