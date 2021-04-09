package com.appcare.followconnect.Comments.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CommentsListInputs {
    @SerializedName("feed_id")
    @Expose
    private String feed_id;

    public String getFeed_id() {
        return feed_id;
    }

    public void setFeed_id(String feed_id) {
        this.feed_id = feed_id;
    }
}
