package com.example.demo.service;

import java.util.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Payment;

import com.example.demo.entity.User;

import com.example.demo.dao.OrderDao;
import com.example.demo.dao.PaymentDao;

@Service
public class PaymentServiceImpl implements PaymentService {


	@Autowired
	private PaymentDao paymentRepository;

	@Autowired
	private OrderDao orderRepository;

	@Autowired
	private UserService customerService;

	public PaymentServiceImpl(PaymentDao paymentRepository, UserService customerService) {
		super();
		this.paymentRepository = paymentRepository;

		this.customerService = customerService;
	}

	@Override
	public Payment addPayment(Payment payment, long orderId, long customerId) {
		@SuppressWarnings("deprecation")
		com.example.demo.entity.Order order = orderRepository.getById(orderId);
		payment.setOrderId(orderId);
		payment.setTotalPrice(order.getTotalPrice());
		payment.setPaidDate(new Date());
		payment.setPaidAmount(order.getTotalPrice());

		if (payment.getTotalPrice() == payment.getPaidAmount()) {
			order.setStatus("Paid");
		} else {

			order.setStatus("Not Paid");
		}
		User customer = customerService.findUserById(customerId).orElse(null);
		payment.setUser(customer);
		return paymentRepository.save(payment);
	}

	@Override
	public List<Payment> getAllPayments() {
		return paymentRepository.findAll();
	}

	@Override
	public void deletePayment(long paymentId) {
		paymentRepository.findById(paymentId);
		if (paymentRepository.existsById(paymentId)) {
			paymentRepository.deleteById(paymentId);
		}
	}

	@Override
	public Optional<Payment> findPaymentById(long id) {
		return this.paymentRepository.findById(id);
	}

	@Override
	public List<Payment> getPaymentsByOrderId(long orderId) {
		return paymentRepository.findByOrderId(orderId);
	}

	@Override
	public void deletePaymentsByOrderId(long orderId) {
		paymentRepository.findByOrderId(orderId).forEach(payment -> paymentRepository.delete(payment));

	}

	
	@SuppressWarnings("deprecation")
	@Override
	public Payment addCashPayment(Payment payment, long orderId ){
		com.example.demo.entity.Order order = orderRepository.getById(orderId);
		User customer = customerService.findUserById(order.getUser().getUserId()).orElse(null);
		payment.setOrderId(orderId);
		payment.setUser(customer);
		payment.setTotalPrice(order.getTotalPrice());
		payment.setPaidDate( new Date());
		payment.setPaidAmount(order.getTotalPrice());
		order.setStatus("Paid");
		return paymentRepository.save(payment);
	}

	@Override
	public List<Payment> findPaymentsByUserId(long customerId) {
		// TODO Auto-generated method stub
		return null;
	}

}
