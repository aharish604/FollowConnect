package com.appcare.followconnect.SearchFriends.followandunfollow;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class followrequestbean {

    @SerializedName("uid")
    @Expose
    private String uid;

    @SerializedName("fid")
    @Expose
    private String fid;

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
}
