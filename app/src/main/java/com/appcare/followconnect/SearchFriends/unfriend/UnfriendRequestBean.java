package com.appcare.followconnect.SearchFriends.unfriend;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UnfriendRequestBean {

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    @SerializedName("uid")
    @Expose
    private String uid;
    @SerializedName("fid")
    @Expose
    private String fid;

}
