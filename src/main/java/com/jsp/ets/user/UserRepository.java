package com.jsp.ets.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String>{
	
	List<User> findByRole(UserRole role);
	Optional<User> findByEmail(String email);
	
}
