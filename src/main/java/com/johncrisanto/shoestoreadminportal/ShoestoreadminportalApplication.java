package com.johncrisanto.shoestoreadminportal;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.johncrisanto.shoestoreadminportal.entity.User;
import com.johncrisanto.shoestoreadminportal.entity.security.Role;
import com.johncrisanto.shoestoreadminportal.entity.security.UserRole;
import com.johncrisanto.shoestoreadminportal.service.UserService;
import com.johncrisanto.shoestoreadminportal.utility.SecurityUtility;

@SpringBootApplication
public class ShoestoreadminportalApplication implements CommandLineRunner {
	
	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(ShoestoreadminportalApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		User user1 = new User();
		user1.setFirstName("John");
		user1.setLastName("Adams");
		user1.setUsername("admin");
		user1.setPassword(SecurityUtility.passwordEncoder().encode("password"));
		user1.setEmail("jadamsadmin@gmail.com");
		Set<UserRole> userRoleSet = new HashSet<>();
		Role role1 = new Role();
		role1.setRoleId(0);
		role1.setRoleName("ROLE_ADMIN");
		userRoleSet.add(new UserRole(user1, role1));
		userService.createUser(user1, userRoleSet);
	}
}
