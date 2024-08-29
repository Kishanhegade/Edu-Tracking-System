package com.jsp.ets.rating;

import com.jsp.ets.user.Student;
import com.jsp.ets.user.Subject;

import jakarta.persistence.Entity;
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
	private String ratingId;
	
	private Subject subject;
	@Min(1)
	@Max(5)
	private Integer rating;
	
	private String feedback;
	
	@ManyToOne
	private Student student;
}
