package com.example.NavaLog.model;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long>{

	Optional<User> findById(Long id);
	Optional <User> findByUserId(String userId);
	List<User> findByName(String name);
	Optional<User> findByEmail(String email);  //taken optional not list because utilizing in controller if wants to update through email
	List<User>findByPhone(String phone);
	Optional<User> findByIdOrEmail(Long id, String email); // tried to use in user controller 
	Boolean existsByUserId(String userId);  // No idea why professor used this in student repo
	
}
