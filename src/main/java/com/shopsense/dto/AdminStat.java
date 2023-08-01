package com.shopsense.dto;

import java.util.HashMap;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AdminStat {
	private double revenue;
	private double profit;
	private int sellers;
	private int customers;
	private List<HashMap<String, String>> weeklyRevenue;
	private List<HashMap<String, String>> bestSeller;
	private List<HashMap<String, String>> orderStatus;
}

