package com.user.model;

import java.io.ObjectInputFilter.Status;
import java.time.LocalDateTime;

public class Property {
	
	private int propertyID;

    private User manager;

    private String address;


    private String description;

    private double rent;

    
    private Status status;

   
    private LocalDateTime dateListed;

   
    private LocalDateTime dateUpdated;


	public Property() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Property(int propertyID, User manager, String address, String description, double rent, Status status,
			LocalDateTime dateListed, LocalDateTime dateUpdated) {
		super();
		this.propertyID = propertyID;
		this.manager = manager;
		this.address = address;
		this.description = description;
		this.rent = rent;
		this.status = status;
		this.dateListed = dateListed;
		this.dateUpdated = dateUpdated;
	}


	public int getPropertyID() {
		return propertyID;
	}


	public void setPropertyID(int propertyID) {
		this.propertyID = propertyID;
	}


	public User getManager() {
		return manager;
	}


	public void setManager(User manager) {
		this.manager = manager;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public double getRent() {
		return rent;
	}


	public void setRent(double rent) {
		this.rent = rent;
	}


	public Status getStatus() {
		return status;
	}


	public void setStatus(Status status) {
		this.status = status;
	}


	public LocalDateTime getDateListed() {
		return dateListed;
	}


	public void setDateListed(LocalDateTime dateListed) {
		this.dateListed = dateListed;
	}


	public LocalDateTime getDateUpdated() {
		return dateUpdated;
	}


	public void setDateUpdated(LocalDateTime dateUpdated) {
		this.dateUpdated = dateUpdated;
	}


	@Override
	public String toString() {
		return "Property [propertyID=" + propertyID + ", manager=" + manager + ", address=" + address + ", description="
				+ description + ", rent=" + rent + ", status=" + status + ", dateListed=" + dateListed
				+ ", dateUpdated=" + dateUpdated + "]";
	}

    
}
