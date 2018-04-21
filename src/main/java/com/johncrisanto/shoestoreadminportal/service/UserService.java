package com.johncrisanto.shoestoreadminportal.service;

import java.util.Set;

import com.johncrisanto.shoestoreadminportal.entity.User;
import com.johncrisanto.shoestoreadminportal.entity.security.PasswordResetToken;
import com.johncrisanto.shoestoreadminportal.entity.security.UserRole;


public interface UserService {
	PasswordResetToken getPasswordResetToken(final String token);
	
	void createPasswordResetTokenForUser(final User user, final String token);
	
	User findByUsername(String username);
	
	User findByEmail(String email);
	
	User createUser(User user, Set<UserRole> userRoleSet) throws Exception;
	
	User save(User user);
}
