package com.jsp.ets.batch;

import java.time.LocalTime;
import java.util.List;

import com.jsp.ets.user.Subject;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class BatchRequest {
	@NotNull
	@NotEmpty
	@Pattern(regexp = "^[A-Za-z]+$" ,message = "title can have only alphabets")
	private String title;
	@NotNull
	@NotEmpty
	private List<Subject> subjects;
	@NotNull(message = "beginsAt cannot be null")
	private LocalTime beginsAt;
	@NotNull(message = "endsAt cannot be null")
	private LocalTime endsAt;
	

}
