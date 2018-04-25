package com.johncrisanto.shoestoreadminportal.service;

import java.util.List;

import com.johncrisanto.shoestoreadminportal.entity.Shoe;

public interface ShoeService {

	Shoe save(Shoe shoe);

	List<Shoe> findAll();

	Shoe findById(Long id);
}
