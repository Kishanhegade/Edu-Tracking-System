package com.jsp.ets.rating;


import com.jsp.ets.user.Subject;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class RatingRequest {
	
	private Subject subject;
	private Integer rating;
	private String feedback;
	

}
