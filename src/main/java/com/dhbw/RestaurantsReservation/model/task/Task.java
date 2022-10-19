package com.dhbw.RestaurantsReservation.model.task;

public class Task {

	private String userName = "";
	private String userSureName = "";
	private String eMail = "";
	private String userPassword = "";
	private int userID = 0;

	public Task() {
		
	}

	public Task(String userName, String userSureName,String eMail, String userPassword, int userID) {
		super();
		this.userName = userName;
		this.userSureName = userSureName;
		this.eMail = eMail;
		this.userPassword = userPassword;
		this.userID = userID;

	}

	public String getUserName() { return userName;}
	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getUserSureName() {return userSureName;}
	public void  setUserSureName(String userSureName){this.userSureName = userSureName;}


	public String geteMail() {
		return eMail;
	}
	public void seteMail(String eMail) {
		this.eMail = eMail;
	}


	public String getUserPassword(){return userPassword;}
	public void setUserPassword(String userPassword){this.userPassword =userPassword;}


	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}

	
	
	
}
