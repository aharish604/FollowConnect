package com.appcare.followconnect.Settings.PrivacyPolicy;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PrivacyPolicyResponeseBean {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private ArrayList<PrivacyPolicyResponseBean1> data = null;

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

    public ArrayList<PrivacyPolicyResponseBean1> getData() {
        return data;
    }

    public void setData(ArrayList<PrivacyPolicyResponseBean1> data) {
        this.data = data;
    }


}
