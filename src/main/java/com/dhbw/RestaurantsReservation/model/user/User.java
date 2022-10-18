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
