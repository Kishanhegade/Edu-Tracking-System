package com.jsp.ets.rating;

import com.jsp.ets.user.Subject;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RatingResponse {

	private String ratingId;
	private Integer rating;

	@Schema(description = "Feedback provided by the trainer", example = "Great course, learned a lot!")
	private String feedback;

	@Schema(description = "Subject associated with the rating", example = "CORE_JAVA")
	private Subject subject;
}
