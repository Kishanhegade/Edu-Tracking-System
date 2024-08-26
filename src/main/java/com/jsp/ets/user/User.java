package com.jsp.ets.user;

import java.time.LocalDateTime;

import com.jsp.ets.config.GenerateSequenceId;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Getter
@Table(name = "users")
public class User {
	@Id
	@GenerateSequenceId
	@Column(name = "user_id")
	private String userId;
	@Column(name = "username")
	private String username;
	@Column(name = "email")
	private String email;
	@Column(name = "password")
	private String password;
	@Column(name = "created_date")
	private LocalDateTime createdDate;
	@Column(name = "created_at")
	private LocalDateTime createdAt;
	@Column(name = "role")
	private UserRole role;
	
	
	

}
