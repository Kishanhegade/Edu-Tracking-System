package com.jsp.ets.batch;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.jsp.ets.user.Student;
import com.jsp.ets.user.Subject;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class BatchResponse {
	
	private String batchId;
	private String title;
	private List<Student> students;
	private List<Subject> subjects;
	private LocalDate startDate;
	private LocalDate closedDate;
	private LocalTime beginsAt;
	private LocalTime endsAt;
}
