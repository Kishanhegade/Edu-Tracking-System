package com.jsp.ets.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jsp.ets.user.UserNotFoundByIdException;
import com.jsp.ets.utility.AppResponseBuilder;
import com.jsp.ets.utility.ErrorStructure;

import lombok.AllArgsConstructor;

@RestControllerAdvice
@AllArgsConstructor
public class UserExceptionHandler {
	
	private AppResponseBuilder builder;
	
	@ExceptionHandler(UserNotFoundByIdException.class)
	public ResponseEntity<ErrorStructure<String>> handleUserNotFoundById(UserNotFoundByIdException ex) {
		return builder.error(HttpStatus.NOT_FOUND, ex.getMessage(), "User not found by given Id");
	}

}
