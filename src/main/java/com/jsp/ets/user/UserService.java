package com.jsp.ets.user;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jsp.ets.mapper.UserMapper;
import com.jsp.ets.security.RegistrationRequest;

import jakarta.validation.Valid;
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

	public UserResponse updateUser(UserRequest userRequest, String userId, UserRole role) {
		Optional<User> optional = userRepo.findById(userId);
		if(optional.isPresent()) {
			switch (role) {
			case STUDENT -> {
				Student student = (Student)optional.get();
				student = userMapper.mapToStudentEntity((StudentRequest)userRequest, student);
				student = userRepo.save(student);
				return userMapper.mapToStudentResponse(student);
			}
			default -> throw new IllegalArgumentException("Unexpected value: " + role);
			}
		}else {
			return null;
		}
	}


}
