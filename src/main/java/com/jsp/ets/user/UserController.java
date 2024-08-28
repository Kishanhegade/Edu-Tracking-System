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
	
	@PostMapping("/users")
	public ResponseEntity<ResponseStructure<UserResponse>> saveAdmin(@RequestBody @Valid UserRequest userRequest) {
		UserResponse userResponse = userService.saveUser(userRequest);
		return builder.success(HttpStatus.CREATED, "admin created", userResponse);
		
	}

}
