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
	 @NotBlank(message = "Password is mandatory")
	 @Size(min = 8, message = "Password must be at least 8 characters long")
	 @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$^&*._-]).{8,}$",
	            message = "Password must contain at least one uppercase letter, one lowercase letter, one number, and one special character")
	private String password;

}
