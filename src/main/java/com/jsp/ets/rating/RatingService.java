package com.jsp.ets.rating;

import org.springframework.stereotype.Service;

import com.jsp.ets.exception.RatingNotFoundByIdException;
import com.jsp.ets.mapper.RatingMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RatingService {

	private RatingRepository ratingRepo;
	private RatingMapper ratingMapper;
	
	public RatingResponse updateRating(String ratingId, RatingRequest ratingRequest) {
		return ratingRepo.findById(ratingId).map(rating->{
			ratingMapper.mapToRatingEntity(ratingRequest, rating);
			rating=ratingRepo.save(rating);
			return ratingMapper.mapToRatingResponse(rating);
		}).orElseThrow(()->new RatingNotFoundByIdException("Failed to update rating"));
	}

}
