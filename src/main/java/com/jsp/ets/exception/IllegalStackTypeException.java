package com.jsp.ets.exception;

public class IllegalStackTypeException extends RuntimeException{

	private String message;
	
	public IllegalStackTypeException(String message) {
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return this.message;
	}
}
