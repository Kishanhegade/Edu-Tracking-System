package com.jsp.ets.exception;

public class InvalidStackValueException extends RuntimeException{

	private String message;

	public InvalidStackValueException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return this.message;
	}
}
