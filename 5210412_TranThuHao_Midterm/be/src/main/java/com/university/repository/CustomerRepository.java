package com.university.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.university.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID> {
	Customer findByUsername(String username);
}
