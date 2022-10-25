package com.dhbw.RestaurantsReservation.model.user;

public class User {

    // StudentenInformationen sind komplett ignoriert
    // TODO
    // alle Aktionen müssten Datenbank-Aktionen mit einschließen
    // UserManager schreiben
    // TaskManager anpassen, so dass er den Student auch nutzt
    //

    private String firstName;
    private String lastName;
    private String uEMail;
    private String phoneNumber;
    private String password;
   /*private String loginedInToken;*/


    public User(){

    }



    public User (String firstName, String lastName, String uEMail, String phoneNumber, String password){
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.uEMail = uEMail;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }


    public User (String uEMail, String password){
        this.uEMail = uEMail;
        this.password = password;
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


    public String getEMail() {
        return uEMail;
    }
    public void setEMail(String uEMail) {
        this.uEMail = uEMail;
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }


/*
    public String getLoginedInToken() {
        return loginedInToken;
    }

    public void setLoginedInToken(String loginedInToken) {
        this.loginedInToken = loginedInToken;
    }

    public void logStudentOff() {
        loginedInToken = "";
    }
*/

}
