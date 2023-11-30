package com.university.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.university.entity.BaseModel;

@Service
public abstract class BaseService<P extends BaseModel, R extends JpaRepository<P, UUID>> {
	
    @Autowired
    protected R repository;
    
    public P create(P payload) {
    	payload.setId(null);
//        input.setCreatedAt(new Date());
//        input.setCreatedBy(userId);
//        input.setUpdateAt(null);
//        input.setUpdatedBy(null);
        repository.save(payload);
        return payload;
    }
    
    public boolean deleteById(UUID id) {
       P data = getById(id, true);
       repository.delete(data);
       return true;
    }
    
    public P updateById(UUID id, P payload) {
        P model =  getById(id, true);
        payload.setId(model.getId());
        repository.save(payload);
        return payload;
    }
    
    public P getById(UUID id, Boolean isRequired) {
    	Optional<P> model =  repository.findById(id);
    	if (!model.isPresent() && isRequired) {
    		throw new NoSuchElementException(id.toString());
    	}
    	return model.orElse(null);
    }
    
    public Page<P> getAll(int pageIndex, int pageSize) {
    	Pageable pageable = PageRequest.of(pageIndex, pageSize);
    	Page<P> paginatedData = repository.findAll(pageable);
    	return paginatedData;
    }
	
}
