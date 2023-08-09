package com.example.NavaLog.model;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {
	
	Optional<Registration> findById(Long id);
	List <Registration> findByShipmentId(Long id);
	List<Registration> findByUserId(Long id);
	
	
	//creating this method to get a specific user associated with particular shipment id
    List<Registration> findByShipmentIdAndUserId(Long uid, Long eid);
}
