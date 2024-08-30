package com.jsp.ets.rating;



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
	@Min(1)
	@Max(5)
	@Pattern(regexp = "^-?\\d+$", message = "Must be an integer")
	private Integer rating;
	@NotNull
	@NotEmpty
	private String feedback;
	

}
