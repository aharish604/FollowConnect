package com.appcare.followconnect.Profile.Bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class ProfileBeanResponse  implements Serializable {


    @SerializedName("message")
    @Expose
    private String message="";

    @SerializedName("status")
    @Expose
    private boolean status;

    @SerializedName("data")
    @Expose
    private ArrayList<ProfileResponseBean1> data;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public ArrayList<ProfileResponseBean1> getData() {
        return data;
    }

    public void setData(ArrayList<ProfileResponseBean1> data) {
        this.data = data;
    }
}
