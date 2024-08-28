package com.jsp.ets.mapper;

import org.springframework.stereotype.Component;

import com.jsp.ets.user.User;
import com.jsp.ets.user.UserRequest;
import com.jsp.ets.user.UserResponse;
import com.jsp.ets.user.UserRole;

@Component
public class UserMapper {
	
	public User mapToUserEntity(UserRequest userRequest, User user) {
		user.setUsername(userRequest.getUsername());
		user.setEmail(userRequest.getEmail());
		user.setPassword(userRequest.getPassword());
		user.setRole(UserRole.ADMIN);
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
