package com.jsp.ets.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class InvalidTokenException extends RuntimeException{
    private final String message;

    @Override
    public String getMessage() {
        return message;
    }
}
