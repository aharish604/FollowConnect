package com.appcare.followconnect.SearchFriends.Bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserNameSearchBeanRequest {

  /*  {
        "uid":"userid",
            "username":"username"
    }*/

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @SerializedName("uid")
    @Expose
    private String uid;

    @SerializedName("username")
    @Expose
    private String username;

}
