package com.shopsense.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.shopsense.entity.Wishlist;

import jakarta.transaction.Transactional;

public interface WishlistRepository extends JpaRepository<Wishlist, Integer> {

	@Transactional
	void deleteByCustomerIdAndProductId(int customerId, int productId);

	boolean existsByCustomerIdAndProductId(int customerId, int productId);

	List<Wishlist> findAllByCustomerId(int customerId);
}
