package com.appcare.followconnect.InToTo.Bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class IntotoRequestBean {

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
