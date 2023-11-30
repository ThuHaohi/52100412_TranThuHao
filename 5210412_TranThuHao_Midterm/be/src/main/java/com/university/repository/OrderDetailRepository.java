package com.university.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.university.entity.Brand;
import com.university.entity.Order;
import com.university.entity.OrderDetail;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, UUID> {
	public List<OrderDetail> findByOrderId(UUID orderId);
}
