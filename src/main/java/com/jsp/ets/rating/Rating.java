package com.jsp.ets.rating;

import com.jsp.ets.config.GenerateSequenceId;
import com.jsp.ets.user.Student;
import com.jsp.ets.user.Subject;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ratings")
@Getter
@Setter
public class Rating {
	@Id
	@GenerateSequenceId
	@Column(name = "rating_id")
	private String ratingId;
	
	@Column(name = "subject")
	@Enumerated(EnumType.STRING)
	private Subject subject;
	
	@Column(name="rating")
	@Min(1)
	@Max(5)
	private Integer rating;
	
	@Column(name="feedback")
	private String feedback;
	
	
	@ManyToOne
	private Student student;
}
