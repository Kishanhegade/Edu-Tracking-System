package com.jsp.ets.user;

import org.springframework.stereotype.Service;

import com.jsp.ets.mapper.UserMapper;
import com.jsp.ets.security.RegistrationRequest;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserService {

	private UserRepository userRepo;
	private UserMapper userMapper;

	public UserResponse saveUser(RegistrationRequest registrationRequest, UserRole role) {
		User user = null;
		switch (role) {
		case ADMIN -> user = new Admin();
		case HR -> user = new HR();
		case STUDENT -> user = new Student();
		case TRAINER -> user = new Trainer();
		default -> throw new IllegalArgumentException("Unexpected value: " + role);
		}
		
		if(user != null) {
			user = userMapper.mapToUserEntity(registrationRequest, user);
			user.setRole(role);
			user = userRepo.save(user);
		}
		
		return userMapper.mapToUserResponse(user);
	}


}
