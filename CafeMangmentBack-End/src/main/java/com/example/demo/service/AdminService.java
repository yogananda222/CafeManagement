package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Admins;

public interface AdminService 
{
	public List<Admins> findAll();
	public void updateAdmin(Admins admin);
	Admins findByUsername(String username);
	public void deleteById(int id);
	public Optional<Admins>findById(int id);
	public void addAdmin(Admins admin);
}