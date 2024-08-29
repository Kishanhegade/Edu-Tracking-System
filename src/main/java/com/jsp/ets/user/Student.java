package com.jsp.ets.user;

import java.time.Year;
import java.util.List;

import com.jsp.ets.batch.Batch;
import com.jsp.ets.rating.Rating;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
@Entity
@Table(name = "students")
@Getter
@Setter
public class Student extends User{
	@Column(name = "degree")
	private String degree;
	
	@Column(name = "stream")
	private String stream;
	
	@Column(name = "yop")
	private Year yop;
	
	@Column(name = "degree_percentage")
	private Double degreePercentage;
	
	@Column(name = "twelfth_percentage")
	private Double twelfthPercentage;
	
	@Column(name = "tenth_percentage")
	private Double tenthPercentage;
	
	@Column(name = "tech_stack")
	@Enumerated(EnumType.STRING)
	private Stack stack;
	
	@OneToMany(mappedBy = "student")
	private List<Rating> ratings;
	
	@ManyToMany(mappedBy = "students")
	private List<Batch> batches;
	
	
	

}
