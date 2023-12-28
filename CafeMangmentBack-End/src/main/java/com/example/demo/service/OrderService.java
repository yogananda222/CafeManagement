package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Order;

public interface OrderService 
{

	public List<Order>getAllTakeOrders();

	public Order getOrderById(long orderId);

	//public TakeOrder saveOrder(TakeOrder order,long customerId,int productId);

	public void deleteOrder(long orderId);
	


	public Order saveOrder(Order order, Long productId);

	public Optional<Order> findTakeOrdersByCustomerId(long customerId);
	
	
}