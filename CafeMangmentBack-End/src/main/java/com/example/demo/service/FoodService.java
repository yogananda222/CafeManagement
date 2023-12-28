package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Food;

public interface FoodService {

	public List<Food>findAll();
	public void save(Food p);
	public Optional<Food>findById(Long id);
	public void updateproduct(Food p);
	public void deleteById(Long id);
	public List<Food>findByFoodname(String foodName);
	public Optional<Food>getFoodByname(String foodName);
	public Optional<Food> getUserByFoodId(long foodid);
	List<Food> findByfoodName(String foodName);

}