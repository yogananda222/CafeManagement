package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.CartItem;

public interface CartService {
	public CartItem addToCart(Long uid, Long pid, int quantity);

	public void removeFromCart(Long uid, Long pid);

	public void updateQuantity(Long uid, Long pid, int quantity);

	public List<CartItem> getCartItems(Long uid);
	
	public void deleteCart(Long uid);
}