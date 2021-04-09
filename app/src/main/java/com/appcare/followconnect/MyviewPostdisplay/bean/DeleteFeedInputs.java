package com.appcare.followconnect.MyviewPostdisplay.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DeleteFeedInputs {
    @SerializedName("uid")
    @Expose
    private String uid;

    @SerializedName("feed_id")
    @Expose
    private String feed_id;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getFeed_id() {
        return feed_id;
    }

    public void setFeed_id(String feed_id) {
        this.feed_id = feed_id;
    }
}
