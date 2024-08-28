package com.jsp.ets.user;

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
	private String username;
	 @Email(regexp = "^[a-zA-Z0-9._%+-]+@gmail\\.com$", message = "Email should be a valid Gmail address")
	private String email;
	 

}
