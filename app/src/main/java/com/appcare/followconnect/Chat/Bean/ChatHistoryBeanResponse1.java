package com.appcare.followconnect.Chat.Bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChatHistoryBeanResponse1 {

    @SerializedName("_id")
    @Expose
    private String id="";
    @SerializedName("from_id")
    @Expose
    private String fromId="";
    @SerializedName("to_id")
    @Expose
    private String toId="";
    @SerializedName("description")
    @Expose
    private String description="";
    @SerializedName("create_at")
    @Expose
    private String createAt="";
    @SerializedName("image")
    @Expose
    private String image="";
    @SerializedName("read_count")
    @Expose
    private String readCount="";
    @SerializedName("chat")
    @Expose
    private String chat="";
    @SerializedName("n_count")
    @Expose
    private String nCount="";
    @SerializedName("user_id")
    @Expose
    private String userId="";
    @SerializedName("profile_pic")
    @Expose
    private String profilePic="";
    @SerializedName("fullname")
    @Expose
    private String fullname="";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFromId() {
        return fromId;
    }

    public void setFromId(String fromId) {
        this.fromId = fromId;
    }

    public String getToId() {
        return toId;
    }

    public void setToId(String toId) {
        this.toId = toId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getReadCount() {
        return readCount;
    }

    public void setReadCount(String readCount) {
        this.readCount = readCount;
    }

    public String getChat() {
        return chat;
    }

    public void setChat(String chat) {
        this.chat = chat;
    }

    public String getNCount() {
        return nCount;
    }

    public void setNCount(String nCount) {
        this.nCount = nCount;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

}
