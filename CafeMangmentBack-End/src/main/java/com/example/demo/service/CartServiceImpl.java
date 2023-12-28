package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.dao.CartDao;
import com.example.demo.entity.CartItem;
import com.example.demo.entity.Food;
import com.example.demo.entity.User;
import com.example.demo.exception.ProductNotFoundException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.exception.UsersNotFoundException;

@Service
public class CartServiceImpl implements CartService {
	@Autowired
	private CartDao cartItemRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private FoodService productService;

	//Adding Items to Cart
	public CartItem addToCart(Long uid, Long pid, int quantity) {
	    try {
	        User user = userService.findUserById(uid).orElseThrow(() -> new UserNotFoundException(uid));
	        Food product = productService.findById(pid)
	                .orElseThrow(() -> new ProductNotFoundException(pid));
	        
	        CartItem existingCartItem = cartItemRepository.findByUserAndProduct(user, product);

	        if (existingCartItem != null) {
	            existingCartItem.setQuantity(existingCartItem.getQuantity() + quantity);
	            return cartItemRepository.save(existingCartItem);
	        } else {
	            CartItem newCartItem = new CartItem();
	            newCartItem.setUser(user);
	            newCartItem.setProduct(product);
	            newCartItem.setQuantity(quantity);
	            newCartItem.calculateTotalPrice();
	            return cartItemRepository.save(newCartItem);
	        }
	    } catch (UserNotFoundException ex1) {
	        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found", ex1);
	    } catch (ProductNotFoundException ex2) {
	        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product not found", ex2);
	    }
	}
	
	// Remove Products from Cart by Product Id
	@Override
	public void removeFromCart(Long uid, Long pid) {
		User user = userService.findUserById(uid).orElse(null);
		if (user == null) {
			throw new IllegalArgumentException("Invalid customer ID");
		}

		Food product = productService.getUserByFoodId(pid).orElse(null);
		if (product == null) {
			throw new IllegalArgumentException("Invalid menu item ID");
		}

		CartItem existingCartItem = cartItemRepository.findByUserAndProduct(user, product);

		if (existingCartItem != null) {
			cartItemRepository.delete(existingCartItem);
		}
	}

	//Update Quantity of Cart
	@Override
	public void updateQuantity(Long uid, Long pid, int quantity) {
	    User user = userService.findUserById(uid).orElse(null);
	    if (user == null) {
	        throw new IllegalArgumentException("Invalid customer ID");
	    }

	    Food product = productService.getUserByFoodId(pid).orElse(null);
	    if (product == null) {
	        throw new IllegalArgumentException("Invalid menu item ID");
	    }
	    CartItem existingCartItem = cartItemRepository.findByUserAndProduct(user, product);

	    if (existingCartItem != null) {
	        existingCartItem.setQuantity(quantity);
	        cartItemRepository.save(existingCartItem);
	    }
	}


	//Get the Cart
	@Override
	public List<CartItem> getCartItems(Long uid) {
		User user = userService.findUserById(uid).orElse(null);
		if (user == null) {
			throw new UsersNotFoundException(uid);
		}

		List<CartItem> existingCartItem = cartItemRepository.findByUserUserId(uid);
		return existingCartItem;
	}
	
	
	//Delete whole Cart 
	@Override
	public void deleteCart(Long uid) {
		try {
			List<CartItem> cartItemsToDelete = cartItemRepository.findByUserUserId(uid);

			if (!cartItemsToDelete.isEmpty()) {
				cartItemRepository.deleteAll(cartItemsToDelete);
			}
		} catch (Exception ex) {
			throw new UsersNotFoundException(uid);
		}
	}



	
}