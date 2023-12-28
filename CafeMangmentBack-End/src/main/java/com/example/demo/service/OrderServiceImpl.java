package com.example.demo.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.OrderDao;
import com.example.demo.entity.Food;
import com.example.demo.entity.Order;

@Service
public class OrderServiceImpl implements OrderService {


	private  OrderDao takeOrderRepository;

	public OrderServiceImpl(OrderDao takeOrderRepository) {
		this.takeOrderRepository = takeOrderRepository;
	}
	
	@Autowired
	UserService customerservice;
	@Autowired
	FoodService productservice;
	
	@Override
	public List<Order> getAllTakeOrders() {
		// TODO Auto-generated method stub
		return takeOrderRepository.findAll();
	}

	@Override
	public Order getOrderById(long orderId) {
		// TODO Auto-generated method stub
		return takeOrderRepository.findById(orderId).orElse(null);
	}

	@Override
	public Order saveOrder(Order order, Long productId) {
		Food product = productservice.findById(productId).orElse(null);
		if (product == null) 
		{
		 throw new IllegalArgumentException("Invalid Product product ID");
		}
		order.setOrderDate(new Date()); // Set the current date as the order date
		order.setOrderTime(new Date()); // Set the current time as the order time
		// Calculate total price
		order.calculateTotalPrice();
		// Set initial order status
		order.setStatus("pending");
		
//		order.setCustomer(customer);
		order.setFood(product);
		
		// Set total price in the entity to be saved in the database
		order.setTotalPrice(order.getTotalPrice());

		// Save the order
		return takeOrderRepository.save(order);
	}

	@Override
	public void deleteOrder(long orderId) {
		// TODO Auto-generated method stub
		takeOrderRepository.deleteById(orderId);
	}

	@Override
	public Optional<Order> findTakeOrdersByCustomerId(long customerId) {
		// TODO Auto-generated method stub
		return this.takeOrderRepository.findById(customerId);
	}

	
}