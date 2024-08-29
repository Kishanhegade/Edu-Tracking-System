package com.jsp.ets.batch;

import java.util.List;

import com.jsp.ets.user.Subject;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class BatchRequest {
	@NotNull
	private String title;
	@NotNull
	@NotEmpty
	private List<Subject> subjects; 

}
