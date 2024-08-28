package com.jsp.ets.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
	public ResponseEntity<ResponseStructure<UserResponse>> saveAdmin(@RequestBody @Valid UserRequest userRequest) {
		UserResponse userResponse = userService.saveUser(userRequest, UserRole.ADMIN);
		return builder.success(HttpStatus.CREATED, "admin created", userResponse);
		
	}
	
	@PostMapping("/hrs")
	public ResponseEntity<ResponseStructure<UserResponse>> saveHR(@RequestBody @Valid UserRequest userRequest) {
		UserResponse userResponse = userService.saveUser(userRequest, UserRole.HR);
		return builder.success(HttpStatus.CREATED, "HR created", userResponse);
	}
	
	@PostMapping("/trainers")
	public ResponseEntity<ResponseStructure<UserResponse>> saveTrainer(@RequestBody @Valid UserRequest userRequest) {
		UserResponse userResponse = userService.saveUser(userRequest, UserRole.TRAINER);
		return builder.success(HttpStatus.CREATED, "Trainer created", userResponse);
	}
	
	@PostMapping("/students")
	public ResponseEntity<ResponseStructure<UserResponse>> saveStudent(@RequestBody @Valid UserRequest userRequest) {
		UserResponse userResponse = userService.saveUser(userRequest, UserRole.TRAINER);
		return builder.success(HttpStatus.CREATED, "Student created", userResponse);
	}

}
