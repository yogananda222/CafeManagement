package com.example.demo.dao;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.User;

@Repository
public interface UserDao extends JpaRepository<User, Long> {
	User findByUsername(String username);
	 boolean existsByUsername(String username);
	@Query(value = "SELECT * FROM CUSTOMERS u WHERE u.user_name = ?", nativeQuery = true)
	public Optional<User> findByusername(String username);
}