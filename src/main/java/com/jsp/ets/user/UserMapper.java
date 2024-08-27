package com.jsp.ets.user;

public class UserMapper {
	
	public User mapUserRequestToUser(UserRequest userRequest, User user) {
		user.setUsername(userRequest.getUsername());
		user.setEmail(userRequest.getEmail());
		user.setPassword(userRequest.getPassword());
		user.setRole(userRequest.getRole());
		
		return user;
	}

}
