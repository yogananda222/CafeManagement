package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.CartItem;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.service.CartService;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/cart")
public class CartController {
	@Autowired
	private CartService cartService;

	//Add Product to Cart
	@PostMapping("/add/{userId}/{productId}/{quantity}")
	public ResponseEntity<?> addToCart(@PathVariable Long userId, @PathVariable Long productId,
			@PathVariable int quantity) {
		try {
			CartItem cart = cartService.addToCart(userId, productId, quantity);
			return ResponseEntity.ok(cart);
		} catch (IllegalArgumentException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid input: " + ex.getMessage());
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An internal server error occurred.");
		}
	}

	//Delete Product From Cart
	@DeleteMapping("/remove/{userId}/{productId}")
	public ResponseEntity<Map<String, String>> removeFromCart(@PathVariable Long userId, @PathVariable Long productId) {

		cartService.removeFromCart(userId, productId);
		Map<String, String> response = new HashMap<>();
		response.put("status", "success");
		response.put("message", "User data deleted!!");
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	//Update The quantity of Product in Cart
	@PutMapping("/update/{userId}/{productId}/{quantity}")
	public ResponseEntity<Map<String, String>> updateCartItem(@PathVariable Long userId, @PathVariable Long productId,
			@PathVariable int quantity) {
		try {
			cartService.updateQuantity(userId, productId, quantity);
			Map<String, String> response = new HashMap<>();
			response.put("status", "success");
			response.put("message", "Quantity Updated!!");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (UserNotFoundException e) {

			Map<String, String> errorResponse = new HashMap<>();
			errorResponse.put("status", "failed");
			errorResponse.put("message", "User not found with ID: " );
			return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
		} catch (Exception ex) {

			Map<String, String> errorResponse = new HashMap<>();
			errorResponse.put("status", "failed");
			errorResponse.put("message", "An error occurred.");
			return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	//View Cart By User Id
	@GetMapping("/view/{userId}")
	public ResponseEntity<?> viewCart(@PathVariable Long userId) {
		try {
			List<CartItem> cartItems = cartService.getCartItems(userId);
			return ResponseEntity.ok(cartItems);
		} catch (IllegalArgumentException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid input: " + ex.getMessage());
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No Items in Cart.");
		}
	}

	//Delete All Cart
	@DeleteMapping("/cart/delete/{uid}")
	public ResponseEntity<Map<String, String>> deleteCart(@PathVariable Long uid) {
		cartService.deleteCart(uid);
		Map<String, String> response = new HashMap<>();
		response.put("status", "success");
		response.put("message", "User data deleted!!");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}