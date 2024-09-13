package com.jsp.ets.user;

import jakarta.mail.MessagingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.ets.exception.InvalidStackValueException;
import com.jsp.ets.security.RegistrationRequest;
import com.jsp.ets.utility.AppResponseBuilder;
import com.jsp.ets.utility.ErrorStructure;
import com.jsp.ets.utility.ResponseStructure;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@Tag(name = "User Controller", description = "APIs for managing User.")
public class UserController {

	private UserService userService;
	private AppResponseBuilder builder;

	@Operation(description = "The API endpoint is used to register a new Admin user into the database."
			+ " The endpoint validates the registration details and assigns the ADMIN role to the user.",
			responses = {
					@ApiResponse(responseCode = "201", description = "Admin created"),
					@ApiResponse(responseCode = "400", description = "Bad Request, invalid inputs", content = @Content(schema = @Schema(anyOf = ErrorStructure.class))),
					@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(anyOf = RuntimeException.class)))
	})
	@PostMapping("/register/admins")
	public ResponseEntity<ResponseStructure<UserResponse>> saveAdmin(@RequestBody @Valid RegistrationRequest registrationRequest) throws MessagingException {
		UserResponse userResponse = userService.registerUser(registrationRequest, UserRole.ADMIN);
		return builder.success(HttpStatus.ACCEPTED, "Accepted the details, verify your email by submitting the otp", userResponse);

	}

	@Operation(description = "The API endpoint is used to register a new HR into the database."
			+ " The endpoint validates the registration details and assigns HR role to the user.",
			responses = {
					@ApiResponse(responseCode = "201", description = "HR created"),
					@ApiResponse(responseCode = "400", description = "Bad Request, invalid inputs", content = @Content(schema = @Schema(anyOf = ErrorStructure.class))),
					@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(anyOf = RuntimeException.class)))
	})
	@PostMapping("/register/hrs")
	public ResponseEntity<ResponseStructure<UserResponse>> saveHR(@RequestBody @Valid RegistrationRequest registrationRequest) throws MessagingException {
		UserResponse userResponse = userService.registerUser(registrationRequest, UserRole.HR);
		return builder.success(HttpStatus.ACCEPTED, "Accepted the details, verify your email by submitting the otp", userResponse);
	}

	@Operation(description = "The API endpoint is used to register a new Trainer into the database."
			+ " The endpoint validates the registration details and assigns Trainer role to the user.",
			responses = {
					@ApiResponse(responseCode = "201", description = "Trainer created"),
					@ApiResponse(responseCode = "400", description = "Bad Request, invalid inputs", content = @Content(schema = @Schema(anyOf = ErrorStructure.class))),
					@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(anyOf = RuntimeException.class)))
	})
	@PostMapping("/register/trainers")
	public ResponseEntity<ResponseStructure<UserResponse>> saveTrainer(@RequestBody @Valid RegistrationRequest registrationRequest) throws MessagingException {
		UserResponse userResponse = userService.registerUser(registrationRequest, UserRole.TRAINER);
		return builder.success(HttpStatus.ACCEPTED, "Accepted the details, verify your email by submitting the otp", userResponse);
	}

	@Operation(description = "The API endpoint is used to register a new Student into the database."
			+ " The endpoint validates the registration details and assigns Student role to the user.",
			responses = {
					@ApiResponse(responseCode = "201", description = "HR created"),
					@ApiResponse(responseCode = "400", description = "Bad Request, invalid inputs", content = @Content(schema = @Schema(anyOf = ErrorStructure.class))),
					@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(anyOf = RuntimeException.class)))
	})
	@PostMapping("/register/students")
	public ResponseEntity<ResponseStructure<UserResponse>> saveStudent(@RequestBody @Valid RegistrationRequest registrationRequest) throws MessagingException {
		UserResponse userResponse = userService.registerUser(registrationRequest, UserRole.STUDENT);
		return builder.success(HttpStatus.ACCEPTED, "Accepted the details, verify your email by submitting the otp", userResponse);
	}


	@Operation(description = "The API endpoint is used to update the details of an existing Student user based on a unique Identifier."
			+ " The endpoint requires the path variable `userId` and the student details that are to be updated.",
			responses = {
					@ApiResponse(responseCode = "200", description = "Student updated"),
					@ApiResponse(responseCode = "400", description = "Bad Request, invalid inputs", content = @Content(schema = @Schema(anyOf = ErrorStructure.class))),
					@ApiResponse(responseCode = "404", description = "Student not found", content = @Content(schema = @Schema(anyOf = ErrorStructure.class))),
					@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(anyOf = RuntimeException.class)))
	})
	@PutMapping("/students/{userId}")
	public ResponseEntity<ResponseStructure<StudentResponse>> updateStudent(@RequestBody @Valid StudentRequest studentRequest, @PathVariable String userId) {
		StudentResponse studentResponse = (StudentResponse)userService.updateUser(studentRequest, userId);
		return builder.success(HttpStatus.ACCEPTED, "Accepted the details, verify your email by submitting the otp", studentResponse);
	}

	@Operation(description = "The API endpoint is used to update the details of an existing Trainer user based on a unique Identifier."
			+ " The endpoint requires the path variable `userId` and the updated trainer details.",
			responses = {
					@ApiResponse(responseCode = "200", description = "Trainer updated"),
					@ApiResponse(responseCode = "400", description = "Bad Request, invalid inputs", content = @Content(schema = @Schema(anyOf = ErrorStructure.class))),
					@ApiResponse(responseCode = "404", description = "Trainer not found", content = @Content(schema = @Schema(anyOf = ErrorStructure.class))),
					@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(anyOf = RuntimeException.class)))
	})
	@PutMapping("/trainers/{userId}")
	public ResponseEntity<ResponseStructure<TrainerResponse>> updateTrainer(@RequestBody @Valid TrainerRequest trainerRequest, @PathVariable String userId) {
		TrainerResponse trainerResponse = (TrainerResponse)userService.updateUser(trainerRequest, userId);
		return builder.success(HttpStatus.OK, "Trainer updated", trainerResponse);
	} 


	@Operation(
			summary = "Update Student Stack",
			description = "The API endpoint is used to update the stack of an existing Student user based on a unique Identifier. "
					+ "The endpoint requires the path variable `userId` and the new stack value. "
					+ "Only the following stack values are allowed: JAVA_FULL_STACK, MERN_STACK, PYTHON_FULL_STACK. "
					+ "If an invalid stack value is provided, an exception will be thrown.",
					responses = {
							@ApiResponse(
									responseCode = "200", 
									description = "Student stack updated",
									content = @Content(schema = @Schema(implementation = StudentResponse.class))
									),
							@ApiResponse(
									responseCode = "400", 
									description = "Invalid stack value provided", 
									content = @Content(schema = @Schema(implementation = InvalidStackValueException.class))
									),
							@ApiResponse(
									responseCode = "404", 
									description = "Student not found", 
									content = @Content(schema = @Schema(implementation = ErrorStructure.class))
									),
							@ApiResponse(
									responseCode = "500", 
									description = "Internal Server Error", 
									content = @Content(schema = @Schema(implementation = RuntimeException.class))
									)
			}
			)
	@PatchMapping("/students/{userId}")
	public ResponseEntity<ResponseStructure<StudentResponse>> updateStudentStack(
			@Parameter(description = "The new stack to assign to the student. Must be one of: JAVA_FULL_STACK, MERN_STACK, PYTHON_FULL_STACK.", example = "JAVA_FULL_STACK")
			@RequestParam String stack, 
			@PathVariable String userId) {
		StudentResponse studentResponse = userService.updateStudentStack(userId, stack);
		return builder.success(HttpStatus.OK, "Student stack updated", studentResponse);
	}

	@PostMapping("/users/verify-otp")
	public ResponseEntity<ResponseStructure<UserResponse>> verifyUser(
			@RequestBody @Valid OtpRequest otpRequest) {
		UserResponse response = userService.verifyUser(otpRequest);
		return builder.success(HttpStatus.OK, "Successfully registered the user", response);
	}

}
