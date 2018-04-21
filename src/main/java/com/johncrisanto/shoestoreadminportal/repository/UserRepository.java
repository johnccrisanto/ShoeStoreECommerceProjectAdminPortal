package com.johncrisanto.shoestoreadminportal.repository;
import org.springframework.data.repository.CrudRepository;

import com.johncrisanto.shoestoreadminportal.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
    
    User findByEmail(String email);
 
}
