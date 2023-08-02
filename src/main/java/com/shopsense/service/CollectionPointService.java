package com.shopsense.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopsense.entity.CollectionPoint;
import com.shopsense.repository.CollectionPointRepository;

@Service
public class CollectionPointService {

	@Autowired
	CollectionPointRepository collectionPointRepository;

	public CollectionPoint createOrUpdateCollectionPoint(CollectionPoint collectionPoint) {
		return collectionPointRepository.save(collectionPoint);
	}

	public List<CollectionPoint> readAllCollectionPoint() {
		return collectionPointRepository.findAll();
	}

	public List<CollectionPoint> readByDistrict(String district) {
		return collectionPointRepository.findAllByDistrict(district);
	}

	public void deleteCollectionPoint(int id) {
		collectionPointRepository.deleteById(id);
	}
}
