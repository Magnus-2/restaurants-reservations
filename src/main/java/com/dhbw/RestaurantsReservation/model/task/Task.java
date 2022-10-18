package com.dhbw.RestaurantsReservation.model.task;

public class Task {

	/*private String name = "";
	private String description = "";
	private int priority = 0;
	*/

	private String userName;
	private String userSureName;
	private String userPassword;
	private String eMail;
	private Integer userID;

	public Task() {
		
	}

	public Task(String userName, String userSureName, String userPassword, String eMail, Integer userID) {
		this.userName = userName;
		this.userSureName = userSureName;
		this.userPassword = userPassword;
		this.eMail = eMail;
		this.userID = userID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserSureName() {
		return userSureName;
	}

	public void setUserSureName(String userSureName) {
		this.userSureName = userSureName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String  geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}




	/*
	public Task(String name, String description, int priority) {
		super();
		this.name = name;
		this.description = description;
		this.priority = priority;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}
*/
	
	
	
}
