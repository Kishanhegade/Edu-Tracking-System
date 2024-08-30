package com.jsp.ets.rating;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.ets.utility.AppResponseBuilder;
import com.jsp.ets.utility.ResponseStructure;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class RatingController {
	
	private RatingService ratingService;
	private AppResponseBuilder builder;
	
	@PutMapping("ratings/{ratingId}")
	public ResponseEntity<ResponseStructure<RatingResponse>> updateRating(@PathVariable String ratingId, @RequestBody RatingRequest ratingRequest) {
		RatingResponse ratingResponse = ratingService.updateRating(ratingId, ratingRequest);
		return builder.success(HttpStatus.OK,"Rating updated", ratingResponse);
	}

	@GetMapping("/ratings/{userId}")
	public ResponseEntity<ResponseStructure<List<RatingResponse>>> findAllRatings(@PathVariable String userId) {
		List<RatingResponse> ratingResponses = ratingService.findAllRatings(userId);
		return builder.success(HttpStatus.FOUND, "ratings found", ratingResponses);
	}
}
