package com.jsp.ets.utility;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseStructure<T> {
	
	private int status;
	private String message;
	private T data;
	
	public static <T> ResponseStructure<T> create(int status, String message, T data) {
		ResponseStructure<T> responseStructure = new ResponseStructure<T>();
		responseStructure.setStatus(status);
		responseStructure.setMessage(message);
		responseStructure.setData(data);
		return responseStructure;
	}

}
