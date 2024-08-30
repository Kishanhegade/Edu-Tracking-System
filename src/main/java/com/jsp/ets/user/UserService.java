package com.jsp.ets.user;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.stereotype.Service;


import com.jsp.ets.exception.IllegalStackTypeException;
import com.jsp.ets.exception.UserNotFoundByIdException;
import com.jsp.ets.mapper.UserMapper;
import com.jsp.ets.rating.Rating;
import com.jsp.ets.rating.RatingRepository;
import com.jsp.ets.security.RegistrationRequest;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserService {

	private UserRepository userRepo;
	private UserMapper userMapper;
	private RatingRepository ratingRepo;


	public UserResponse saveUser(RegistrationRequest registrationRequest, UserRole role) {
		User user = switch (role) {
		case ADMIN -> new Admin();
		case HR -> new HR();
		case STUDENT -> new Student();
		case TRAINER -> new Trainer();
		default -> throw new IllegalArgumentException("Unexpected value: " + role);
		};

		user = userMapper.mapToUserEntity(registrationRequest, user);
		user.setRole(role);
		user = userRepo.save(user);

		return userMapper.mapToUserResponse(user);
	}

	public UserResponse updateUser(UserRequest userRequest, String userId, UserRole role) {
		return userRepo.findById(userId)
				.map(user -> {
					switch (role) {
					case STUDENT -> {
						Student student = userMapper.mapToStudentEntity((StudentRequest) userRequest, (Student) user);
						return userMapper.mapToStudentResponse(userRepo.save(student));
					}
					case TRAINER -> {
						Trainer trainer = userMapper.mapToTrainerEntity((TrainerRequest) userRequest, (Trainer) user);
						return userMapper.mapToTrainerResponse(userRepo.save(trainer));
					}
					default -> throw new IllegalArgumentException("Unexpected value: " + role);
					}
				})
				.orElseThrow(() -> new UserNotFoundByIdException("Failed to update User"));

	}

	public StudentResponse updateStudentStack(String userId, String stack) {
		Student student = (Student) userRepo.findById(userId)
				.orElseThrow(() -> new UserNotFoundByIdException("Failed to update Stack"));

		Stack techStack = Arrays.stream(Stack.values())
				.filter(s -> s.name().equalsIgnoreCase(stack))
				.findFirst()
				.orElseThrow(() -> new IllegalStackTypeException("Stack not found"));

		techStack.getSubjects().forEach(subject -> {
			Rating rating = new Rating();
			rating.setStudent(student);
			rating.setSubject(subject);
			ratingRepo.save(rating);
		});

		student.setStack(techStack);
		userRepo.save(student);
		return userMapper.mapToStudentResponse(student);
	}




}
