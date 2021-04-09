package com.appcare.followconnect.Comments.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CommentsListResponse {
    @SerializedName("status")
    @Expose
    private boolean status;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("data")
    @Expose
    private ArrayList<CommentData> data;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<CommentData> getData() {
        return data;
    }

    public void setData(ArrayList<CommentData> data) {
        this.data = data;
    }

    public class CommentData{

        @SerializedName("_id")
        @Expose
        private String _id;

        @SerializedName("devicetype")
        @Expose
        private String devicetype;

        @SerializedName("gender")
        @Expose
        private String gender;

        @SerializedName("username")
        @Expose
        private String username;

        @SerializedName("dob")
        @Expose
        private String dob;

        @SerializedName("devicetoken")
        @Expose
        private String devicetoken;

        @SerializedName("deviceid")
        @Expose
        private String deviceid;

        @SerializedName("fullname")
        @Expose
        private String fullname;

        @SerializedName("password")
        @Expose
        private String password;

        @SerializedName("country")
        @Expose
        private String country;

        @SerializedName("mobile")
        @Expose
        private String mobile;

        @SerializedName("email")
        @Expose
        private String email;

        @SerializedName("user_id")
        @Expose
        private String user_id;

        @SerializedName("account_status")
        @Expose
        private String account_status;

        @SerializedName("profile_pic")
        @Expose
        private String profile_pic;

        @SerializedName("cd")
        @Expose
        private String cd;

        @SerializedName("commenttime")
        @Expose
        private String commenttime;

        @SerializedName("userDetails")
        @Expose
        private UserDetails userDetails;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getDevicetype() {
            return devicetype;
        }

        public void setDevicetype(String devicetype) {
            this.devicetype = devicetype;
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

        public String getDob() {
            return dob;
        }

        public void setDob(String dob) {
            this.dob = dob;
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

        public String getFullname() {
            return fullname;
        }

        public void setFullname(String fullname) {
            this.fullname = fullname;
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

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getAccount_status() {
            return account_status;
        }

        public void setAccount_status(String account_status) {
            this.account_status = account_status;
        }

        public String getProfile_pic() {
            return profile_pic;
        }

        public void setProfile_pic(String profile_pic) {
            this.profile_pic = profile_pic;
        }

        public String getCd() {
            return cd;
        }

        public void setCd(String cd) {
            this.cd = cd;
        }

        public String getCommenttime() {
            return commenttime;
        }

        public void setCommenttime(String commenttime) {
            this.commenttime = commenttime;
        }

        public UserDetails getUserDetails() {
            return userDetails;
        }

        public void setUserDetails(UserDetails userDetails) {
            this.userDetails = userDetails;
        }

        public class UserDetails {

            @SerializedName("_id")
            @Expose
            private SId _id;

            public SId get_id() {
                return _id;
            }

            public void set_id(SId _id) {
                this._id = _id;
            }

            public class SId{

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

            @SerializedName("sid")
            @Expose
            private String sid;

            @SerializedName("puid")
            @Expose
            private String puid;

            @SerializedName("cid")
            @Expose
            private String cid;

            @SerializedName("likes")
            @Expose
            private String likes;

            @SerializedName("dislike")
            @Expose
            private String dislike;

            @SerializedName("share")
            @Expose
            private String share;

            @SerializedName("comment")
            @Expose
            private String comment;

            @SerializedName("cd")
            @Expose
            private String cd;

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

            public String getCid() {
                return cid;
            }

            public void setCid(String cid) {
                this.cid = cid;
            }

            public String getLikes() {
                return likes;
            }

            public void setLikes(String likes) {
                this.likes = likes;
            }

            public String getDislike() {
                return dislike;
            }

            public void setDislike(String dislike) {
                this.dislike = dislike;
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

            public String getCd() {
                return cd;
            }

            public void setCd(String cd) {
                this.cd = cd;
            }
        }

    }
}
