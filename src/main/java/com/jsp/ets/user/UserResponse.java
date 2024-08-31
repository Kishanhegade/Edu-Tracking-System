package com.jsp.ets.user;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {

	@Schema(description = "Unique identifier for the user.", example = "EDU2024b62fe7a5-39bb-4c96-99fe-696066d684c1")
	private String userId;

	@Schema(description = "Username for the user.", example = "john_doe123!")
	private String username;

	@Schema(description = "Email address of the user.", example = "example@gmail.com")
	private String email;

	@Enumerated(EnumType.STRING)
	@Schema(description = "Role of the user in the system.", example = "STUDENT")
	private UserRole role;

	@Schema(description = "Timestamp when the user was created.", example = "2024-08-30T19:03:42.813Z")
	private LocalDateTime createdDate;

	@Schema(description = "Timestamp when the user's information was last modified.", example = "2024-08-30T19:03:42.813Z")
	private LocalDateTime modifiedDate;

}
