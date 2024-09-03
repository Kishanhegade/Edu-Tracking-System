package com.jsp.ets.mapper;

import org.springframework.stereotype.Component;
import com.jsp.ets.rating.Rating;
import com.jsp.ets.rating.RatingRequest;
import com.jsp.ets.rating.RatingResponse;
@Component

public class RatingMapper {
	
	public Rating mapToRatingEntity(RatingRequest ratingRequest, Rating rating) {
		rating.setRatings(ratingRequest.getRatings());
		rating.setFeedback(ratingRequest.getFeedback());
		return rating;
	}
	
	public RatingResponse mapToRatingResponse(Rating rating) {

		RatingResponse ratingResponse = new RatingResponse();
		ratingResponse.setRatings(rating.getRatings());
		ratingResponse.setFeedback(rating.getFeedback());
		ratingResponse.setRatingId(rating.getRatingId());
		ratingResponse.setSubject(rating.getSubject());
		return ratingResponse;
	}

}
