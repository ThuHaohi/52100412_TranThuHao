package com.university.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.university.entity.Brand;
import com.university.entity.Customer;
import com.university.entity.Order;
import com.university.entity.OrderDetail;
import com.university.enums.OrderStatus;
import com.university.repository.BrandRepository;
import com.university.repository.CustomerRepository;
import com.university.repository.OrderDetailRepository;
import com.university.repository.OrderRepository;

@Service
public class OrderDetailService extends BaseService<OrderDetail, OrderDetailRepository> {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	public List<OrderDetail> getByOrderId(UUID orderId) {
		return repository.findByOrderId(orderId);
	}
	
	public UUID create(String username, OrderDetail orderDetail) {
		Customer customer = customerRepository.findByUsername(username);
		Order order = null;
		
		// Loop through all orders of the current user
		for (Order ord : customer.getOrders()) {
			// If there is a pending order, append this order-detail, otherwise creating a new pending order
			if(ord.getStatus().equals(OrderStatus.PENDING.toString())) {
				order = ord;
				// Loop through all order-detail of the pending order
				for (OrderDetail ordDetail : order.getOrderDetails()) {
					// If there is duplicate order-detail, update the amount instead of adding new order-detail
					if (ordDetail.getVehicleId().equals(orderDetail.getVehicleId())) {
						ordDetail.setAmount(ordDetail.getAmount() + orderDetail.getAmount());
						repository.save(ordDetail);
						return order.getId();
					}
				}
				break;
			}
		}
		if (order == null) {
			order = new Order();
			order.setCustomer(customer);
			order.setStatus(OrderStatus.PENDING.toString());
			orderRepository.save(order);
		}
		orderDetail.setOrder(order);
		super.create(orderDetail);
		return order.getId();
	}
	
	@Override
	public boolean deleteById(UUID id) {
		OrderDetail orderDetail = getById(id, true);
		if (orderDetail.getAmount() > 1) {
			orderDetail.setAmount(orderDetail.getAmount() - 1);
			repository.save(orderDetail);
		}
		else {
			repository.delete(orderDetail);
		}
		return true;
	}
	
}
