package com.example.demo.exception;

public class BillNotFoundException extends RuntimeException {

	public BillNotFoundException(String message)
	{
		super(message);
	}
}