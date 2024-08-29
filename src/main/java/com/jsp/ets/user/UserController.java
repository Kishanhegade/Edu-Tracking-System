package com.jsp.ets.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.ets.security.RegistrationRequest;
import com.jsp.ets.utility.AppResponseBuilder;
import com.jsp.ets.utility.ResponseStructure;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class UserController {
	
	private UserService userService;
	private AppResponseBuilder builder;
	
	@PostMapping("/admins")
	public ResponseEntity<ResponseStructure<UserResponse>> saveAdmin(@RequestBody @Valid RegistrationRequest registrationRequest) {
		UserResponse userResponse = userService.saveUser(registrationRequest, UserRole.ADMIN);
		return builder.success(HttpStatus.CREATED, "admin created", userResponse);
		
	}
	
	@PostMapping("/hrs")
	public ResponseEntity<ResponseStructure<UserResponse>> saveHR(@RequestBody @Valid RegistrationRequest registrationRequest) {
		UserResponse userResponse = userService.saveUser(registrationRequest, UserRole.HR);
		return builder.success(HttpStatus.CREATED, "HR created", userResponse);
	}
	
	@PostMapping("/trainers")
	public ResponseEntity<ResponseStructure<UserResponse>> saveTrainer(@RequestBody @Valid RegistrationRequest registrationRequest) {
		UserResponse userResponse = userService.saveUser(registrationRequest, UserRole.TRAINER);
		return builder.success(HttpStatus.CREATED, "Trainer created", userResponse);
	}
	
	@PostMapping("/students")
	public ResponseEntity<ResponseStructure<UserResponse>> saveStudent(@RequestBody @Valid RegistrationRequest registrationRequest) {
		UserResponse userResponse = userService.saveUser(registrationRequest, UserRole.STUDENT);
		return builder.success(HttpStatus.CREATED, "Student created", userResponse);
	}
	
	@PutMapping("/students/{userId}")
	public ResponseEntity<ResponseStructure<StudentResponse>> updateStudent(@RequestBody @Valid StudentRequest studentRequest, @PathVariable String userId) {
		StudentResponse studentResponse = (StudentResponse)userService.updateUser(studentRequest, userId, UserRole.STUDENT);
		return builder.success(HttpStatus.OK, "Student updated", studentResponse);
	}

	@PutMapping("/trainers/{userId}")
	public ResponseEntity<ResponseStructure<TrainerResponse>> updateTrainer(@RequestBody @Valid TrainerRequest trainerRequest, @PathVariable String userId) {
		TrainerResponse trainerResponse = (TrainerResponse)userService.updateUser(trainerRequest, userId, UserRole.TRAINER);
		return builder.success(HttpStatus.OK, "Trainer updated", trainerResponse);
	} 
	
	@PatchMapping("/students/{userId}")
	public ResponseEntity<ResponseStructure<StudentResponse>> updateStudentStack(@RequestParam String stack, @PathVariable String userId) {
		StudentResponse studentResponse = (StudentResponse)userService.updateStudentStack(userId, stack);
		return builder.success(HttpStatus.OK, "Student stack updated", studentResponse);
	}
}
