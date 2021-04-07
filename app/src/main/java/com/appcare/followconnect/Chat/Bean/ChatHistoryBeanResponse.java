package com.appcare.followconnect.Chat.Bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ChatHistoryBeanResponse {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message="";
    @SerializedName("data")
    @Expose
    private ArrayList<ChatHistoryBeanResponse1> data = null;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public ArrayList<ChatHistoryBeanResponse1> getData() {
        return data;
    }

    public void setData(ArrayList<ChatHistoryBeanResponse1> data) {
        this.data = data;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
