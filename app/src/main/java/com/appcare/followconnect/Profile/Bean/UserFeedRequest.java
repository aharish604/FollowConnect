package com.appcare.followconnect.Profile.Bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserFeedRequest {
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
