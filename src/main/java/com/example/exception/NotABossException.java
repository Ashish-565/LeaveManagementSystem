package com.example.exception;

@SuppressWarnings("serial")
public class NotABossException extends RuntimeException{

	public NotABossException(String message) {
		super(message);
	}
	
}
