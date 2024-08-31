package com.jsp.ets.user;

import java.time.Year;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentRequest extends UserRequest {

	@NotBlank(message = "Degree is required")
	@Schema(
			description = "Degree obtained by the student. Cannot be blank.",
			example = "Bachelor of Technology"
			)
	private String degree;

	@NotBlank(message = "Stream is required")
	@Schema(
			description = "Stream or specialization of the student. Cannot be blank.",
			example = "Computer Science"
			)
	private String stream;

	@NotNull(message = "Year of Passing is required")
	@Schema(
			description = "Year of passing the degree. Must be a valid year and cannot be null.",
			example = "2022"
			)
	private Year yop;

	@NotNull(message = "Degree percentage is required")
	@DecimalMin(value = "0.0", inclusive = false, message = "Degree percentage must be greater than 0")
	@DecimalMax(value = "100.0", inclusive = true, message = "Degree percentage must be less than or equal to 100")
	@Schema(
			description = "Percentage obtained in the degree. Must be between 0.0 and 100.0.",
			example = "85.5"
			)
	private Double degreePercentage;

	@NotNull(message = "Twelfth percentage is required")
	@DecimalMin(value = "0.0", inclusive = false, message = "Twelfth percentage must be greater than 0")
	@DecimalMax(value = "100.0", inclusive = true, message = "Twelfth percentage must be less than or equal to 100")
	@Schema(
			description = "Percentage obtained in the twelfth grade. Must be between 0.0 and 100.0.",
			example = "88.0"
			)
	private Double twelfthPercentage;

	@NotNull(message = "Tenth percentage is required")
	@DecimalMin(value = "0.0", inclusive = false, message = "Tenth percentage must be greater than 0")
	@DecimalMax(value = "100.0", inclusive = true, message = "Tenth percentage must be less than or equal to 100")
	@Schema(
			description = "Percentage obtained in the tenth grade. Must be between 0.0 and 100.0.",
			example = "90.0"
			)
	private Double tenthPercentage;

	@Schema(
			description = "Stack associated with the student. Must be one of the predefined values.",
			example = "JAVA_FULL_STACK"
			)
	private Stack stack;
}
