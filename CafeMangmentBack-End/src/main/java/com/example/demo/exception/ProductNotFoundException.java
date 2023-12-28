package com.example.demo.exception;
public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(Long productId) {
        super("Product not found with ID: " + productId);
        System.out.println("Product not found with ID: " + productId);
    }
}