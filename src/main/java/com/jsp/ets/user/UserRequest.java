package com.jsp.ets.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class UserRequest {
	@NotNull(message = "Username is mandatory")
	@Pattern(regexp = "^[a-zA-Z0-9#$&*\\-_.!@]+$", message = "Invalid username format")
	private String username;
	private String email;
	
	private String password;
	private Role role;
	
	

}
