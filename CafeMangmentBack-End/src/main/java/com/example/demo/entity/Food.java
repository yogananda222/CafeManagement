package com.example.demo.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="Food_items")
public class Food 
{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="foodId")
	private long foodId;
	
	
	@Size(max = 400,message = "Product name can't be more than 400 characters")
	@Size(min=2, message="Product name must be more than 2 characters")
	@Column(name="food_name")
	private String foodName;
	
	@Column(name="foodPrice")
	private float foodPrice;
	
	    @Size(max = 255, message = "Image URL must not exceed 255 characters")
	    @Column(name = "img", nullable = false)
	    private String img;
	
	public Food()
	{
		
	}

	public Food(long foodId, String foodName,float foodPrice,String img) {
		super();
		this.foodId = foodId;
		this.foodName = foodName;
		this.foodPrice =foodPrice;
		this.img = img;
	}
	
	


	public long getFoodId() {
		return foodId;
	}

	public void setFoodId(int foodId) {
		this.foodId = foodId;
	}

	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	public float getFoodPrice() {
		return foodPrice;
	}

	public void setFoodPrice(float foodPrice) {
		this.foodPrice = foodPrice;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}


	
	}