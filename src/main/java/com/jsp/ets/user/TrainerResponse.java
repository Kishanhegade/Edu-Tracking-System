package com.jsp.ets.user;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class TrainerResponse extends UserResponse{
	
	private List<Subject> subjects;

}
