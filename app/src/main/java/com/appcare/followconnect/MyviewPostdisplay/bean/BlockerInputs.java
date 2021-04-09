package com.appcare.followconnect.MyviewPostdisplay.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BlockerInputs {
    @SerializedName("user_id")
    @Expose
    private String user_id;

    @SerializedName("block_id")
    @Expose
    private String block_id;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getBlock_id() {
        return block_id;
    }

    public void setBlock_id(String block_id) {
        this.block_id = block_id;
    }

}
