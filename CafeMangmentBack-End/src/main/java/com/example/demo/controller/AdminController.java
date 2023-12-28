package com.example.demo.controller;
import com.example.demo.service.EmailService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.example.demo.dao.AdminDao;
import com.example.demo.entity.Admins;
import com.example.demo.entity.User;
import com.example.demo.service.AdminService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/admin")
public class AdminController {
     
	@Autowired
	AdminService service;
	@Autowired
	AdminDao dao;
	
	EmailService emailservice;
	
	@GetMapping("/list")
	public ResponseEntity<List<Admins>> findAll() {
		return new ResponseEntity<List<Admins>>(this.service.findAll(),HttpStatus.OK);
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody Admins adminData)
	{
		Admins admin = service.findByUsername(adminData.getusername());

		if (admin.getAdminpassword().equals(adminData.getAdminpassword())) {
			Admins sendadmin = new Admins();
			sendadmin.setAdminId(admin.getAdminId());
			sendadmin.setAdminName(admin.getAdminName());
			sendadmin.setAdminPhoneNo(admin.getAdminPhoneNo());
			sendadmin.setusername(admin.getusername());
			
			return ResponseEntity.ok(sendadmin);
		} else {
	
			return (ResponseEntity<?>) ResponseEntity.internalServerError();
		}
	}
	
	@PutMapping("/updateadmin")
	public ResponseEntity<Map<String,String>>updateadmin(@RequestBody Admins admin)
	{
		try
		{
			if(this.dao.findById(admin.getAdminId()).isPresent())
			{
				Admins existingadmin=this.dao.findById(admin.getAdminId()).get();
				existingadmin.setAdminName(admin.getAdminName());
				existingadmin.setAdminPhoneNo(admin.getAdminPhoneNo());
				existingadmin.setusername(admin.getusername());
				existingadmin.setAdminpassword(admin.getAdminpassword());
				existingadmin.setEmail(admin.getEmail());
				this.service.addAdmin(admin);
				 Map<String,String> response=new HashMap<String,String>();
		            response.put("status", "success");
		            response.put("message", "Admin data updated!!");
		            return new ResponseEntity<Map<String,String>>(response, HttpStatus.CREATED);
			}
			else
			{
				Map<String,String> response=new HashMap<String,String>();
                response.put("status", "failed");
                response.put("message", "Customer data  not found!!");
                return new ResponseEntity<Map<String,String>>(response, HttpStatus.NOT_FOUND);
			}
		 }
		 catch(Exception e1)
		{
			 Map<String,String> response=new HashMap<String,String>();
	         response.put("status", "failed");
	         response.put("message", "Admin not updated!!");
	         return new ResponseEntity<Map<String,String>>(response, HttpStatus.BAD_REQUEST);
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
}