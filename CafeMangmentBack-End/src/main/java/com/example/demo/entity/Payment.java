package com.example.demo.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
	

@Entity
@Table(name = "payment")
@SequenceGenerator(name = "generator5", sequenceName = "gen5", initialValue = 20000)
public class Payment {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator5")
	@Column(name = "payment_id")
	private long paymentId;

	@Column(name = "total_price")
	private double totalPrice;

	@Column(name = "order_id", unique = true)
	private long orderId;

	@Column(name = "paid_date")
	private Date PaidDate;

	@Column(name = "paid_amount", nullable = false)
	private double paidAmount;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
	private User user;

	public Payment() {
	}

	public Payment(long paymentId, double totalPrice, long orderId, Date PaidDate, double paidAmount,
			User user) {
		super();
		this.paymentId = paymentId;
		this.totalPrice = totalPrice;
		this.orderId = orderId;
		this.PaidDate = PaidDate;
		this.paidAmount = paidAmount;
		
		this.user = user;
	}

	public long getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(long paymentId) {
		this.paymentId = paymentId;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public Date getPaidDate() {
		return PaidDate;
	}

	public void setPaidDate(Date paidDate) {
		PaidDate = paidDate;
	}

	public double getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(double paidAmount) {
		this.paidAmount = paidAmount;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Payment [paymentId=" + paymentId + ", totalPrice=" + totalPrice + ", orderId=" + orderId + ", PaidDate="
				+ PaidDate + ", paidAmount=" + paidAmount +", customer=" + user
				+ "]";
	}

}