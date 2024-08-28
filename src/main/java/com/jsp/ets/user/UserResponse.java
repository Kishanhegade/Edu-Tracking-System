package com.jsp.ets.user;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {
	
	private String userId;
	private String username;
	private String email;
	@Enumerated(EnumType.STRING)
	private UserRole role;

}
