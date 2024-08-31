package com.jsp.ets.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {
	@NotNull(message = "Username is mandatory")
	@Pattern(regexp = "^[a-zA-Z0-9#$&*\\-_.!@]+$", message = "Invalid username format")
	@Schema(
			description = "Username for the user. Must be alphanumeric and can include special characters like #$&*-_.!@.",
			example = "john_doe123!"
			)
	private String username;
	@Email(regexp = "^[a-zA-Z0-9._%+-]+@gmail\\.com$", message = "Email should be a valid Gmail address")
	@Schema(
			description = "Email address of the user. Must be a valid Gmail address.",
			example = "example@gmail.com"
			)
	private String email;


}
