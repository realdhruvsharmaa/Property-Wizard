package com.user.model;

import java.time.LocalDateTime;

public class ProfileModel {

	private int profileID;

    
    private User user;

    
    private String rentalHistory;

    
    private LocalDateTime dateUpdated;


	public ProfileModel() {
		super();
		// TODO Auto-generated constructor stub
	}


	public ProfileModel(int profileID, User user, String rentalHistory, LocalDateTime dateUpdated) {
		super();
		this.profileID = profileID;
		this.user = user;
		this.rentalHistory = rentalHistory;
		this.dateUpdated = dateUpdated;
	}


	public int getProfileID() {
		return profileID;
	}


	public void setProfileID(int profileID) {
		this.profileID = profileID;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public String getRentalHistory() {
		return rentalHistory;
	}


	public void setRentalHistory(String rentalHistory) {
		this.rentalHistory = rentalHistory;
	}


	public LocalDateTime getDateUpdated() {
		return dateUpdated;
	}


	public void setDateUpdated(LocalDateTime dateUpdated) {
		this.dateUpdated = dateUpdated;
	}


	@Override
	public String toString() {
		return "ProfileModel [profileID=" + profileID + ", user=" + user + ", rentalHistory=" + rentalHistory
				+ ", dateUpdated=" + dateUpdated + "]";
	}

    

}
