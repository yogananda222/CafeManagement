package com.example.demo.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.Order;

public interface OrderDao extends JpaRepository<Order, Long> 
{
	
	public List<Order> findByOrderId(long orderId);
	
}