package com.university.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.university.entity.Customer;
import com.university.repository.CustomerRepository;

@Service
public class CustomerService extends BaseService<Customer, CustomerRepository> implements UserDetailsService {
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private CustomerRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Customer customer = repository.findByUsername(username);
		if (customer == null) {
			throw new UsernameNotFoundException("Customer not found !");
		}
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

		org.springframework.security.core.userdetails.User userDetails = new User(customer.getUsername(), customer.getPassword(), authorities);
		return userDetails;
	}
	
	public Customer create(String username, String password) {
		if (repository.findByUsername(username) != null) {
			throw new DuplicateKeyException("Account with this username already exist");
		}
		Customer customer = new Customer();
		customer.setUsername(username);
		customer.setPassword(bCryptPasswordEncoder.encode(password));
		return super.create(customer);
	}
}
