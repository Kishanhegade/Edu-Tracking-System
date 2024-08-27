package com.jsp.ets.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "super_admin")
@Getter
@Setter
public class SuperAdmin extends User{
	

}
