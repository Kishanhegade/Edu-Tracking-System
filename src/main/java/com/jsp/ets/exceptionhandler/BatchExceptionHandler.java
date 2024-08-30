package com.jsp.ets.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jsp.ets.exception.BatchNotFoundByIdException;
import com.jsp.ets.utility.AppResponseBuilder;
import com.jsp.ets.utility.ErrorStructure;

@RestControllerAdvice
public class BatchExceptionHandler {
	
private AppResponseBuilder builder;
	
	@ExceptionHandler(BatchNotFoundByIdException.class)
	public ResponseEntity<ErrorStructure<String>> handleRatingNotFoundById(BatchNotFoundByIdException ex) {
		return builder.error(HttpStatus.NOT_FOUND, ex.getMessage(), "Batch not found by given Id");
	}

}
