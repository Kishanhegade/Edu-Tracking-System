package com.jsp.ets.batch;



import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.jsp.ets.config.GenerateSequenceId;
import com.jsp.ets.user.Student;
import com.jsp.ets.user.Subject;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "batches")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Batch {
	@Id
	@GenerateSequenceId
	@Column(name="batch_id")
	private String batchId;
	
	@Column(name="title")
	private String title;
	
	@Column(name="subjects")
	private List<Subject> subjects;
	
	
	@Column(name="starting_date")
	private LocalDate startDate;
	
	@LastModifiedDate
	@Column(name="closed_date")
	private LocalDate closedDate;
	
	@ManyToMany
	private List<Student> students;
	
	@Column(name = "begins_at")
	private LocalTime beginsAt;
	
	@Column(name="ends_at")
	private LocalTime endsAt;
	
	@Column(name = "status")
	private BatchStatus status;
}
