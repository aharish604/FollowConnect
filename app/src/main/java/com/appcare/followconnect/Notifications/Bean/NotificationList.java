package com.appcare.followconnect.Notifications.Bean;

import com.appcare.followconnect.MyviewPostdisplay.bean.Id;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NotificationList {

    @SerializedName("_id")
    @Expose
    private Id id;
    @SerializedName("sender_id")
    @Expose
    private String senderId;
    @SerializedName("receiver_id")
    @Expose
    private String receiverId;
    @SerializedName("ntype")
    @Expose
    private String ntype;
    @SerializedName("ndetails")
    @Expose
    private String ndetails;
    @SerializedName("idref")
    @Expose
    private String idref;
    @SerializedName("cd")
    @Expose
    private String cd;

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public String getNtype() {
        return ntype;
    }

    public void setNtype(String ntype) {
        this.ntype = ntype;
    }

    public String getNdetails() {
        return ndetails;
    }

    public void setNdetails(String ndetails) {
        this.ndetails = ndetails;
    }

    public String getIdref() {
        return idref;
    }

    public void setIdref(String idref) {
        this.idref = idref;
    }

    public String getCd() {
        return cd;
    }

    public void setCd(String cd) {
        this.cd = cd;
    }


}
