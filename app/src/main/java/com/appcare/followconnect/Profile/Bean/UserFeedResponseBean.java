package com.appcare.followconnect.Profile.Bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class UserFeedResponseBean {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("posts")
    @Expose
    private Integer posts;
    @SerializedName("friends")
    @Expose
    private Integer friends;
    @SerializedName("followers")
    @Expose
    private Integer followers;

    @SerializedName("user_info")
    @Expose
    private ArrayList<feedUserInfo> userInfo = null;

    @SerializedName("data")
    @Expose
    private ArrayList<UserfeedResponseBean1> data = null;

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

    public Integer getPosts() {
        return posts;
    }

    public void setPosts(Integer posts) {
        this.posts = posts;
    }

    public Integer getFriends() {
        return friends;
    }

    public void setFriends(Integer friends) {
        this.friends = friends;
    }

    public Integer getFollowers() {
        return followers;
    }

    public void setFollowers(Integer followers) {
        this.followers = followers;
    }

    public ArrayList<feedUserInfo> getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(ArrayList<feedUserInfo> userInfo) {
        this.userInfo = userInfo;
    }

    public ArrayList<UserfeedResponseBean1> getData() {
        return data;
    }

    public void setData(ArrayList<UserfeedResponseBean1> data) {
        this.data = data;
    }


}
