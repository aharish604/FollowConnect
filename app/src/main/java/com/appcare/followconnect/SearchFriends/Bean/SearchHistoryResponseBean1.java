package com.appcare.followconnect.SearchFriends.Bean;

import com.appcare.followconnect.MyviewPostdisplay.bean.Id;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchHistoryResponseBean1 {


    @SerializedName("_id")
    @Expose
    private Idfname id;
    @SerializedName("fullname")
    @Expose
    private String fullname;
    @SerializedName("profile_pic")
    @Expose
    private String profilePic;
    @SerializedName("user_id")
    @Expose
    private String userId;

    public Idfname getId() {
        return id;
    }

    public void setId(Idfname id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


}
