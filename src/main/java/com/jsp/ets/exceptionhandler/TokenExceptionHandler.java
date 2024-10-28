package com.jsp.ets.exceptionhandler;


import com.jsp.ets.exception.InvalidTokenException;
import com.jsp.ets.utility.AppResponseBuilder;
import com.jsp.ets.utility.ErrorStructure;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@AllArgsConstructor
@RestControllerAdvice
public class TokenExceptionHandler {
    private final AppResponseBuilder responseBuilder;

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<ErrorStructure<String>> handleBatchNotFoundById(InvalidTokenException exception){
        return responseBuilder.error(HttpStatus.NOT_FOUND, exception.getMessage(), "token is invalid");
    }
}
