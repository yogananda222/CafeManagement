package com.example.demo.exception;
public class UsersNotFoundException extends RuntimeException {
    public UsersNotFoundException(long userId) {
        super("User not found with ID: " + userId);
        System.out.println("User not found with ID: " + userId);
    }
}
