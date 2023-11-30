package com.university.controller;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.university.entity.Brand;
import com.university.entity.Customer;
import com.university.entity.Vehicle;
import com.university.service.BrandService;
import com.university.service.CustomerService;
import com.university.utilities.PaginatedResponse;
import com.university.utilities.ResponseHandler;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;


@Tag(name = "Customer")
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
//	@GetMapping(value =  "/login", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//	public ResponseEntity<?> login(
//		@RequestPart(value = "username", required = true) String username,
//		@RequestPart(value = "password", required = true) String password
//	)
//	{
//		
//		return ResponseHandler.generateResponse(null, HttpStatus.OK, null);
//	}
	
	@PostMapping(value =  "/signup", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> create(
			@RequestPart(value = "username", required = true) String username,
			@RequestPart(value = "password", required = true) String password
	) {
		try {
			Customer customer = customerService.create(username, password);
			return ResponseHandler.generateResponse(customer, HttpStatus.OK, null);
		} catch (DuplicateKeyException e) {
			return ResponseHandler.generateResponse(null, HttpStatus.CONFLICT, e.getMessage());
		}
		
	}
}
