package com.jsp.ets.user;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrainerRequest extends UserRequest{
	

    @NotNull(message = "Subjects list cannot be null")
    @NotEmpty(message = "At least one subject must be selected")
	private List<Subject> subjects;
}
