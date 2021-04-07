package com.appcare.followconnect.ProfileUpdate;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProfileUpdateBeanResponse {

   /* {
        "status": true,
            "message": "Profile updated successfully.",
            "data": true
    }*/


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

    public boolean isData() {
        return data;
    }

    public void setData(boolean data) {
        this.data = data;
    }

    @SerializedName("message")
    @Expose
    public String message = "";


    @SerializedName("status")
    @Expose
    public boolean status ;


    @SerializedName("data")
    @Expose
    public boolean data ;


}
