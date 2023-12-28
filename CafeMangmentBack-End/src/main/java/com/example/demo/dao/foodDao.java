package com.example.demo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.Food;

public interface foodDao extends JpaRepository<Food, Long>
{
	List<Food> findByFoodName(String foodName);
		
	@Query(value = "SELECT * FROM Food_items pd WHERE pd.food_name = ?1",nativeQuery = true )
	
	public Optional<Food> findFoodByName (String title);

	static Optional<Food> findById(long foodid) {
		// TODO Auto-generated method stub
		return null;
	}
	List<Food>findByfoodNameIgnoreCase(String foodName);
	boolean existsByfoodName(String foodName);
}