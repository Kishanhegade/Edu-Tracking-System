package com.jsp.ets.rating;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jsp.ets.exception.RatingNotFoundByIdException;
import com.jsp.ets.exception.UserNotFoundByIdException;
import com.jsp.ets.mapper.RatingMapper;
import com.jsp.ets.user.Student;
import com.jsp.ets.user.User;
import com.jsp.ets.user.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RatingService {

	private RatingRepository ratingRepo;
	private RatingMapper ratingMapper;
	private UserRepository userRepo;
	
	public RatingResponse updateRating(String ratingId, RatingRequest ratingRequest) {
		return ratingRepo.findById(ratingId).map(rating->{
			ratingMapper.mapToRatingEntity(ratingRequest, rating);
			rating=ratingRepo.save(rating);
			return ratingMapper.mapToRatingResponse(rating);
		}).orElseThrow(()->new RatingNotFoundByIdException("Failed to update rating"));
	}

	public List<RatingResponse> findAllRatings(String userId) {
		Optional<User> optional = userRepo.findById(userId);
		if(optional.isPresent()) {
			Student student = (Student) optional.get();
			List<Rating> ratings = student.getRatings();
			List<RatingResponse> responses = new ArrayList<RatingResponse>();
			for(Rating rating:ratings) {
				responses.add(ratingMapper.mapToRatingResponse(rating));
			}
			return responses;
		}
		else {
			throw new UserNotFoundByIdException("failed to fetch ratings");
		}
		
	}

}
