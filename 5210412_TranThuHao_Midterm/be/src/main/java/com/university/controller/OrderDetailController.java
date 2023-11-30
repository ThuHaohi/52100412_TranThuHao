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
import com.university.entity.Order;
import com.university.entity.OrderDetail;
import com.university.entity.Vehicle;
import com.university.service.BrandService;
import com.university.service.OrderDetailService;
import com.university.service.OrderService;
import com.university.utilities.JwtUtils;
import com.university.utilities.PaginatedResponse;
import com.university.utilities.ResponseHandler;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;


@Tag(name = "Order Detail")
@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class OrderDetailController implements SecuredRestController {
	
	@Autowired
	private OrderDetailService orderDetailService;
	
	@GetMapping("/order-details")
	public ResponseEntity<?> getAll(@RequestParam(required = true) UUID orderId) {
		List<OrderDetail> orderDetails = orderDetailService.getByOrderId(orderId);
		return ResponseHandler.generateResponse(orderDetails, HttpStatus.OK, null);
	}
	
	@GetMapping("/order-details/{id}")
	public ResponseEntity<?> getById(@PathVariable UUID id) {
		try {
			OrderDetail orderDetail = orderDetailService.getById(id, true);
			return ResponseHandler.generateResponse(orderDetail, HttpStatus.OK, null);
		} catch (NoSuchElementException e) {
			return ResponseHandler.generateResponse(null, HttpStatus.NOT_FOUND, e.getMessage());
		}
		
	}
	
	@PostMapping("/order-details")
	public ResponseEntity<?> create(@RequestBody OrderDetail payload) {
		String username = JwtUtils.getUsernameFromToken();
		UUID orderId = orderDetailService.create(username, payload);
		return ResponseHandler.generateResponse(orderId, HttpStatus.OK, null);
	}
	
//	@PutMapping("/order-details/{id}")
//	public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody OrderDetail payload) {
//		try {
//			OrderDetail orderDetail = orderDetailService.updateById(id, payload);
//			return ResponseHandler.generateResponse(payload, HttpStatus.OK, null);
//		} catch (NoSuchElementException e) {
//			return ResponseHandler.generateResponse(payload, HttpStatus.NOT_FOUND, e.getMessage());
//		}
//	}
	
	@DeleteMapping("/order-details/{id}")
	public ResponseEntity<?> deleteById(@PathVariable UUID id) {
		try {
			orderDetailService.deleteById(id);
			return ResponseHandler.generateResponse(null, HttpStatus.OK, null);
		} catch (NoSuchElementException e) {
			return ResponseHandler.generateResponse(null, HttpStatus.NOT_FOUND, e.getMessage());
		}
		
	}
	
	
}
