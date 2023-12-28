package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Order;
import com.example.demo.exception.TakeOrderNotFoundException;
import com.example.demo.service.OrderService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/orders")
public class OrderController {
  
	private  OrderService takeOrderService;
	
	
	 public OrderController(OrderService takeOrderService)
	 {
		this.takeOrderService=takeOrderService;
	 }
	
	@GetMapping("/vieworders")
	public ResponseEntity<List<Order>>getAllTakeOrders() 
	{
		List<Order> takeorders = takeOrderService.getAllTakeOrders();
		return new ResponseEntity<>(takeorders, HttpStatus.OK);
	}
	@GetMapping("/{orderId}")
	public ResponseEntity<Order>getOrderById(@PathVariable Long orderId)
	{
		Order takeOrder=takeOrderService.getOrderById(orderId);
		if (takeOrder == null)
		{
			throw new TakeOrderNotFoundException("Order with ID " + orderId + " not found.");
		}
		return new ResponseEntity<>(takeOrder,HttpStatus.OK);	
	}
	
	@PostMapping("/create/{productId}")
	public ResponseEntity<Order> createTakeOrder(@RequestBody Order takeOrder,@PathVariable long productId)
	{
		Order savedTakeOrder = takeOrderService.saveOrder(takeOrder,  productId);
		return ResponseEntity.ok(savedTakeOrder);		
	}
	@DeleteMapping("/delete/{orderId}")
	public ResponseEntity<Void> deleteOrder(@PathVariable long orderId)
	{
		takeOrderService.deleteOrder(orderId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<?>getTakeOrdersByCustomerId(@PathVariable long userId)
	{
		try
		{
			java.util.Optional<Order> takeOrders = takeOrderService.findTakeOrdersByCustomerId(userId);
			if(takeOrders.isEmpty())
			{
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();//no order founds
			}
			return ResponseEntity.ok(takeOrders);
		}
		catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An error occurred: " + e.getMessage());
		}
	}
	
}