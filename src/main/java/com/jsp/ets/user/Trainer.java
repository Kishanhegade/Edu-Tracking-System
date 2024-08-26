package com.jsp.ets.user;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "trainers")
public class Trainer extends User{
	
	private List<Subject> subjects;

}
