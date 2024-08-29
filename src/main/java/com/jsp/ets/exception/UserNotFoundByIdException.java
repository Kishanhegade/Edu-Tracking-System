package com.jsp.ets.user;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserNotFoundByIdException extends RuntimeException {
	private String message;
	
	@Override
	public String getMessage() {
		return this.message;
	}

}
