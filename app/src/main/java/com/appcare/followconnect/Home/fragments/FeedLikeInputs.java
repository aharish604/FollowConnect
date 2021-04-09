package com.appcare.followconnect.Home.fragments;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FeedLikeInputs {
    @SerializedName("feed_id")
    @Expose
    private String feed_id;

    @SerializedName("poster_id")
    @Expose
    private String poster_id;

    @SerializedName("commenter_id")
    @Expose
    private String commenter_id;

    @SerializedName("like")
    @Expose
    private String like;

    @SerializedName("share")
    @Expose
    private String share;

    @SerializedName("comment")
    @Expose
    private String comment;

    @SerializedName("dislike")
    @Expose
    private String dislike;

    @SerializedName("view")
    @Expose
    private String view;

    public String getFeed_id() {
        return feed_id;
    }

    public void setFeed_id(String feed_id) {
        this.feed_id = feed_id;
    }

    public String getPoster_id() {
        return poster_id;
    }

    public void setPoster_id(String poster_id) {
        this.poster_id = poster_id;
    }

    public String getCommenter_id() {
        return commenter_id;
    }

    public void setCommenter_id(String commenter_id) {
        this.commenter_id = commenter_id;
    }

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }

    public String getShare() {
        return share;
    }

    public void setShare(String share) {
        this.share = share;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDislike() {
        return dislike;
    }

    public void setDislike(String dislike) {
        this.dislike = dislike;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }
}