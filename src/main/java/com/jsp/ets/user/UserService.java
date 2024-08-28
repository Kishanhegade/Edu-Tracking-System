package com.jsp.ets.user;

import org.springframework.stereotype.Service;

import com.jsp.ets.mapper.UserMapper;


import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserService {
	
	private UserRepository userRepo;
	private UserMapper userMapper;
	
	public UserResponse saveUser(UserRequest userRequest) {
		User admin = userMapper.mapToUserEntity(userRequest, new Admin());
		admin = userRepo.save(admin);
		return userMapper.mapToUserResponse(admin);
	}

}
