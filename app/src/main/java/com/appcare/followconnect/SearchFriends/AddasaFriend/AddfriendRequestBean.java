package com.appcare.followconnect.SearchFriends.AddasaFriend;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddfriendRequestBean {
/*
    {
        "uid": "210415123735",
            "email": "uuu@gmail.com"
    }*/


    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @SerializedName("uid")
    @Expose
    private String uid;

     @SerializedName("email")
    @Expose
    private String email;



}
