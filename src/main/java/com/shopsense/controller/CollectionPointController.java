package com.shopsense.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shopsense.dto.ApiResponse;
import com.shopsense.entity.CollectionPoint;
import com.shopsense.service.CollectionPointService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/collectionpoint")
public class CollectionPointController {

	@Autowired
	CollectionPointService cps;

	@PostMapping()
	public CollectionPoint createCollectionPoint(@RequestBody CollectionPoint cpd) {
		return cps.createOrUpdateCollectionPoint(cpd);
	}

	@GetMapping
	public List<CollectionPoint> readAllCollectionPoint() {
		return cps.readAllCollectionPoint();
	}

	@GetMapping("/search")
	public List<CollectionPoint> readByDistrict(@RequestParam String district) {
		return cps.readByDistrict(district);
	}

	@PutMapping()
	public CollectionPoint updateCollectionPoint(@RequestBody CollectionPoint cpd) {
		return cps.createOrUpdateCollectionPoint(cpd);
	}

	@DeleteMapping()
	public ApiResponse deleteCollectionPoint(@RequestParam int id) {
		cps.deleteCollectionPoint(id);
		return new ApiResponse(true, "Collection Point Deleted");
	}
}
