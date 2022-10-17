package com.dhbw.RestaurantsReservation.model.user;



public class User {

    // UserInformationen sind komplett ignoriert
    // TODO
    // alle Aktionen müssten Datenbank-Aktionen mit einschließen
    // UserManager schreiben
    // TaskManager anpassen, so dass er den User auch nutzt
    //

    private String userName;
    private String userSureName;
    private String userPassword;
    private String eMail;
    private Integer userID;
    private String loginedInToken;


    public User(String userName, String userSureName, String userPassword, String eMail, Integer userID) {
        this.userName = userName;
        this.userSureName = userSureName;
        this.userPassword = userPassword;
        this.eMail = eMail;
        this.userID = userID;
    }

    public String getuserName() {
        return userName;
    }

    public void setuserName(String userName) {
        this.userName = userName;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getLoginedInToken() {
        return loginedInToken;
    }

    public void setLoginedInToken(String loginedInToken) {
        this.loginedInToken = loginedInToken;
    }

    public void logStudentOff() {
        loginedInToken = "";
    }

}
