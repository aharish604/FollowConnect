package com.appcare.followconnect.Settings.ChnagePassword;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChangePasswordRequestBean {


  /*  {
        "password":"123456",
            "cpassword":"123456",
            "currentpassword":"1234567",
            "email":"naresh@appcare.co.in",
            "user_id":"210107091749"
    }
    */

    @SerializedName("password")
    @Expose
    private String password = "";

    @SerializedName("cpassword")
    @Expose
    private String cpassword = "";

    @SerializedName("currentpassword")
    @Expose
    private String currentpassword = "";


    @SerializedName("email")
    @Expose
    private String email = "";

    @SerializedName("user_id")
    @Expose
    private String user_id = "";


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCpassword() {
        return cpassword;
    }

    public void setCpassword(String cpassword) {
        this.cpassword = cpassword;
    }

    public String getCurrentpassword() {
        return currentpassword;
    }

    public void setCurrentpassword(String currentpassword) {
        this.currentpassword = currentpassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }




}
