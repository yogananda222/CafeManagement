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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.service.UserService;
import com.example.demo.service.EmailService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserService service;
	@Autowired
	UserDao dao;
	@Autowired
	EmailService emailservice;
	@PostMapping("/signup")
	public ResponseEntity<Map<String, String>> singup(@RequestBody User user) {
		this.service.addUser(user);
		Map<String, String> response = new HashMap<String, String>();
		response.put("status", "success");
		response.put("message", "User registered!!");
		emailservice.sendEmail(user.getEmail(), "SignUp Email", "Welcome to family as \nYour username is :"+user.getUsername()+"\n Password is :" + user.getUserpassword());
		return new ResponseEntity<Map<String, String>>(response, HttpStatus.CREATED);
	}

	@GetMapping("/list")
	public ResponseEntity<List<User>> findAll() {
		return new ResponseEntity<List<User>>(this.service.findAll(), HttpStatus.OK);
	}

	@GetMapping("/id/{id}")
	public ResponseEntity<User> getCustomerById(@PathVariable long id) {
		Optional<User> customerOptional = dao.findById(id);

		if (customerOptional.isPresent()) {
			return ResponseEntity.ok(customerOptional.get());
		} else {
			throw new UserNotFoundException("Customer with Id " + id + " not found.");
		}
	}

	@GetMapping("/username/{username}")
	public ResponseEntity<User> getCustomerByUsername(@PathVariable String username) {
		User customer = service.findByUsername(username);
		if (customer == null) {
			throw new UserNotFoundException("Customer with username " + username + " not found.");
		}
		return new ResponseEntity<>(customer, HttpStatus.OK);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Map<String, String>> updateUser(@RequestBody User user) {
		try {
			if (this.dao.findById(user.getUserId()).isPresent()) {
				User existingCus = this.dao.findById(user.getUserId()).get();
				existingCus.setUsername(user.getUsername());
				existingCus.setUserPhone(user.getUserPhone());
				existingCus.setUsername(user.getUsername());
				existingCus.setUserpassword(user.getUserpassword());
				existingCus.setEmail(user.getEmail());
				this.dao.save(existingCus);

				Map<String, String> response = new HashMap<String, String>();
				response.put("status", "success");
				response.put("message", "User data updated!!");
				return new ResponseEntity<Map<String, String>>(response, HttpStatus.CREATED);
			} else {
				Map<String, String> response = new HashMap<String, String>();
				response.put("status", "failed");
				response.put("message", "User data not found!!");
				return new ResponseEntity<Map<String, String>>(response, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e1) {
			Map<String, String> response = new HashMap<String, String>();
			response.put("status", "failed");
			response.put("message", "User data not updated!!");
			return new ResponseEntity<Map<String, String>>(response, HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Map<String, String>> deleteUser(@PathVariable(name = "id") int id) {
		try {
			this.service.deleteById(id);
			Map<String, String> response = new HashMap<String, String>();
			response.put("status", "success");
			response.put("message", "user  data deleted!!");
			return new ResponseEntity<Map<String, String>>(response, HttpStatus.OK);
		} catch (Exception e) {
			Map<String, String> response = new HashMap<String, String>();
			response.put("status", "failed");
			response.put("message", "Employee data not deleted!!");
			return new ResponseEntity<Map<String, String>>(response, HttpStatus.NOT_FOUND);
		}
	}



	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody User customerData) {
		User customer = service.findByUsername(customerData.getUsername());
		if (customer.getUserpassword().equals(customerData.getUserpassword())) {
			User sendcustomer = new User();
			sendcustomer.setUserId(customer.getUserId());
			sendcustomer.setUserPhone(customer.getUserPhone());
			sendcustomer.setUsername(customer.getUsername());
			sendcustomer.setUserpassword(customer.getUserpassword());
			sendcustomer.setEmail(customer.getEmail());

			return ResponseEntity.ok(sendcustomer);
		} else {
			return (ResponseEntity<?>) ResponseEntity.internalServerError();
		}
	}

}