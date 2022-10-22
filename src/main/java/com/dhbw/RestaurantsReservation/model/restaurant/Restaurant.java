package com.dhbw.RestaurantsReservation.model.restaurant;

public class Restaurant {

    private String rName = "";
    private int rSeats = 0;
    private int rZipcode = 0;
    private String rAddress = "";
    private String rCategory ="";
    private int rPhone = 0;
    private String rEmail = "";
    private String rPassword = "";

    public Restaurant(){

    }

    public Restaurant(String rName, int rSeats, int rZipcode, String rAddress, String rCategory,
                        int rPhone, String rEmail, String rPassword){

        super();
        this.rName = rName;
        this.rSeats = rSeats;
        this.rZipcode = rZipcode;
        this.rAddress = rAddress;
        this.rCategory = rCategory;
        this.rPhone = rPhone;
        this.rEmail = rEmail;
        this.rPassword = rPassword;
    }

    public String getrName() {
        return rName;
    }
    public void setrName(String rName) {
        this.rName = rName;
    }


    public int getrSeats() {
        return rSeats;
    }
    public void setrSeats(int rSeats) {
        this.rSeats = rSeats;
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


    public String getrCategory() {
        return rCategory;
    }
    public void setrCategory(String rCategory) {
        this.rCategory = rCategory;
    }


    public int getrPhone() {
        return rPhone;
    }
    public void setrPhone(int rPhone) {
        this.rPhone = rPhone;
    }


    public String getrEmail() {
        return rEmail;
    }
    public void setrEmail(String rEmail) {
        this.rEmail = rEmail;
    }


    public String getrPassword() {
        return rPassword;
    }
    public void setrPassword(String rPassword) {
        this.rPassword = rPassword;
    }


}
