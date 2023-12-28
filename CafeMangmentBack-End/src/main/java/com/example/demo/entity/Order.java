package com.example.demo.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;


@Entity
@Table(name="Orders")
public class Order {
	  
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  @Column(name = "order_id")
	  private long orderId;
	
	@ManyToOne(targetEntity = User.class, cascade = { CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_Id", referencedColumnName = "user_id")
    private User user;
	  @Column(name = "order_date")
	  @Temporal(TemporalType.DATE)
	  private Date orderDate;
	  

	  @Column(name = "order_time")
	  @Temporal(TemporalType.TIME)
      private Date orderTime;
	  
	  @ManyToOne(targetEntity = Food.class, cascade = { CascadeType.MERGE }, fetch = FetchType.EAGER)
	  @JoinColumn(name = "FoodId", referencedColumnName = "FoodId")
	  private Food food;
	
	  @Column(name = "quantity")
	  private int quantity;
	  
	  @Column(name = "total_price")
	  private int totalPrice;
	
	
	
	 @Column(name = "status")
     private String status;

	public Order()
	{
		
	}

	public Order(long orderId, User user, Date orderDate, Date orderTime, Food food, int quantity, String status, long userId)
	{
		super();
		this.orderId = orderId;
		this.user =user;
		this.orderDate = orderDate;
		this.orderTime = orderTime;
		this.food = food;
		this.quantity = quantity;
		this.status = status;
		calculateTotalPrice(); 
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public Date getOrderDate() {
		return orderDate;
	}
	
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Food getFood() {
		return food;
	}
	public void setFood(Food food) {
		this.food = food;
	}
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getTotalPrice() {
		return this.totalPrice = (int) (food.getFoodPrice() * quantity);
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		
		this.status = status;
	}
	
	// Calculate total price based on menu item price and quantity
		public void calculateTotalPrice() {
			if (food != null) {
				this.totalPrice = (int) (food.getFoodPrice() * quantity);
			} else {
				this.totalPrice = 0; 
			}
		}

}	