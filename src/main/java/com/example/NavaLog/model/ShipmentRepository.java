package com.example.NavaLog.model;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import jakarta.persistence.Column;



public interface ShipmentRepository extends JpaRepository<Shipment, Long> {
	Optional<Shipment> findById(Long id);
	
	List<Shipment> findByName(String name);  
	List<Shipment> findByDepartCountry(String departCountry);
	List<Shipment> findByDepartPort(String departPort);
	List<Shipment> findByDestCountry(String destCountry);
	List<Shipment> findByDestPort(String destPort);
	List<Shipment> findByDepartDate(String departDate);
	List<Shipment> findByArrivalDate(String arrivalDate);
	List<Shipment> findBySpace(String space); 
	List<Shipment> findByType(String type); 
	//List<Shipment> findByLink(String link); 
	

}
