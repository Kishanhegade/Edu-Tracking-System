package com.jsp.ets.mapper;

import org.springframework.stereotype.Component;

import com.jsp.ets.security.RegistrationRequest;
import com.jsp.ets.user.User;
import com.jsp.ets.user.UserRequest;
import com.jsp.ets.user.UserResponse;

@Component
public class UserMapper {
	
	public User mapToUserEntity(RegistrationRequest registrationRequest, User user) {
		user.setUsername(registrationRequest.getUsername());
		user.setEmail(registrationRequest.getEmail());
		user.setPassword(registrationRequest.getPassword());
		return user;
	}



	public UserResponse mapToUserResponse(User user) {
		UserResponse response = new UserResponse();
		response.setUserId(user.getUserId());
		response.setUsername(user.getUsername());
		response.setEmail(user.getEmail());
		response.setRole(user.getRole());
		return response;
	}

}
