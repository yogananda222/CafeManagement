package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.foodDao;
import com.example.demo.entity.Food;
import com.example.demo.exception.ProductNotFoundException;

@Service
public class FoodImpl implements FoodService {
	
	@Autowired
	foodDao dao;
	public List<Food> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public void save(Food p) {
		// TODO Auto-generated method stub
		dao.save(p);
	}

	@Override
	public Optional<Food> findById(Long id) {
		// TODO Auto-generated method stub
		return dao.findById(id);
	}

	@Override
	public void updateproduct(Food p) {
		// TODO Auto-generated method stub
		dao.save(p);
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		dao.deleteById(id);
	}

	@Override
	public List<Food> findByFoodname(String foodName) {
		// TODO Auto-generated method stub
		return this.dao.findByFoodName(foodName);
	}

	@Override
	public Optional<Food> getFoodByname(String foodName) {
		// TODO Auto-generated method stub
		return this.dao.findFoodByName(foodName);
	}

	@Override
	public Optional<Food> getUserByFoodId(long foodid) {
	    Optional<Food> product = foodDao.findById(foodid);
	    if (product.isEmpty()) {
	        throw new ProductNotFoundException(foodid);
	    }
	    return product;
	}
	@Override
	public List<Food> findByfoodName(String foodName) {
		return dao.findByfoodNameIgnoreCase(foodName);
	}
	
}