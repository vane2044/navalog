package com.example.NavaLog.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Registration {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "shipment_id", nullable = false)
	// @JsonIgnore
	////@JsonProperty("shipment") 
	@JsonUnwrapped(prefix = "shipment.")
	private Shipment shipment;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "user_id", nullable = false)
	// @JsonIgnore  // Important this doesn't allow the post mapping to happen because it ignores the setting into json format
	////?@JsonProperty("user") 
	@JsonUnwrapped(prefix = "user.")
	private User user;

	public Registration() {

	}

	public Registration(User user, Shipment shipment) {
		this.user = user;
        this.shipment = shipment;
		user.getRegistrations().add(this);
	    shipment.getRegistrations().add(this);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Shipment getShipment() {
		return shipment;
	}

	public void setShipment(Shipment shipment) {
		this.shipment = shipment;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

//	@Override
//	public String toString() {
//		//return this.id + " " + this.shipment + " " + this.user;
//		return  this.shipment + " " + this.user.getId()+"printing in regiter class";
//	}
}
