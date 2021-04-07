package com.appcare.followconnect.MyviewPostdisplay.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetPostRequestBean {
/*
    {
        "uid":"210322104707"
    }*/

    @SerializedName("uid")
    @Expose
    private String uid;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }



}
