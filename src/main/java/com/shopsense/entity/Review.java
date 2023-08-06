package com.shopsense.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Review {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int reviewId;
	private int customerId;
	private String customerName;
	private int productId;
	private int star;
	private String comment;
}
