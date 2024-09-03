package com.jsp.ets.exception;

public class InvalidStackValueException extends RuntimeException{

	private final String message;

	public InvalidStackValueException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return this.message;
	}
}
