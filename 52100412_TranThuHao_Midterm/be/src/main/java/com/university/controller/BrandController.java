package com.university.controller;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.university.entity.Brand;
import com.university.entity.Vehicle;
import com.university.service.BrandService;
import com.university.utilities.PaginatedResponse;
import com.university.utilities.ResponseHandler;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;


@Tag(name = "Brand")
@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class BrandController implements SecuredRestController {
	
	@Autowired
	private BrandService brandService;
	
	@GetMapping("/brands")
	public ResponseEntity<?> getAll(
			@RequestParam(defaultValue = "0") int pageIndex,
			@RequestParam(defaultValue = "10") int pageSize
	) {
		Page<Brand> brands = brandService.getAll(pageIndex, pageSize);
		PaginatedResponse<Vehicle> paginatedResponse = new PaginatedResponse(brands.getContent(), brands.getTotalElements(), brands.getTotalPages());
		return ResponseHandler.generateResponse(paginatedResponse, HttpStatus.OK, null);
	}
	
	@GetMapping("/brands/{id}")
	public ResponseEntity<?> getById(@PathVariable UUID id) {
		try {
			Brand brand = brandService.getById(id, true);
			return ResponseHandler.generateResponse(brand, HttpStatus.OK, null);
		} catch (NoSuchElementException e) {
			return ResponseHandler.generateResponse(null, HttpStatus.NOT_FOUND, e.getMessage());
		}
		
	}
	
	@PostMapping("/brands")
	public ResponseEntity<?> create(@RequestBody Brand payload) {
		brandService.create(payload);
		return ResponseHandler.generateResponse(payload, HttpStatus.OK, null);
	}
	
	@PutMapping("/brands/{id}")
	public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody Brand payload) {
		try {
			Brand brand = brandService.updateById(id, payload);
			return ResponseHandler.generateResponse(payload, HttpStatus.OK, null);
		} catch (NoSuchElementException e) {
			return ResponseHandler.generateResponse(payload, HttpStatus.NOT_FOUND, e.getMessage());
		}
	}
	
	@DeleteMapping("/brands/{id}")
	public ResponseEntity<?> deleteById(@PathVariable UUID id) {
		try {
			brandService.deleteById(id);
			return ResponseHandler.generateResponse(null, HttpStatus.OK, null);
		} catch (NoSuchElementException e) {
			return ResponseHandler.generateResponse(null, HttpStatus.NOT_FOUND, e.getMessage());
		}
		
	}
	
	
}
