package com.appcare.followconnect.SearchFriends.followandunfollow;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class followResponseBean {
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private Data data;
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

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }





    public class Data {

        @SerializedName("uid")
        @Expose
        private String uid;
        @SerializedName("fid")
        @Expose
        private String fid;

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getFid() {
            return fid;
        }

        public void setFid(String fid) {
            this.fid = fid;
        }

    }

}
