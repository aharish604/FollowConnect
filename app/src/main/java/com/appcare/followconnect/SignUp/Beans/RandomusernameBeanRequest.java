package com.appcare.followconnect.SignUp.Beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RandomusernameBeanRequest {

   /* {"fullname":"harish","email":"aharish604@gmail.com"}*/


    @SerializedName("fullname")
    @Expose
    private String fullname="";

    @SerializedName("email")
    @Expose
    private String email="";

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
