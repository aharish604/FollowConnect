package com.appcare.followconnect.Profile.Bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProfileBeanRequest {

    @SerializedName("user_id")
        @Expose
        public String user_id = "";

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

}
