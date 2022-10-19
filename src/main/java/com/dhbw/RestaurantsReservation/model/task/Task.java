package com.dhbw.RestaurantsReservation.model.task;

public class Task {

	private String eMail = "";
	private String userPassword = "";
	private int userID = 0;

	private int userAccount = 0;

	public Task() {
		
	}

	public Task(String eMail, String userPassword, int userID, int userAccount) {
		super();
		this.eMail = eMail;
		this.userPassword = userPassword;
		this.userID = userID;
		this.userAccount = userAccount;


	}

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


	public int getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(int userAccount) {
		this.userID = userID;
	}

	
	
	
}
