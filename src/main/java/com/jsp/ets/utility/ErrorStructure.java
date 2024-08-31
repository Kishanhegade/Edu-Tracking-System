package com.jsp.ets.utility;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorStructure<T> {
	private int status;
	private String message;
	private T rootCause;

	public static <T> ErrorStructure<T> create(int status, String message, T rootCause) {
		ErrorStructure<T> error = new ErrorStructure<T>();
		error.setStatus(status);
		error.setMessage(message);
		error.setRootCause(rootCause);
		return error;
	}
}
