package com.jsp.ets.rating;

import com.jsp.ets.user.Subject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RatingResponse {

	private String ratingId;
	private String feedback;
	private Integer rating;
	private Subject subject;
}
