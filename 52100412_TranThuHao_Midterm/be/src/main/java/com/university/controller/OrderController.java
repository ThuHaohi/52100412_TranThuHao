package com.university.controller;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
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
import com.university.entity.Order;
import com.university.entity.Vehicle;
import com.university.enums.OrderStatus;
import com.university.service.BrandService;
import com.university.service.OrderService;
import com.university.utilities.JwtUtils;
import com.university.utilities.PaginatedResponse;
import com.university.utilities.ResponseHandler;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;


@Tag(name = "Order")
@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class OrderController implements SecuredRestController {
	
	@Autowired
	private OrderService orderService;
	
	@GetMapping("/orders")
	public ResponseEntity<?> getAll() {
		String username = JwtUtils.getUsernameFromToken();
		List<Order> orders = orderService.getByCustomer(username);
		return ResponseHandler.generateResponse(orders, HttpStatus.OK, null);
	}
	
	@GetMapping("/orders/{id}")
	public ResponseEntity<?> getById(@PathVariable UUID id) {
		try {
			Order order = orderService.getById(id, true);
			orderService.setTotalPrice(order);
			return ResponseHandler.generateResponse(order, HttpStatus.OK, null);
		} catch (NoSuchElementException e) {
			return ResponseHandler.generateResponse(null, HttpStatus.NOT_FOUND, e.getMessage());
		}
		
	}
	
//	@PostMapping("/orders")
//	public ResponseEntity<?> create(@RequestBody Order payload) {
//		String username = JwtUtils.getUsernameFromToken();
//		orderService.create(username, payload);
//		return ResponseHandler.generateResponse(payload, HttpStatus.OK, null);
//	}
	
//	@PutMapping("/orders")
//	public ResponseEntity<?> proceedOrder() {
//		String username = JwtUtils.getUsernameFromToken();
//		try {
//			orderService.MarkAsDelivered(username);
//			return ResponseHandler.generateResponse(null, HttpStatus.OK, null);
//		} catch (NoSuchElementException e) {
//			return ResponseHandler.generateResponse(null, HttpStatus.NOT_FOUND, e.getMessage());
//		}
//	}
	
	@PutMapping("/orders/{id}")
	public ResponseEntity<?> proceedOrder(@PathVariable UUID id) {
		try {
			orderService.MarkAsDelivered(id);
			return ResponseHandler.generateResponse(null, HttpStatus.OK, null);
		} catch (NoSuchElementException e) {
			return ResponseHandler.generateResponse(null, HttpStatus.NOT_FOUND, e.getMessage());
		}
	}
	
	@DeleteMapping("/orders/{id}")
	public ResponseEntity<?> deleteById(@PathVariable UUID id) {
		try {
			orderService.deleteById(id);
			return ResponseHandler.generateResponse(null, HttpStatus.OK, null);
		} catch (NoSuchElementException e) {
			return ResponseHandler.generateResponse(null, HttpStatus.NOT_FOUND, e.getMessage());
		}
		
	}
	
	
}
