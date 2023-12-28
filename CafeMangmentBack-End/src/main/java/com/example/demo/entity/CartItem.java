package com.example.demo.entity;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name="CartItems")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @ManyToOne(targetEntity = User.class, cascade = { CascadeType.MERGE }, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;


    @ManyToOne(targetEntity = Food.class, cascade = { CascadeType.MERGE }, fetch = FetchType.EAGER)
    @JoinColumn(name = "foodId", referencedColumnName = "foodId")
    private Food product;

    @Min(value = 1, message = "Quantity must be at least 1")
    @Column(name = "quantity")
    private int quantity;

    @Min(value = 0, message = "Total price cannot be negative")
    @Column(name = "total_price")
    private double totalPrice;

	public CartItem() {
	}

	public CartItem(Long id, User user, Food product, int quantity) {
		super();
		this.id = id;
		this.user = user;
		this.product = product;
		this.quantity = quantity;
		calculateTotalPrice();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Food getProduct() {
		return product;
	}

	public void setProduct(Food product) {
		this.product = product;
		calculateTotalPrice();
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
		calculateTotalPrice();
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public void calculateTotalPrice() {
		if (product != null) {
			totalPrice = product.getFoodPrice() * quantity;
		} else {
			totalPrice = 0.0;
		}
	}

	@Override
	public String toString() {
		return "CartItem [id=" + id + ", user=" + user + ", product=" + product + ", quantity=" + quantity + "]";
	}
}
