package com.jsp.ets.mapper;

import org.springframework.stereotype.Component;

import com.jsp.ets.security.RegistrationRequest;
import com.jsp.ets.user.Student;
import com.jsp.ets.user.StudentRequest;
import com.jsp.ets.user.StudentResponse;
import com.jsp.ets.user.Trainer;
import com.jsp.ets.user.TrainerRequest;
import com.jsp.ets.user.TrainerResponse;
import com.jsp.ets.user.User;
import com.jsp.ets.user.UserResponse;

@Component
public class UserMapper {
	
	public User mapToUserEntity(RegistrationRequest registrationRequest, User user) {
		user.setUsername(registrationRequest.getUsername());
		user.setEmail(registrationRequest.getEmail());
		user.setPassword(registrationRequest.getPassword());
		return user;
	}



	public UserResponse mapToUserResponse(User user) {
		UserResponse response = new UserResponse();
		response.setUserId(user.getUserId());
		response.setUsername(user.getUsername());
		response.setEmail(user.getEmail());
		response.setRole(user.getRole());
		return response;
	}

	public Student mapToStudentEntity(StudentRequest studentRequest, Student student) {
		
		student.setEmail(studentRequest.getEmail());
		student.setUsername(studentRequest.getUsername());
		student.setDegree(studentRequest.getDegree());
		student.setStream(studentRequest.getStream());
		student.setTwelfthPercentage(studentRequest.getTwelfthPercentage());
		student.setTenthPercentage(studentRequest.getTenthPercentage());
		student.setDegreePercentage(studentRequest.getDegreePercentage());
		student.setYop(studentRequest.getYop());
		return student;
	}



	public StudentResponse mapToStudentResponse(Student student) {
		StudentResponse response = new StudentResponse();
		response.setUserId(student.getUserId());
		response.setUsername(student.getUsername());
		response.setRole(student.getRole());
		response.setEmail(student.getEmail());
		response.setDegree(student.getDegree());
		response.setStream(student.getStream());
		response.setTenthPercentage(student.getTenthPercentage());
		response.setTwelfthPercentage(student.getTwelfthPercentage());
		response.setDegreePercentage(student.getDegreePercentage());
		response.setYop(student.getYop());
		return response;
	}



	public Trainer mapToTrainerEntity(TrainerRequest trainerRequest, Trainer trainer) {
		trainer.setUsername(trainerRequest.getUsername());
		trainer.setEmail(trainerRequest.getEmail());
		trainer.setSubjects(trainerRequest.getSubjects());
		return trainer;
	}



	public TrainerResponse mapToTrainerResponse(Trainer trainer) {
		TrainerResponse response = new TrainerResponse();
		response.setUserId(trainer.getUserId());
		response.setUsername(trainer.getUsername());
		response.setEmail(trainer.getEmail());
		response.setRole(trainer.getRole());
		response.setSubjects(trainer.getSubjects());
		return response;
	}
}
