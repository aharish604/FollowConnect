package com.appcare.followconnect.InToTo.Bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class intotoprivate {

    @SerializedName("_id")
    @Expose
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



}
