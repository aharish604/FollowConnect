package com.appcare.followconnect.Chat.Bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChatHistoryBeanRequest {

    @SerializedName("from_id")
    @Expose
    private String from_id="";

    @SerializedName("to_id")
    @Expose
    private String to_id="";

    public String getFrom_id() {
        return from_id;
    }

    public void setFrom_id(String from_id) {
        this.from_id = from_id;
    }

    public String getTo_id() {
        return to_id;
    }

    public void setTo_id(String to_id) {
        this.to_id = to_id;
    }


}
