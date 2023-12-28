package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "admin")
public class Admins 
{
	 @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column(name = "adminId")
     private int adminId;
	

	 @Size(min = 2, max = 30, message = "Admin name must be between 2 and 30 characters")
     @Column(name = "adminName")
     private String adminName;
	 

     @Size(min = 10, max = 11, message = "Admin phone must be between 10 and 11 digits")
     @Column(name = "adminPhoneNo")
	 private String adminPhoneNo;

	 @Size(min = 2, max = 20, message = "Username must be between 2 and 20 characters")
     @Column(name = "username")
     private String username;
	 
	
	 @Size(min = 6, max = 6, message = "Admin password should be 6 characters")
	 @Column(name = "adminpassword", length=6)
	 private String adminpassword;
	 @NotBlank(message = "Email cannot be blank")
	    @Email(message = "Invalid email format")
	    @Column(name = "email")
	    private String email;

	 public Admins()
	 {
		 
	 }
	 
   	 public Admins(int adminId, String adminName, String adminPhoneNo, String username, String adminpassword, String email) 
   	 {
	   super();
	   this.adminId = adminId;
	   this.adminName = adminName;
	   this.adminPhoneNo = adminPhoneNo;
	   this.username = username;
	   this.adminpassword = adminpassword;	
	   this.email=email;
   	 }
 	public int getAdminId() {
		return adminId;
	}
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public String getAdminPhoneNo() {
		return adminPhoneNo;
	}
	public void setAdminPhoneNo(String adminPhoneNo) {
		this.adminPhoneNo = adminPhoneNo;
	}
	public String getusername() {
		return username;
	}
	public void setusername(String username) {
		this.username = username;
	}

	public String getAdminpassword() {
		return adminpassword;
	}

	public void setAdminpassword(String adminpassword) {
		this.adminpassword = adminpassword;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}