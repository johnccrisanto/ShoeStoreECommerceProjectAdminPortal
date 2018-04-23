package com.johncrisanto.shoestoreadminportal.service;

import java.util.Set;

import com.johncrisanto.shoestoreadminportal.entity.User;
import com.johncrisanto.shoestoreadminportal.entity.security.UserRole;

public interface UserService {
	
	User createUser(User user, Set<UserRole> userRoleSet) throws Exception;
	
	User save(User user);
}
