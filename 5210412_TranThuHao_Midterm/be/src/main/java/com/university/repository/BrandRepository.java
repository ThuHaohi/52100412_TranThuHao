package com.university.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.university.entity.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand, UUID> {

}
