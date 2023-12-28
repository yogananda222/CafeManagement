package com.example.demo.exception;
public class ProductsNotFoundException extends RuntimeException {
    public ProductsNotFoundException(Long orderId) {
        super("Product not found of the Order "+orderId);
        System.out.println("Product not found of the Order "+orderId);
        
    }
}