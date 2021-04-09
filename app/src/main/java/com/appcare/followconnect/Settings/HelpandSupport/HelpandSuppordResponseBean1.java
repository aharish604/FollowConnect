package com.appcare.followconnect.Settings.HelpandSupport;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HelpandSuppordResponseBean1 {
    @SerializedName("_id")
    @Expose
    private String id;


    @SerializedName("discription")
    @Expose
    private String discription;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }


}
