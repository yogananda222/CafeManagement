package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.foodDao;
import com.example.demo.entity.Food;
import com.example.demo.service.FoodService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/food")
public class FoodController 
{
	@Autowired
	FoodService service;
	@GetMapping("/list")
	public ResponseEntity<List<Food>>findAll()
	{
		return new 	ResponseEntity<List<Food>>(this.service.findAll(), HttpStatus.OK);	
	}
	@Autowired
	foodDao dao;
	@PostMapping("/addfood")
	public ResponseEntity<Map<String,String>>saveproduct(@RequestBody Food p)
	{
		try
		{
			Optional<Food>existingproduct=this.dao.findById(p.getFoodId());
			if(existingproduct.isEmpty())
			{
				this.service.save(p);
				Map<String,String>response=new HashMap<String,String>();
				response.put("status", "success");
	            response.put("message", "Product data added!!");
	            return new ResponseEntity<Map<String,String>>(response, HttpStatus.CREATED);
	         }
			else
			{
                Map<String,String> response=new HashMap<String,String>();
                response.put("status", "failed");
                response.put("message", "Product already  found!!");
                return new ResponseEntity<Map<String,String>>(response, HttpStatus.NOT_FOUND);
			}
		}
		catch(Exception e1)
		{
			Map<String,String> response=new HashMap<String,String>();
            response.put("status", "failed");
            response.put("message", "Product not updated!!");
            return new ResponseEntity<Map<String,String>>(response, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/find/{id}")
	public ResponseEntity<?>getProductById(@PathVariable Long id)
	{
		if(this.service.findById(id).isPresent())
		{
			return new ResponseEntity<Food>(this.service.findById(id).get(),HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("Product Id not found!", HttpStatus.NOT_FOUND);
		}
	}
	@PutMapping("/update")
	public ResponseEntity<Map<String,String>> updateproduct(@RequestBody Food p)
	{
		try
		{
			if(this.dao.findById(p.getFoodId()).isPresent())
			{
				Food existingProduct=this.dao.findById(p.getFoodId()).get();
				existingProduct.setFoodName(p.getFoodName());
				
				this.service.updateproduct(p);
				Map<String,String> response=new HashMap<String,String>();
	            response.put("status", "success");
	            response.put("message", "Product data updated!!");
	            return new ResponseEntity<Map<String,String>>(response, HttpStatus.CREATED);
	            }
	            else
	            {
	                Map<String,String> response=new HashMap<String,String>();
	                response.put("status", "failed");
	                response.put("message", "Product data  not found!!");
	                return new ResponseEntity<Map<String,String>>(response, HttpStatus.NOT_FOUND);
	            }
			}
			 catch(Exception e1)
		        {
		            Map<String,String> response=new HashMap<String,String>();
		            response.put("status", "failed");
		            response.put("message", "Product not updated!!");
		            return new ResponseEntity<Map<String,String>>(response, HttpStatus.BAD_REQUEST);
		        }
		}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Map<String,String>> deleteById(@PathVariable Long id)
	{
		try
		{
			this.service.deleteById(id);
			Map<String,String> response=new HashMap<String,String>();
			response.put("status", "success");
			response.put("message", "Product data deleted!!");
			return new ResponseEntity<Map<String,String>>(response, HttpStatus.OK);
		}
		catch(Exception e)
		{
			Map<String,String> response=new HashMap<String,String>();
			response.put("status", "failed");
			response.put("message", "Product data not deleted!!");
			return new ResponseEntity<Map<String,String>>(response, HttpStatus.NOT_FOUND);
		}
	}
	
	 @GetMapping("/foodName/{foodName}")
	    public ResponseEntity<List<Food>> getMenuByFname(@PathVariable String foodName) {
	        System.out.println("Received a request to get menu items by fname: " + foodName);

	        List<Food> menuItems = service.findByfoodName(foodName);

	        if (menuItems.isEmpty()) {
	            System.out.println("No menu items found for fname: " + foodName);
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }

	        System.out.println("Returning menu items by fname: " + menuItems);
	        return new ResponseEntity<>(menuItems, HttpStatus.OK);
	    }

}