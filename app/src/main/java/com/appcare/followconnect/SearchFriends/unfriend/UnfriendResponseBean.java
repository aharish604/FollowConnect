package com.appcare.followconnect.SearchFriends.unfriend;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UnfriendResponseBean {
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

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;
}
