package com.appcare.followconnect.Chat.Bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ChatListBeanResponse1 implements Serializable {
    @Override
    public String toString() {
        return "ChatListBeanResponse1{" +
                "id=" + id +
                ", fromId='" + fromId + '\'' +
                ", toId='" + toId + '\'' +
                ", createAt='" + createAt + '\'' +
                ", description='" + description + '\'' +
                ", userId='" + userId + '\'' +
                ", profilePic='" + profilePic + '\'' +
                ", fullname='" + fullname + '\'' +
                ", username='" + username + '\'' +
                ", cd='" + cd + '\'' +
                ", chatHistory=" + chatHistory +
                '}';
    }

    @SerializedName("_id")
    @Expose
    private transient  Id_chatBean id;
    @SerializedName("from_id")
    @Expose
    private String fromId;
    @SerializedName("to_id")
    @Expose
    private String toId;
    @SerializedName("create_at")
    @Expose
    private String createAt;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("profile_pic")
    @Expose
    private String profilePic;
    @SerializedName("fullname")
    @Expose
    private String fullname;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("cd")
    @Expose
    private String cd;
    @SerializedName("chat_history")
    @Expose
    private transient  ChatHistoryBean chatHistory;

    public Id_chatBean getId() {
        return id;
    }

    public void setId(Id_chatBean id) {
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

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCd() {
        return cd;
    }

    public void setCd(String cd) {
        this.cd = cd;
    }

    public ChatHistoryBean getChatHistory() {
        return chatHistory;
    }

    public void setChatHistory(ChatHistoryBean chatHistory) {
        this.chatHistory = chatHistory;
    }
}
