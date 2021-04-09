package com.appcare.followconnect.Settings;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AccountDeleteRequestBean {

    @SerializedName("uid")
    @Expose
    private String uid;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
