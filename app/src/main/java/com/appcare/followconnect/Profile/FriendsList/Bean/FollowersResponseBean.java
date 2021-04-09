package com.appcare.followconnect.Profile.FriendsList.Bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class FollowersResponseBean {


    @SerializedName("status")
    @Expose
    private Boolean status;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<FollowersResponseBean1> getData() {
        return data;
    }

    public void setData(ArrayList<FollowersResponseBean1> data) {
        this.data = data;
    }

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private ArrayList<FollowersResponseBean1> data = null;
}
