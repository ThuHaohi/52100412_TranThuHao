package com.university.service;

import java.text.DecimalFormat;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.university.entity.Brand;
import com.university.entity.Customer;
import com.university.entity.Order;
import com.university.entity.OrderDetail;
import com.university.entity.Vehicle;
import com.university.enums.OrderStatus;
import com.university.repository.BrandRepository;
import com.university.repository.CustomerRepository;
import com.university.repository.OrderRepository;

@Service
public class OrderService extends BaseService<Order, OrderRepository> {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	public Order create(String username, Order order) {
		Customer customer = customerRepository.findByUsername(username);
		order.setCustomer(customer);
		return super.create(order);
	}
	
	public List<Order> getByCustomer(String username) {
		Customer customer = customerRepository.findByUsername(username);
		return repository.findByCustomerId(customer.getId());
	}
	
	public void MarkAsDelivered(String username) {
		Customer customer = customerRepository.findByUsername(username);
		for (Order order : customer.getOrders()) {
			if (order.getStatus().equals(OrderStatus.PENDING.toString())) {
				order.setStatus(OrderStatus.DELIVERED.toString());
				repository.save(order);
				return;
			}
		}
		throw new NoSuchElementException();
	}
	
	public void MarkAsDelivered(UUID id) {
		Order order = getById(id, true);
		order.setStatus(OrderStatus.DELIVERED.toString());
		repository.save(order);
		return;
	}
	
	public void setTotalPrice(Order order) {
		double totalPrice = 0;
		for (OrderDetail orderDetail : order.getOrderDetails()) {
			Vehicle vehicle = orderDetail.getVehicle();
			totalPrice += vehicle.getPrice() * orderDetail.getAmount();
		}
		DecimalFormat numberFormat = new DecimalFormat("#.00");
		order.setTotalPrice(Double.parseDouble(numberFormat.format(totalPrice)));
	}
	
}
