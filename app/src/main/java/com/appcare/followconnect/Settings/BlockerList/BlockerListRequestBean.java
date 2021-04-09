package com.appcare.followconnect.Settings.BlockerList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BlockerListRequestBean {

    @SerializedName("uid")
    @Expose
    public String uid= "";

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
