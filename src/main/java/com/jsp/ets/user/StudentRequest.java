package com.jsp.ets.user;

import java.time.Year;

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
    private String degree;

    @NotBlank(message = "Stream is required")
    private String stream;

    @NotNull(message = "Year of Passing is required")
    private Year yop;

    @NotNull(message = "Degree percentage is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Degree percentage must be greater than 0")
    @DecimalMax(value = "100.0", inclusive = true, message = "Degree percentage must be less than or equal to 100")
    private Double degreePercentage;

    @NotNull(message = "Twelfth percentage is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Twelfth percentage must be greater than 0")
    @DecimalMax(value = "100.0", inclusive = true, message = "Twelfth percentage must be less than or equal to 100")
    private Double twelfthPercentage;

    @NotNull(message = "Tenth percentage is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Tenth percentage must be greater than 0")
    @DecimalMax(value = "100.0", inclusive = true, message = "Tenth percentage must be less than or equal to 100")
    private Double tenthPercentage;
}
