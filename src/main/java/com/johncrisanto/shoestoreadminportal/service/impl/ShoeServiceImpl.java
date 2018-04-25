package com.johncrisanto.shoestoreadminportal.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.johncrisanto.shoestoreadminportal.entity.Shoe;
import com.johncrisanto.shoestoreadminportal.repository.ShoeRepository;
import com.johncrisanto.shoestoreadminportal.service.ShoeService;

@Service
public class ShoeServiceImpl implements ShoeService {
	
		@Autowired
		private ShoeRepository shoeRepository;

		@Override
		public Shoe save(Shoe shoe) {
			return shoeRepository.save(shoe);
		}

		@Override
		public List<Shoe> findAll() {
			return (List<Shoe>) shoeRepository.findAll();
		}

		@Override
		public Shoe findById(Long id) {
			return shoeRepository.findById(id).orElse(null);
		}
		
}
