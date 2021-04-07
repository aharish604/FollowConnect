package com.appcare.followconnect.SearchFriends.Bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SearchDataInsertBeanResponse  implements Serializable {

   /* {
        "status": true,
            "message": "data inserted"
    }
    */


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

    @SerializedName("message")
    @Expose
    private String message="";

}
