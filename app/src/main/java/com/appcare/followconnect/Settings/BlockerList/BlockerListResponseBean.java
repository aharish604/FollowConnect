package com.appcare.followconnect.Settings.BlockerList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class BlockerListResponseBean {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private ArrayList<BlockerListResponseBean1> data = null;


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

    public ArrayList<BlockerListResponseBean1> getData() {
        return data;
    }

    public void setData(ArrayList<BlockerListResponseBean1> data) {
        this.data = data;
    }


}
