package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;
import com.example.demo.exception.UsernameAlreadyExistsException;


@Service
public class UserImpl implements UserService {
	@Autowired
	UserDao dao;

	@Override
	public void addUser(User user) {
		if (dao.existsByUsername(user.getUsername())) {
			 throw new UsernameAlreadyExistsException(user.getUsername());
        }
		else {this.dao.save(user);}
		

	}

	@Override
	public List<User> findAll() {
		return dao.findAll();
	}

	@Override
	public void updateUser(User user) {
		this.dao.save(user);

	}

	@Override
	public Optional<User> findUserById(long id) {
		return this.dao.findById(id);
		
	}

	@Override
	public void deleteById(long id) {
		dao.deleteById(id);
		
	}

	@Override
	public User findByUsername(String username) {
		return dao.findByUsername(username);
	}

	@Override
	public Optional<User> getUserByName(String username) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

}