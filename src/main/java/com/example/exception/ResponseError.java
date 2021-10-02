package com.example.exception;

import lombok.Data;

@Data
public class ResponseError {
	 private boolean error;
	 private String message;
}
