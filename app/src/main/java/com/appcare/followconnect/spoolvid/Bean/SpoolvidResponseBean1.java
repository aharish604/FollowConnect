package com.appcare.followconnect.spoolvid.Bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SpoolvidResponseBean1 {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("lng")
    @Expose
    private String lng;
    @SerializedName("CountryCode")
    @Expose
    private String countryCode;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("fullname")
    @Expose
    private String fullname;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("lat")
    @Expose
    private String lat;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("devicetoken")
    @Expose
    private String devicetoken;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("deviceid")
    @Expose
    private String deviceid;
    @SerializedName("devicetype")
    @Expose
    private String devicetype;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("dob")
    @Expose
    private String dob;
    @SerializedName("ip")
    @Expose
    private String ip;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("account_status")
    @Expose
    private String accountStatus;
    @SerializedName("profile_pic")
    @Expose
    private String profilePic;
    @SerializedName("cd")
    @Expose
    private String cd;
    @SerializedName("uotp")
    @Expose
    private String uotp;
    @SerializedName("flag")
    @Expose
    private String flag;
    @SerializedName("feed_list")
    @Expose
    private SpoolvidResponseBeanfeedlist feedList;
    @SerializedName("whosename")
    @Expose
    private String whosename;


    @SerializedName("comments_count")
    @Expose
    private Integer commentsCount;
    @SerializedName("share_count")
    @Expose
    private Integer shareCount;
    @SerializedName("likes_count")
    @Expose
    private Integer likesCount;
    @SerializedName("dislike_count")
    @Expose
    private Integer dislikeCount;
    @SerializedName("views")
    @Expose
    private Integer views;
    @SerializedName("friend_status")
    @Expose
    private String friendStatus;
    @SerializedName("likes")
    @Expose
    private Integer likes;
    @SerializedName("dislike")
    @Expose
    private Integer dislike;
    @SerializedName("vf")
    @Expose
    private String vf;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDevicetoken() {
        return devicetoken;
    }

    public void setDevicetoken(String devicetoken) {
        this.devicetoken = devicetoken;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid;
    }

    public String getDevicetype() {
        return devicetype;
    }

    public void setDevicetype(String devicetype) {
        this.devicetype = devicetype;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getCd() {
        return cd;
    }

    public void setCd(String cd) {
        this.cd = cd;
    }

    public String getUotp() {
        return uotp;
    }

    public void setUotp(String uotp) {
        this.uotp = uotp;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }


    public String getWhosename() {
        return whosename;
    }

    public void setWhosename(String whosename) {
        this.whosename = whosename;
    }

    public Integer getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(Integer commentsCount) {
        this.commentsCount = commentsCount;
    }

    public Integer getShareCount() {
        return shareCount;
    }

    public void setShareCount(Integer shareCount) {
        this.shareCount = shareCount;
    }

    public Integer getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(Integer likesCount) {
        this.likesCount = likesCount;
    }

    public Integer getDislikeCount() {
        return dislikeCount;
    }

    public void setDislikeCount(Integer dislikeCount) {
        this.dislikeCount = dislikeCount;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public String getFriendStatus() {
        return friendStatus;
    }

    public void setFriendStatus(String friendStatus) {
        this.friendStatus = friendStatus;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Integer getDislike() {
        return dislike;
    }

    public void setDislike(Integer dislike) {
        this.dislike = dislike;
    }

    public String getVf() {
        return vf;
    }

    public void setVf(String vf) {
        this.vf = vf;
    }

    public SpoolvidResponseBeanfeedlist getFeedList() {
        return feedList;
    }

    public void setFeedList(SpoolvidResponseBeanfeedlist feedList) {
        this.feedList = feedList;
    }

}
