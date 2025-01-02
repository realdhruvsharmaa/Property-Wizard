package com.user.model;

public class User {

	private int userID;

	private String name;

	private String email;

	private String password;

	private String role;

	private int dateCreated;

	private String lastLogin;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(int userID, String name, String email, String password, String role, int dateCreated,
			String lastLogin) {
		super();
		this.userID = userID;
		this.name = name;
		this.email = email;
		this.password = password;
		this.role = role;
		this.dateCreated = dateCreated;
		this.lastLogin = lastLogin;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(int dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(String lastLogin) {
		this.lastLogin = lastLogin;
	}

	@Override
	public String toString() {
		return "User [userID=" + userID + ", name=" + name + ", email=" + email + ", password=" + password + ", role="
				+ role + ", dateCreated=" + dateCreated + ", lastLogin=" + lastLogin + "]";
	}

}
