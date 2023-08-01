package com.shopsense.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SalesReportDto {
	private String date;
	private int items;
	private double revenue;
	private double costs;
	private double profit;
}
