
package com.appcare.followconnect.MyviewPostdisplay.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FeedList {

    @SerializedName("_id")
    @Expose
    private Id id;
    @SerializedName("sid")
    @Expose
    private String sid;
    @SerializedName("puid")
    @Expose
    private String puid;
    @SerializedName("feed")
    @Expose
    private String feed;
    @SerializedName("imgf")
    @Expose
    private String imgf;
    @SerializedName("vf")
    @Expose
    private String vf;
    @SerializedName("ltd")
    @Expose
    private Object ltd;
    @SerializedName("lng")
    @Expose
    private Object lng;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("prvcy")
    @Expose
    private String prvcy;
    @SerializedName("feed_type")
    @Expose
    private String feedType;
    @SerializedName("cd")
    @Expose
    private String cd;
    @SerializedName("af_thumb")
    @Expose
    private String afThumb;
    @SerializedName("vf_thumb")
    @Expose
    private String vfThumb;
    @SerializedName("isspoolvid")
    @Expose
    private String isspoolvid;
    @SerializedName("ssid")
    @Expose
    private String ssid;
    @SerializedName("wpost")
    @Expose
    private String wpost;
    @SerializedName("sts")
    @Expose
    private String sts;
    @SerializedName("af")
    @Expose
    private String af;
    @SerializedName("date")
    @Expose
    private String date;

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getPuid() {
        return puid;
    }

    public void setPuid(String puid) {
        this.puid = puid;
    }

    public String getFeed() {
        return feed;
    }

    public void setFeed(String feed) {
        this.feed = feed;
    }

    public String getImgf() {
        return imgf;
    }

    public void setImgf(String imgf) {
        this.imgf = imgf;
    }

    public String getVf() {
        return vf;
    }

    public void setVf(String vf) {
        this.vf = vf;
    }

    public Object getLtd() {
        return ltd;
    }

    public void setLtd(Object ltd) {
        this.ltd = ltd;
    }

    public Object getLng() {
        return lng;
    }

    public void setLng(Object lng) {
        this.lng = lng;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPrvcy() {
        return prvcy;
    }

    public void setPrvcy(String prvcy) {
        this.prvcy = prvcy;
    }

    public String getFeedType() {
        return feedType;
    }

    public void setFeedType(String feedType) {
        this.feedType = feedType;
    }

    public String getCd() {
        return cd;
    }

    public void setCd(String cd) {
        this.cd = cd;
    }

    public String getAfThumb() {
        return afThumb;
    }

    public void setAfThumb(String afThumb) {
        this.afThumb = afThumb;
    }

    public String getVfThumb() {
        return vfThumb;
    }

    public void setVfThumb(String vfThumb) {
        this.vfThumb = vfThumb;
    }

    public String getIsspoolvid() {
        return isspoolvid;
    }

    public void setIsspoolvid(String isspoolvid) {
        this.isspoolvid = isspoolvid;
    }

    public String getSsid() {
        return ssid;
    }

    public void setSsid(String ssid) {
        this.ssid = ssid;
    }

    public String getWpost() {
        return wpost;
    }

    public void setWpost(String wpost) {
        this.wpost = wpost;
    }

    public String getSts() {
        return sts;
    }

    public void setSts(String sts) {
        this.sts = sts;
    }

    public String getAf() {
        return af;
    }

    public void setAf(String af) {
        this.af = af;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
