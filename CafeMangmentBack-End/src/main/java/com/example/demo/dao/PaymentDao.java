package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Payment;

@Repository
public interface PaymentDao extends JpaRepository<Payment, Long> {
		public List<Payment> findByOrderId(long orderId); //Admin
	//	public List<Payment> findByCustomerCustomerId(long customerId);
		
}