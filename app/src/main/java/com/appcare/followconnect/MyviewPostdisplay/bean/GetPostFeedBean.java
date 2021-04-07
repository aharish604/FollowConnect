
package com.appcare.followconnect.MyviewPostdisplay.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetPostFeedBean {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("devicetype")
    @Expose
    private String devicetype;
    @SerializedName("devicetoken")
    @Expose
    private String devicetoken;
    @SerializedName("deviceid")
    @Expose
    private String deviceid;
    @SerializedName("social_uid")
    @Expose
    private String socialUid;
    @SerializedName("fullname")
    @Expose
    private String fullname;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("profile_pic")
    @Expose
    private String profilePic;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("dob")
    @Expose
    private String dob;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("account_status")
    @Expose
    private String accountStatus;
    @SerializedName("cd")
    @Expose
    private String cd;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("flag")
    @Expose
    private String flag;
    @SerializedName("CountryCode")
    @Expose
    private String countryCode;
    @SerializedName("feed_list")
    @Expose
    private FeedList feedList;
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
    @SerializedName("friend_status")
    @Expose
    private String friendStatus;
    @SerializedName("likes")
    @Expose
    private Integer likes;
    @SerializedName("dislike")
    @Expose
    private Integer dislike;
    @SerializedName("viwes")
    @Expose
    private Integer viwes;
    @SerializedName("af_thumb_file")
    @Expose
    private String afThumbFile;
    @SerializedName("vf_file")
    @Expose
    private String vfFile;
    @SerializedName("imgf_file")
    @Expose
    private String imgfFile;
    @SerializedName("vf_thumb_file")
    @Expose
    private String vfThumbFile;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDevicetype() {
        return devicetype;
    }

    public void setDevicetype(String devicetype) {
        this.devicetype = devicetype;
    }

    public String getDevicetoken() {
        return devicetoken;
    }

    public void setDevicetoken(String devicetoken) {
        this.devicetoken = devicetoken;
    }

    public String getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid;
    }

    public String getSocialUid() {
        return socialUid;
    }

    public void setSocialUid(String socialUid) {
        this.socialUid = socialUid;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public String getCd() {
        return cd;
    }

    public void setCd(String cd) {
        this.cd = cd;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public FeedList getFeedList() {
        return feedList;
    }

    public void setFeedList(FeedList feedList) {
        this.feedList = feedList;
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

    public Integer getViwes() {
        return viwes;
    }

    public void setViwes(Integer viwes) {
        this.viwes = viwes;
    }

    public String getAfThumbFile() {
        return afThumbFile;
    }

    public void setAfThumbFile(String afThumbFile) {
        this.afThumbFile = afThumbFile;
    }

    public String getVfFile() {
        return vfFile;
    }

    public void setVfFile(String vfFile) {
        this.vfFile = vfFile;
    }

    public String getImgfFile() {
        return imgfFile;
    }

    public void setImgfFile(String imgfFile) {
        this.imgfFile = imgfFile;
    }

    public String getVfThumbFile() {
        return vfThumbFile;
    }

    public void setVfThumbFile(String vfThumbFile) {
        this.vfThumbFile = vfThumbFile;
    }

}
