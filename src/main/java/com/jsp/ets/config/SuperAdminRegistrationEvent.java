package com.jsp.ets.config;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.jsp.ets.user.SuperAdmin;
import com.jsp.ets.user.User;
import com.jsp.ets.user.UserRepository;
import com.jsp.ets.user.UserRole;

import lombok.extern.slf4j.Slf4j;


@Component
@Slf4j
public class SuperAdminRegistrationEvent {
	
	private UserRepository userRepository;
	//Here AllArgsConstructor cannot be used
	public SuperAdminRegistrationEvent(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Value("${super_admin.email}")
	private String superAdminEmail;
	
	@EventListener(classes = ApplicationReadyEvent.class)
	public void registerSuperAdmin() {
		log.info("Checking if Super Admin present");
		List<User> superAdmins = userRepository.findByRole(UserRole.SUPER_ADMIN);
		if(superAdmins.isEmpty()) {
			log.info("Super Admin is not present so creating one");
			SuperAdmin superAdmin = new SuperAdmin();
			superAdmin.setEmail(superAdminEmail);
			superAdmin.setPassword(UUID.randomUUID().toString());
			superAdmin.setRole(UserRole.SUPER_ADMIN);
			userRepository.save(superAdmin);
		}else {
			log.info("Super Admin already present with email:" + superAdmins.getFirst().getEmail());
		}
		
	}

}
