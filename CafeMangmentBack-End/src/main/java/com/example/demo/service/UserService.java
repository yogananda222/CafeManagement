package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Admins;
import com.example.demo.entity.User;

public interface UserService {

	public void addUser(User user);

	public List<User> findAll();

	public void updateUser(User customer);

	public void deleteById(long id);

	User findByUsername(String username);	

	public Optional<User> getUserByName(String username);
	public Optional<User> findUserById(long id);
}