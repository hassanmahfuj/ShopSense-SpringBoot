package com.shopsense.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SellerStat {
	private int totalOrder;
	private int newOrder;
	private int processing;
	private int soldItems;
	private double revenue;
	private double profit;
}
