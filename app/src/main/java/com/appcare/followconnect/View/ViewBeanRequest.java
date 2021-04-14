package com.appcare.followconnect.View;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ViewBeanRequest {

    @SerializedName("uid")
    @Expose
    private String uid;
    @SerializedName("feed_id")
    @Expose
    private String feedId;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getFeedId() {
        return feedId;
    }

    public void setFeedId(String feedId) {
        this.feedId = feedId;
    }


}
