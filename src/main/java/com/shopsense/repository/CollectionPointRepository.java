package com.shopsense.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopsense.entity.CollectionPoint;

public interface CollectionPointRepository extends JpaRepository<CollectionPoint, Integer> {

	public List<CollectionPoint> findAllByDistrict(String district);
}
