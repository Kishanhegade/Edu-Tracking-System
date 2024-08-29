package com.jsp.ets.batch;



import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
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
	
	@CreatedDate
	@Column(name="created_date")
	private LocalDateTime createdDate;
	
	@LastModifiedDate
	@Column(name="closed_date")
	private LocalDateTime closedDate;
	
	@ManyToMany
	private List<Student> students;
}
