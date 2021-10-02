package com.example.exception;

@SuppressWarnings("serial")
public class NotAnEmployeeException extends RuntimeException{

	public NotAnEmployeeException(String message) {
		super(message);
	}
	
}
