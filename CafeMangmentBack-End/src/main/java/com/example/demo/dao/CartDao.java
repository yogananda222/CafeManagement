package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.CartItem;
import com.example.demo.entity.Food;
import com.example.demo.entity.User;

public interface CartDao extends JpaRepository<CartItem, Long> {

	CartItem findByUserAndProduct(User user, Food product);

	List<CartItem> findByUser(User user);

	List<CartItem> findByUserUserId(Long uid);

}
