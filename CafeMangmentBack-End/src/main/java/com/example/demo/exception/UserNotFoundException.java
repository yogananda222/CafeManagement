package com.example.demo.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String uid) {
        super(uid);
    }

	public UserNotFoundException(Long uid) {
		System.out.println("User Id Not FOund");
	}
}