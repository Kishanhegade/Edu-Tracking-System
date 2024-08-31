package com.jsp.ets.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jsp.ets.exception.RatingNotFoundByIdException;
import com.jsp.ets.utility.AppResponseBuilder;
import com.jsp.ets.utility.ErrorStructure;

@RestControllerAdvice
public class RatingExceptionHandler {
private AppResponseBuilder builder;
	
	@ExceptionHandler(RatingNotFoundByIdException.class)
	public ResponseEntity<ErrorStructure<String>> handleRatingNotFoundById(RatingNotFoundByIdException ex) {
		return builder.error(HttpStatus.NOT_FOUND, ex.getMessage(), "Rating not found by given Id");
	}

}
