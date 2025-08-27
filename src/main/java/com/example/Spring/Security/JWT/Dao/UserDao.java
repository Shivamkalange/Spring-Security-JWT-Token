package com.example.Spring.Security.JWT.Dao;

import com.example.Spring.Security.JWT.AuthenticationService.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, String> {

    User findByUsername(String username);
}
