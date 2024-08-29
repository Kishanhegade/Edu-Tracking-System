package com.jsp.ets.mapper;

import com.jsp.ets.rating.Rating;
import com.jsp.ets.rating.RatingRequest;

public class RatingMapper {
	
	public Rating mapToRatingEntity(RatingRequest ratingRequest, Rating rating) {
		rating.setRating(ratingRequest.getRating());
		rating.setSubject(ratingRequest.getSubject());
		rating.setFeedback(ratingRequest.getFeedback());
		
	}

}
