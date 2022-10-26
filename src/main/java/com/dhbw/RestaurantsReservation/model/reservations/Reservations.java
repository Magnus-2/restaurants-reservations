package com.dhbw.RestaurantsReservation.model.reservations;

public class Reservations {

private String firstName ="";
private String lastName ="";
private String email ="";
private String phoneNumber ="";
private int userId = 0;
private String date ="";
private String time ="";
private int rSeats = 0;
private int restaurantId = 0;
private String rName ="";
private int rZipcode = 0;
private String rAddress ="";
private String rPhoneNumber ="";
private String rEmail ="";


public Reservations(){

}

public  Reservations(String firstName, String lastName, String email, String phoneNumber, int userId, String date,
                     String time, int rSeats,int restaurantId, String rName, int rZipcode, String rAddress,
                     String rPhoneNumber, String rEmail ){
    super();
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.userId = userId;
    this. date = date;
    this.time = time;
    this.rSeats = rSeats;
    this.restaurantId = restaurantId;
    this.rName = rName;
    this.rZipcode = rZipcode;
    this.rAddress = rAddress;
    this. rPhoneNumber = rPhoneNumber;
    this.rEmail = rEmail;

}

public Reservations (String date,String time, int rSeats, String rName){
    super();
    this. date = date;
    this.time = time;
    this.rSeats = rSeats;
    this.rName = rName;
}

public  Reservations (String firstName, String rName){
    this.firstName = firstName;
    this.rName = rName;
}

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getEmail() {
        return email;
    }
    public void setEmail(String Email) {
        this.email = Email;
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }


    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }


    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }


    public int getrSeats() {
        return rSeats;
    }
    public void setrSeats(int rSeats) {
        this.rSeats = rSeats;
    }


    public int getRestaurantId() {
        return restaurantId;
    }
    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }


    public String getrName() {
        return rName;
    }
    public void setrName(String rName) {
        this.rName = rName;
    }


    public int getrZipcode() {
        return rZipcode;
    }
    public void setrZipcode(int rZipcode) {
        this.rZipcode = rZipcode;
    }


    public String getrAddress() {
        return rAddress;
    }
    public void setrAddress(String rAddress) {
        this.rAddress = rAddress;
    }


    public String getrPhoneNumber() {
        return rPhoneNumber;
    }
    public void setrPhoneNumber(String rPhoneNumber) {
        this.rPhoneNumber = rPhoneNumber;
    }


    public String getrEmail() {
        return rEmail;
    }
    public void setrEmail(String rEmail) {
        this.rEmail = rEmail;
    }
}
