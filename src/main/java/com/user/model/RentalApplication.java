package com.user.model;

import java.time.LocalDateTime;

public class RentalApplication {

	private int applicationID;

    
    private User tenant;

    
    private Property property;

    
    private LocalDateTime applicationDate;

    
    private ApplicationStatus status;


	public RentalApplication() {
		super();
		// TODO Auto-generated constructor stub
	}


	public RentalApplication(int applicationID, User tenant, Property property, LocalDateTime applicationDate,
			ApplicationStatus status) {
		super();
		this.applicationID = applicationID;
		this.tenant = tenant;
		this.property = property;
		this.applicationDate = applicationDate;
		this.status = status;
	}


	public int getApplicationID() {
		return applicationID;
	}


	public void setApplicationID(int applicationID) {
		this.applicationID = applicationID;
	}


	public User getTenant() {
		return tenant;
	}


	public void setTenant(User tenant) {
		this.tenant = tenant;
	}


	public Property getProperty() {
		return property;
	}


	public void setProperty(Property property) {
		this.property = property;
	}


	public LocalDateTime getApplicationDate() {
		return applicationDate;
	}


	public void setApplicationDate(LocalDateTime applicationDate) {
		this.applicationDate = applicationDate;
	}


	public ApplicationStatus getStatus() {
		return status;
	}


	public void setStatus(ApplicationStatus status) {
		this.status = status;
	}


	@Override
	public String toString() {
		return "RentalApplication [applicationID=" + applicationID + ", tenant=" + tenant + ", property=" + property
				+ "]";
	}
    
	public enum ApplicationStatus {
        PENDING, APPROVED, REJECTED
    }

}
