package com.appcare.followconnect.SearchFriends;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserFriendsFeedResponse {
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
    @SerializedName("friend_status")
    @Expose
    private String friendStatus;
    @SerializedName("connection_status")
    @Expose
    private String connectionStatus;
    @SerializedName("followers")
    @Expose
    private Integer followers;
    @SerializedName("user_info")
    @Expose
    private List<UserInfo> userInfo = null;
    @SerializedName("data")
    @Expose
    private List<Datum> data = null;

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

    public String getFriendStatus() {
        return friendStatus;
    }

    public void setFriendStatus(String friendStatus) {
        this.friendStatus = friendStatus;
    }

    public String getConnectionStatus() {
        return connectionStatus;
    }

    public void setConnectionStatus(String connectionStatus) {
        this.connectionStatus = connectionStatus;
    }

    public Integer getFollowers() {
        return followers;
    }

    public void setFollowers(Integer followers) {
        this.followers = followers;
    }

    public List<UserInfo> getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(List<UserInfo> userInfo) {
        this.userInfo = userInfo;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public class UserInfo {

        @SerializedName("_id")
        @Expose
        private String id;
        @SerializedName("devicetype")
        @Expose
        private String devicetype;
        @SerializedName("dob")
        @Expose
        private String dob;
        @SerializedName("gender")
        @Expose
        private String gender;
        @SerializedName("mobile")
        @Expose
        private String mobile;
        @SerializedName("password")
        @Expose
        private String password;
        @SerializedName("country")
        @Expose
        private String country;
        @SerializedName("fullname")
        @Expose
        private String fullname;
        @SerializedName("deviceid")
        @Expose
        private String deviceid;
        @SerializedName("username")
        @Expose
        private String username;
        @SerializedName("devicetoken")
        @Expose
        private String devicetoken;
        @SerializedName("email")
        @Expose
        private String email;
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

        public String getDob() {
            return dob;
        }

        public void setDob(String dob) {
            this.dob = dob;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
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

        public String getDeviceid() {
            return deviceid;
        }

        public void setDeviceid(String deviceid) {
            this.deviceid = deviceid;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getDevicetoken() {
            return devicetoken;
        }

        public void setDevicetoken(String devicetoken) {
            this.devicetoken = devicetoken;
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

    }

    public class FeedList {

        @SerializedName("_id")
        @Expose
        private Id id;
        @SerializedName("sid")
        @Expose
        private String sid;
        @SerializedName("puid")
        @Expose
        private String puid;
        @SerializedName("feed")
        @Expose
        private String feed;
        @SerializedName("imgf")
        @Expose
        private String imgf;
        @SerializedName("vf")
        @Expose
        private String vf;
        @SerializedName("ltd")
        @Expose
        private String ltd;
        @SerializedName("lng")
        @Expose
        private String lng;
        @SerializedName("address")
        @Expose
        private String address;
        @SerializedName("prvcy")
        @Expose
        private String prvcy;
        @SerializedName("feed_type")
        @Expose
        private String feedType;
        @SerializedName("cd")
        @Expose
        private String cd;
        @SerializedName("af_thumb")
        @Expose
        private String afThumb;
        @SerializedName("vf_thumb")
        @Expose
        private String vfThumb;
        @SerializedName("isspoolvid")
        @Expose
        private String isspoolvid;
        @SerializedName("ssid")
        @Expose
        private String ssid;
        @SerializedName("wpost")
        @Expose
        private String wpost;
        @SerializedName("sts")
        @Expose
        private String sts;
        @SerializedName("af")
        @Expose
        private String af;
        @SerializedName("date")
        @Expose
        private String date;


        public Id getId() {
            return id;
        }

        public void setId(Id id) {
            this.id = id;
        }

        public String getSid() {
            return sid;
        }

        public void setSid(String sid) {
            this.sid = sid;
        }

        public String getPuid() {
            return puid;
        }

        public void setPuid(String puid) {
            this.puid = puid;
        }

        public String getFeed() {
            return feed;
        }

        public void setFeed(String feed) {
            this.feed = feed;
        }

        public String getImgf() {
            return imgf;
        }

        public void setImgf(String imgf) {
            this.imgf = imgf;
        }

        public String getVf() {
            return vf;
        }

        public void setVf(String vf) {
            this.vf = vf;
        }

        public String getLtd() {
            return ltd;
        }

        public void setLtd(String ltd) {
            this.ltd = ltd;
        }

        public String getLng() {
            return lng;
        }

        public void setLng(String lng) {
            this.lng = lng;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getPrvcy() {
            return prvcy;
        }

        public void setPrvcy(String prvcy) {
            this.prvcy = prvcy;
        }

        public String getFeedType() {
            return feedType;
        }

        public void setFeedType(String feedType) {
            this.feedType = feedType;
        }

        public String getCd() {
            return cd;
        }

        public void setCd(String cd) {
            this.cd = cd;
        }

        public String getAfThumb() {
            return afThumb;
        }

        public void setAfThumb(String afThumb) {
            this.afThumb = afThumb;
        }

        public String getVfThumb() {
            return vfThumb;
        }

        public void setVfThumb(String vfThumb) {
            this.vfThumb = vfThumb;
        }

        public String getIsspoolvid() {
            return isspoolvid;
        }

        public void setIsspoolvid(String isspoolvid) {
            this.isspoolvid = isspoolvid;
        }

        public String getSsid() {
            return ssid;
        }

        public void setSsid(String ssid) {
            this.ssid = ssid;
        }

        public String getWpost() {
            return wpost;
        }

        public void setWpost(String wpost) {
            this.wpost = wpost;
        }

        public String getSts() {
            return sts;
        }

        public void setSts(String sts) {
            this.sts = sts;
        }

        public String getAf() {
            return af;
        }

        public void setAf(String af) {
            this.af = af;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public class Id {

            @SerializedName("$oid")
            @Expose
            private String $oid;

            public String get$oid() {
                return $oid;
            }

            public void set$oid(String $oid) {
                this.$oid = $oid;
            }

        }
    }

    public class Datum {

        @SerializedName("_id")
        @Expose
        private String id;
        @SerializedName("devicetype")
        @Expose
        private String devicetype;
        @SerializedName("dob")
        @Expose
        private String dob;
        @SerializedName("gender")
        @Expose
        private String gender;
        @SerializedName("mobile")
        @Expose
        private String mobile;
        @SerializedName("password")
        @Expose
        private String password;
        @SerializedName("country")
        @Expose
        private String country;
        @SerializedName("fullname")
        @Expose
        private String fullname;
        @SerializedName("deviceid")
        @Expose
        private String deviceid;
        @SerializedName("username")
        @Expose
        private String username;
        @SerializedName("devicetoken")
        @Expose
        private String devicetoken;
        @SerializedName("email")
        @Expose
        private String email;
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
        @SerializedName("feed_list")
        @Expose
        private FeedList feedList;
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
        @SerializedName("connection_status")
        @Expose
        private String connectionStatus;
        @SerializedName("dislike_count")
        @Expose
        private Integer dislikeCount;
        @SerializedName("viwes")
        @Expose
        private Integer viwes;
        @SerializedName("isdisLike")
        @Expose
        private Integer isdisLike;
        @SerializedName("friend_status")
        @Expose
        private String friendStatus;
        @SerializedName("likes")
        @Expose
        private Integer likes;
        @SerializedName("dislike")
        @Expose
        private Integer dislike;
        @SerializedName("af_thumb")
        @Expose
        private String afThumb;
        @SerializedName("vf")
        @Expose
        private String vf;
        @SerializedName("imgf")
        @Expose
        private String imgf;

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

        public String getDob() {
            return dob;
        }

        public void setDob(String dob) {
            this.dob = dob;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
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

        public String getDeviceid() {
            return deviceid;
        }

        public void setDeviceid(String deviceid) {
            this.deviceid = deviceid;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getDevicetoken() {
            return devicetoken;
        }

        public void setDevicetoken(String devicetoken) {
            this.devicetoken = devicetoken;
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

        public FeedList getFeedList() {
            return feedList;
        }

        public void setFeedList(FeedList feedList) {
            this.feedList = feedList;
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

        public String getConnectionStatus() {
            return connectionStatus;
        }

        public void setConnectionStatus(String connectionStatus) {
            this.connectionStatus = connectionStatus;
        }

        public Integer getDislikeCount() {
            return dislikeCount;
        }

        public void setDislikeCount(Integer dislikeCount) {
            this.dislikeCount = dislikeCount;
        }

        public Integer getViwes() {
            return viwes;
        }

        public void setViwes(Integer viwes) {
            this.viwes = viwes;
        }

        public Integer getIsdisLike() {
            return isdisLike;
        }

        public void setIsdisLike(Integer isdisLike) {
            this.isdisLike = isdisLike;
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

        public String getAfThumb() {
            return afThumb;
        }

        public void setAfThumb(String afThumb) {
            this.afThumb = afThumb;
        }

        public String getVf() {
            return vf;
        }

        public void setVf(String vf) {
            this.vf = vf;
        }

        public String getImgf() {
            return imgf;
        }

        public void setImgf(String imgf) {
            this.imgf = imgf;
        }

    }
}
