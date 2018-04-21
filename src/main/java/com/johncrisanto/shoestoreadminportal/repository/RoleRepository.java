package com.johncrisanto.shoestoreadminportal.repository;

import org.springframework.data.repository.CrudRepository;

import com.johncrisanto.shoestoreadminportal.entity.security.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {
//	Role findByName(String name);
}
