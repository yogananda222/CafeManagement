package com.example.demo.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name="User")
public class User {

		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "user_id")
	    private long userId;

	   
	    @Size(min = 2, max = 50, message = "Customer name must be between 2 and 50 characters")
	    @Column(name = "user_name")
	    private String username;

	   
	    @Pattern(regexp = "[0-9]+", message = "Customer phone must contain only digits")
	    @Size(min = 10, max = 12, message = "Customer phone must be between 10 and 12 digits")
	    @Column(name = "user_phone")
	    private String userPhone;



	    @Size(min = 4, message = "User password must be at least 4 characters")
	    @Column(name = "user_password")
	    private String userpassword;

	 
	    @Email(message = "Invalid email format")
	    @Column(name = "email")
	    private String email;

		public User() {
		}

		public User( String username, String userPhone, String userpassword , String email) {
			super();
			this.username = username;
			this.userPhone = userPhone;
			this.userpassword = userpassword;
			this.email= email;
		}

		public long getUserId() {
			return userId;
		}

		public void setUserId(long userId) {
			this.userId = userId;
		}

		
		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getUserPhone() {
			return userPhone;
		}

		public void setUserPhone(String userPhone) {
			this.userPhone = userPhone;
		}

		public String getUserpassword() {
			return userpassword;
		}

		public void setUserpassword(String userpassword) {
			this.userpassword = userpassword;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		
}

	