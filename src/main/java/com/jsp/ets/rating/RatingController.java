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
import com.jsp.ets.utility.ErrorStructure;
import com.jsp.ets.utility.ResponseStructure;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@Tag(name = "Rating Controller", description = "APIs for managing student ratings.")
public class RatingController {

	private RatingService ratingService;
	private AppResponseBuilder builder;

	@Operation(summary = "Update rating of a student based on student's userId", description = "The API endpoint is used to update the student rating based on a unique identifier." 
			+ "The endpoint takes a path variable `userId` and the rating details that has to be updated as the body of the request in json format",
			responses = {
			@ApiResponse(responseCode = "200", description = "Rating updated",content = @Content(mediaType = "application/json",schema = @Schema(implementation = Rating.class))),
			@ApiResponse(responseCode = "400", description = "Bad Request, invalid inputs", content = @Content(mediaType = "application/json",schema = @Schema(anyOf = ErrorStructure.class))),
			@ApiResponse(responseCode = "404", description = "Student not found", content = @Content(mediaType = "application/json",schema = @Schema(anyOf = ErrorStructure.class))),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json",schema = @Schema(anyOf = RuntimeException.class)))
	})

	@PutMapping("/ratings/{ratingId}")
	public ResponseEntity<ResponseStructure<RatingResponse>> updateRating(@PathVariable String ratingId, @RequestBody RatingRequest ratingRequest) {
		RatingResponse ratingResponse = ratingService.updateRating(ratingId, ratingRequest);
		return builder.success(HttpStatus.OK,"Rating updated", ratingResponse);
	}


	@Operation(summary = "Get all ratings", description = "Fetch all ratings for a particular student based on a unique Identifier.")
	@ApiResponse(responseCode = "302", description = "Ratings found.",
	content = @Content(mediaType = "application/json", schema = @Schema(implementation = RatingResponse.class)))
	@GetMapping("/students/{userId}/ratings")
	public ResponseEntity<ResponseStructure<List<RatingResponse>>> findAllRatings(@PathVariable String userId) {
		List<RatingResponse> ratingResponses = ratingService.findAllRatings(userId);
		return builder.success(HttpStatus.FOUND, "Ratings found", ratingResponses);
	}
}
