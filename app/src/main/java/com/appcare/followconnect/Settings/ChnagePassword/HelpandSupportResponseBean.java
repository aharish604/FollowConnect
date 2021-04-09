package com.appcare.followconnect.Settings.ChnagePassword;

import com.appcare.followconnect.Settings.HelpandSuppordResponseBean1;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class HelpandSupportResponseBean {

    @SerializedName("status")
    @Expose
    private Boolean status;

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

    public ArrayList<HelpandSuppordResponseBean1> getData() {
        return data;
    }

    public void setData(ArrayList<HelpandSuppordResponseBean1> data) {
        this.data = data;
    }

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private ArrayList<HelpandSuppordResponseBean1> data = null;

}
