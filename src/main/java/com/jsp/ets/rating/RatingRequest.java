package com.jsp.ets.rating;



import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class RatingRequest {
	@Schema(description = "Rating value between 1 and 5", example = "4", minimum = "1", maximum = "5")
	@Min(1)
	@Max(5)
	@Pattern(regexp = "^-?\\d+$", message = "Must be an integer")
	private Integer ratings;

	@Schema(description = "Feedback provided by the trainer on the performance of the student ", example = "Need to improve in communication")
	@NotNull
	@NotEmpty
	private String feedback;


}
