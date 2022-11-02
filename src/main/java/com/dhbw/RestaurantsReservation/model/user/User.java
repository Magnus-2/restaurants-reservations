package com.dhbw.RestaurantsReservation.model.user;

public class User {

    // StudentenInformationen sind komplett ignoriert
    // TODO
    // alle Aktionen müssten Datenbank-Aktionen mit einschließen
    // UserManager schreiben
    // TaskManager anpassen, so dass er den Student auch nutzt
    //
    private boolean check = false;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String password;
   /*private String loginedInToken;*/


    public User(){

    }
    public User(boolean check){
            this.check = check;
    }


    public User (String firstName, String lastName, String email, String phoneNumber, String password){
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }
    public User (String email, String password){
        this.email = email;
        this.password = password;
    }
    public User (String email, String password, Boolean check){
        this.email = email;
        this.password = password;
        this.check = check;
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
        return email;
    }
    public void setEMail(String email) {
        this.email = email;
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

    public boolean getCheck(){return check;}
    public void setCheck(boolean check){this.check = check;}

    public String setCheck2(String check){
        this.check = false;
        if(check.equals("true")){
            this.check = true;
            return check;
        } else if (check.equals("false")) {
            this.check =false;
            return check;
        }
        else return "The check faild";
    }


    public String getCheckTrue(){
        this.check = true;
        return " "+ check +" ";
    }

    public String getCheckFalse(){
        this.check = false;
        return " "+ check +" ";
    }
}
