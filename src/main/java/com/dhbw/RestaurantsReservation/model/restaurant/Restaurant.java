package com.dhbw.RestaurantsReservation.model.restaurant;





public class Restaurant {

    private String rName = "";

    private int rSeats = 0;

    private int rZipcode = 0;

    private String rAddress = "";

    private String oHMo = "";
    private String oHTu = "";
    private String oHWe = "";
    private String oHTh = "";
    private String oHFr = "";
    private String oHSa = "";
    private String oHSu = "";

    private String rCategory ="";

    private int rPhone = 0;

    private String rEmail = "";

    private String rPassword = "";

    public Restaurant(){

    }

    public Restaurant(String rName, int rSeats, int rZipcode, String rAddress,
                      String oHMo, String oHTu, String oHWe, String oHTh, String oHFr, String oHSa, String oHSu,
                      String rCategory, int rPhone, String rEmail, String rPassword){

        super();
        this.rName = rName;
        this.rSeats = rSeats;
        this.rZipcode = rZipcode;
        this.rAddress = rAddress;
                this.oHMo = oHMo;
                this.oHTu = oHTu;
                this.oHWe = oHWe;
                this.oHTh = oHTh;
                this.oHFr = oHFr;
                this.oHSa = oHSa;
                this.oHSu = oHSu;
        this.rCategory = rCategory;
        this.rPhone = rPhone;
        this.rEmail = rEmail;
        this.rPassword = rPassword;
    }

    public Restaurant(String rName, String rEmail){
        this.rName = rName;
        this.rEmail = rEmail;
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


                    public String getoHMo() {
                        return oHMo;
                    }
                    public void setoHMo(String oHMo) {
                        this.oHMo = oHMo;
                    }
                    public String getoHTu() {
                        return oHTu;
                    }
                    public void setoHTu(String oHTu) {
                        this.oHTu = oHTu;
                    }
                    public String getoHWe() {
                        return oHWe;
                    }
                    public void setoHWe(String oHWe) {
                        this.oHWe = oHWe;
                    }
                    public String getoHTh() {
                        return oHTh;
                    }
                    public void setoHTh(String oHTh) {
                        this.oHTh = oHTh;
                    }
                    public String getoHFr() {
                        return oHFr;
                    }
                    public void setoHFr(String oHFr) {
                        this.oHFr = oHFr;
                    }
                    public String getoHSa() {
                        return oHSa;
                    }
                    public void setoHSa(String oHSa) {
                        this.oHSa = oHSa;
                    }
                    public String getoHSu() {
                        return oHSu;
                    }
                    public void setoHSu(String oHSu) {
                        this.oHSu = oHSu;
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
