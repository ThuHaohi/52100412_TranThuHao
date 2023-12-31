package com.university.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.university.entity.Brand;
import com.university.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {
	
	public List<Order> findByCustomerId(UUID customerId);
	
}
