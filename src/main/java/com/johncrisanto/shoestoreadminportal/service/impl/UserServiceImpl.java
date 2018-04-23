package com.johncrisanto.shoestoreadminportal.service.impl;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.johncrisanto.shoestoreadminportal.entity.User;
import com.johncrisanto.shoestoreadminportal.entity.security.UserRole;
import com.johncrisanto.shoestoreadminportal.repository.RoleRepository;
import com.johncrisanto.shoestoreadminportal.repository.UserRepository;
import com.johncrisanto.shoestoreadminportal.service.UserService;



@Service
public class UserServiceImpl implements UserService {
	
	private static final Logger LOG = LoggerFactory.getLogger(UserService.class);
	
	@Autowired 
	private RoleRepository roleRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public User createUser(User user, Set<UserRole> userRoleSet) throws Exception {
		User localUser = userRepository.findByUsername(user.getUsername());
		
		if(localUser != null) {
			LOG.info("User {} already exists.", user.getUsername());
		} else {
			for(UserRole userRole: userRoleSet) {
				roleRepository.save(userRole.getRole());
			}
			
			user.getUserRoleSet().addAll(userRoleSet);
			
			localUser = userRepository.save(user);
		}
		
		return localUser;
	
	}

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

}
