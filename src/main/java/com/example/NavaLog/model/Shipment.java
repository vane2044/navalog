package com.example.NavaLog.model;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="shipments")
public class Shipment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	
	@Column(name="Name")
	private String name;
	
	@Column(name="DepartCountry")
	private String departCountry;
	
	@Column(name="DepartPort")
	private String departPort;
	
		
	@Column(name="DestCountry")  
	private String destCountry;
	
	@Column(name="DestPort")
	private String destPort;
	
	
	@Column(name="DepartDate")
	private String departDate;

	@Column(name="ArrivalDate")
	private String arrivalDate;
	
	@Column(name="Space")
	private String space; 
	
	@Column(name="Link")
	private String link; 
	
	@Column(name="Type")
	private String type; 
	

	@OneToMany(mappedBy = "shipment", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
	@JsonIgnore
	private Set<Registration> registrations = new HashSet<>();
	
	public Shipment() {}
	
	
	public Shipment(long id) {
		
		this.id = id;
	}


	public Shipment(String name, String departCountry, String departPort, String destCountry, String destPort,
			String departDate, String arrivalDate, String space, String link, String type) {
		
		
		this.name = name;
		this.departCountry = departCountry;
		this.departPort = departPort;
		this.destCountry = destCountry;
		this.destPort = destPort;
		this.departDate = departDate;
		this.arrivalDate = arrivalDate;
		this.space = space;
		this.link = link;
		this.type = type;
		
	}
		
	public Long getId() {
		// TODO Auto-generated method stub
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartCountry() {
		return departCountry;
	}

	public void setDepartCountry(String departCountry) {
		this.departCountry = departCountry;
	}

	public String getDepartPort() {
		return departPort;
	}

	public void setDepartPort(String departPort) {
		this.departPort = departPort;
	}

	public String getDestCountry() {
		return destCountry;
	}

	public void setDestCountry(String destCountry) {
		this.destCountry = destCountry;
	}

	public String getDestPort() {
		return destPort;
	}

	public void setDestPort(String destPort) {
		this.destPort = destPort;
	}

	public String getDepartDate() {
		return departDate;
	}

	public void setDepartDate(String departDate) {
		this.departDate = departDate;
	}

	public String getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(String arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public String getSpace() {
		return space;
	}

	public void setSpace(String space) {
		this.space = space;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}



	public Set<Registration> getRegistrations() {
		return registrations;
	}

	public void setRegistrations(Set<Registration> registrations) {
		this.registrations = registrations;
	}

	
//	public String toString() {		
//		return "printing in shipment to string "+this.id+" ";
//		
//	}
	
}
