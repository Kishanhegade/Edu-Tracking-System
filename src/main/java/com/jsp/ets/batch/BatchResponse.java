package com.jsp.ets.batch;

import java.time.LocalDateTime;
import java.util.List;

import com.jsp.ets.user.Student;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class BatchResponse {
	
	private String batchId;
	private String title;
	private List<Student> students;
	private LocalDateTime createdDate;
	private LocalDateTime closedDate;
}
