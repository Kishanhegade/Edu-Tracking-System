package com.jsp.ets.mapper;

import com.jsp.ets.rating.Rating;
import com.jsp.ets.rating.RatingRequest;
import com.jsp.ets.rating.RatingResponse;

public class RatingMapper {
	
	public Rating mapToRatingEntity(RatingRequest ratingRequest, Rating rating) {
		rating.setRating(ratingRequest.getRating());
		rating.setFeedback(ratingRequest.getFeedback());
		return rating;
	}
	
	public RatingResponse mapToRatingResponse(Rating rating) {

		RatingResponse ratingResponse = new RatingResponse();
		ratingResponse.setRating(rating.getRating());
		ratingResponse.setFeedback(rating.getFeedback());
		ratingResponse.setRatingId(rating.getRatingId());
		ratingResponse.setSubject(rating.getSubject());
		return ratingResponse;
	}

}
