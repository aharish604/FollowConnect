package com.appcare.followconnect.Settings.BlockerList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UnblockRequestBean {
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBlockId() {
        return blockId;
    }

    public void setBlockId(String blockId) {
        this.blockId = blockId;
    }

    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("block_id")
    @Expose
    private String blockId;

}
