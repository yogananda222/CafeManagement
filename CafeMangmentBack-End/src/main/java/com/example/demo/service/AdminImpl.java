package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.AdminDao;
import com.example.demo.entity.Admins;

@Service
public class AdminImpl implements AdminService {

	@Autowired
	AdminDao dao;
	@Override
	public List<Admins> findAll() 
	{
		// TODO Auto-generated method stub
	    return dao.findAll();
	}

	@Override
	public void updateAdmin(Admins admin) {
		// TODO Auto-generated method stub
		this.dao.save(admin);
	}

	@Override
	public Admins findByUsername(String username) {
		// TODO Auto-generated method stub
		return dao.findAdminByUsername(username);
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		dao.deleteById(id);	
	}

	@Override
	public Optional<Admins> findById(int id) {
		// TODO Auto-generated method stub
		return dao.findById(id);
	}

	@Override
	public void addAdmin(Admins admin) {
		// TODO Auto-generated method stub
		this.dao.save(admin);
	}

}