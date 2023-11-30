package com.university.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.university.entity.Brand;
import com.university.entity.Vehicle;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, UUID> {
	
	public Page<Vehicle> findByBrandId(UUID brandId, Pageable pageable);
	
	@Query("SELECT v from Vehicle v WHERE v.title LIKE CONCAT('%', :keyword, '%') or v.price like CONCAT('%', :keyword, '%')")
	public Page<Vehicle> findByKeyword(@Param("keyword") String keyword, Pageable pageable);
	
}
