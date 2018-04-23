package com.johncrisanto.shoestoreadminportal.repository;

import org.springframework.data.repository.CrudRepository;

import com.johncrisanto.shoestoreadminportal.entity.Shoe;

public interface ShoeRepository extends CrudRepository<Shoe, Long> {
	
}
