package com.appcare.followconnect.Profile.FriendsList.Bean;

import com.appcare.followconnect.MyviewPostdisplay.bean.Id;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FollowersProfileDetailsBean {

    @SerializedName("_id")
    @Expose
    private Id id;

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public String getSts() {
        return sts;
    }

    public void setSts(String sts) {
        this.sts = sts;
    }

    public String getAuid() {
        return auid;
    }

    public void setAuid(String auid) {
        this.auid = auid;
    }

    @SerializedName("uid")
    @Expose
    private String uid;
    @SerializedName("fid")
    @Expose
    private String fid;
    @SerializedName("sts")
    @Expose
    private String sts;
    @SerializedName("auid")
    @Expose
    private String auid;
}
