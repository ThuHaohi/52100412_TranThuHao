package com.university.controller;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import javax.xml.crypto.Data;

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
import com.university.service.VehicleService;
import com.university.utilities.PaginatedResponse;
import com.university.utilities.ResponseHandler;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;


@Tag(name = "Vehicle")
@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class VehicleController implements SecuredRestController {
	
	@Autowired
	private VehicleService vehicleService;
	
	@GetMapping("/vehicles")
	public ResponseEntity<?> getAll(
			@RequestParam(required = false) UUID brandId,
			@RequestParam(required = false) String keyword,
			@RequestParam(defaultValue = "0") int pageIndex,
			@RequestParam(defaultValue = "10") int pageSize
	) {
		Page<Vehicle> vehicles;
		if (brandId != null) {
			vehicles = vehicleService.getByBrandId(brandId, pageIndex, pageSize);
		}
		else if (keyword != null) {
			System.out.println("KEYWORD: "+ keyword);
			vehicles = vehicleService.search(keyword, pageIndex, pageSize);
		}
		else {
			vehicles = vehicleService.getAll(pageIndex, pageSize); 
		}
		PaginatedResponse<Vehicle> paginatedResponse = new PaginatedResponse(vehicles.getContent(), vehicles.getTotalElements(), vehicles.getTotalPages());
		return ResponseHandler.generateResponse(paginatedResponse, HttpStatus.OK, null);
	}
	
	@GetMapping("/vehicles/{id}")
	public ResponseEntity<?> getById(@PathVariable UUID id) {
		try {
			Vehicle vehicle = vehicleService.getById(id, true);
			return ResponseHandler.generateResponse(vehicle, HttpStatus.OK, null);
		} catch (NoSuchElementException e) {
			return ResponseHandler.generateResponse(null, HttpStatus.NOT_FOUND, e.getMessage());
		}
		
	}
	
	@PostMapping("/vehicles")
	public ResponseEntity<?> create(@RequestBody Vehicle payload) {
		vehicleService.create(payload);
		return ResponseHandler.generateResponse(payload, HttpStatus.OK, null);
	}
	
	@PutMapping("/vehicles/{id}")
	public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody Vehicle payload) {
		try {
			Vehicle vehicle = vehicleService.updateById(id, payload);
			return ResponseHandler.generateResponse(payload, HttpStatus.OK, null);
		} catch (NoSuchElementException e) {
			return ResponseHandler.generateResponse(payload, HttpStatus.NOT_FOUND, e.getMessage());
		}
	}
	
	@DeleteMapping("/vehicles/{id}")
	public ResponseEntity<?> deleteById(@PathVariable UUID id) {
		try {
			vehicleService.deleteById(id);
			return ResponseHandler.generateResponse(null, HttpStatus.OK, null);
		} catch (NoSuchElementException e) {
			return ResponseHandler.generateResponse(null, HttpStatus.NOT_FOUND, e.getMessage());
		}
		
	}
	
	
}
