package com.example.demo.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Admins;


@Repository
public interface AdminDao extends JpaRepository <Admins, Integer> 
{
	Admins findAdminByUsername(String username);

	@Query(value = "SELECT * FROM ADMIN u WHERE u.username = ?", nativeQuery = true)
	public Optional<Admins> findByusername(String username);
	
	Optional<Admins> findById(int id);
}