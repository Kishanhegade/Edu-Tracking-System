package com.jsp.ets.rating;

import java.util.List;
import org.springframework.stereotype.Service;
import com.jsp.ets.exception.RatingNotFoundByIdException;
import com.jsp.ets.exception.UserNotFoundByIdException;
import com.jsp.ets.mapper.RatingMapper;
import com.jsp.ets.user.Student;
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
		return userRepo.findById(userId)
				.map(user -> {
					Student student = (Student) user;
					return student.getRatings().stream()
							.map(ratingMapper::mapToRatingResponse)
							.toList();
				})
				.orElseThrow(() -> new UserNotFoundByIdException("Failed to fetch ratings"));

	}

}
