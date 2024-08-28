package com.jsp.ets.user;

import java.time.Year;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentResponse extends UserResponse{
	
	private String degree;
	private String stream;
	private Year yop;
	private Double degreePercentage;
	private Double twelfthPercentage;
	private Double tenthPercentage;


}
