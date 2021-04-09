package com.appcare.followconnect.MyviewPostdisplay;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class FeedLikeResponse {
    @SerializedName("status")
    @Expose
    private boolean status;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("data")
    @Expose
    private ArrayList<CommentPosted> data;

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

    public ArrayList<CommentPosted> getData() {
        return data;
    }

    public void setData(ArrayList<CommentPosted> data) {
        this.data = data;
    }

    public class CommentPosted{

        @SerializedName("_id")
        @Expose
        private String _id;

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

        @SerializedName("share")
        @Expose
        private String share;

        @SerializedName("comment")
        @Expose
        private String comment;

        @SerializedName("view")
        @Expose
        private String view;

        @SerializedName("dislike")
        @Expose
        private String dislike;

        @SerializedName("cd")
        @Expose
        private String cd;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
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

        public String getView() {
            return view;
        }

        public void setView(String view) {
            this.view = view;
        }

        public String getDislike() {
            return dislike;
        }

        public void setDislike(String dislike) {
            this.dislike = dislike;
        }

        public String getCd() {
            return cd;
        }

        public void setCd(String cd) {
            this.cd = cd;
        }
    }
}