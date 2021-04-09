package com.appcare.followconnect.Settings.BlockerList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UnblockResponseBean {

   /* {
        "status": true,
            "message": "user  unblocked",
            "data": "1"
    }
*/
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private String data;

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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }


}
