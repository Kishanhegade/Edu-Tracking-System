package com.jsp.ets.user;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

public enum Privilege {
	
	WRITE_ADMIN, WRITE_HR, WRITE_STUDENT, WRITE_TRAINER,
	
	READ_USER,
	
	WRITE_BATCH,
	READ_BATCH,
	
	WRITE_MOCK,
	READ_MOCK,
	
	WRITE_REQUIREMENT,
	READ_REQUIREMENT, 
	
	WRITE_SCHEDULE,
	READ_SCHEDULE,
	
	WRITE_TECH_REPORT,
	READ_TECH_REPORT,
	
	WRITE_RATING,
	READ_RATING;



}
