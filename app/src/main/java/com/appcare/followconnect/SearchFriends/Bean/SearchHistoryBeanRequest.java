package com.appcare.followconnect.SearchFriends.Bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchHistoryBeanRequest {

   /* {
        "uid":"210107091749"
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
