package com.university.service;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.university.entity.Vehicle;
import com.university.repository.VehicleRepository;

@Service
public class VehicleService extends BaseService<Vehicle, VehicleRepository> {
	
	public Page<Vehicle> getByBrandId(UUID brandId, int pageIndex, int pageSize) {
		Pageable pageable = PageRequest.of(pageIndex, pageSize);
		return repository.findByBrandId(brandId, pageable);
	}
	
	public Page<Vehicle> search(String keyword, int pageIndex, int pageSize) {
		Pageable pageable = PageRequest.of(pageIndex, pageSize);
		return repository.findByKeyword(keyword, pageable);
	}

}
