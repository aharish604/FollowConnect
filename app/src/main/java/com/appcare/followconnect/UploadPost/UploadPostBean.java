package com.appcare.followconnect.UploadPost;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.File;

public class UploadPostBean {

   /* uid:200921060018
    feedtext:Hello this is first post
    address:hyderabad
     API-KEY = "827ccb0eea8a706c4c34a16891f84e7b"
   isspoolvid= "No"
    privicy:public
    images[]:image.png
    videos[]:video.mp4,video2.mp4



    uid = "210322104707"
            feedtext =  "Harish"
      privicy = "pvtVid"
        API-KEY = "827ccb0eea8a706c4c34a16891f84e7b"
            isspoolvid= "No"
             images[]:image.png
    videos[]:video.mp4,video2.mp4



    */

    public String getVideopath() {
        return videopath;
    }

    public void setVideopath(String videopath) {
        this.videopath = videopath;
    }

    @SerializedName("uid")
    @Expose
    private String uid = "";
 @SerializedName("videopath")
    @Expose
    private String videopath = "";

    @SerializedName("feedtext")
    @Expose
    private String feedtext = "";

    @SerializedName("address")
    @Expose
    private String address = "";

    @SerializedName("ltd")
    @Expose
    private String ltd = "";

    @SerializedName("lng")
    @Expose
    private String lng = "";

    @SerializedName("privicy")
    @Expose
    private String privicy = "";

    @SerializedName("images")
    @Expose
    private String images[];


    @SerializedName("videos")
    @Expose
    private String videos[];
 @SerializedName("isspoolvid")
    @Expose
    private String isspoolvid="";

    public String getIsspoolvid() {
        return isspoolvid;
    }

    public void setIsspoolvid(String isspoolvid) {
        this.isspoolvid = isspoolvid;
    }

    public String getUid() {
        return uid;
    }

    public String[] getImages() {
        return images;
    }

    public void setImages(String[] images) {
        this.images = images;
    }

    public String[] getVideos() {
        return videos;
    }

    public void setVideos(String[] videos) {
        this.videos = videos;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getFeedtext() {
        return feedtext;
    }

    public void setFeedtext(String feedtext) {
        this.feedtext = feedtext;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLtd() {
        return ltd;
    }

    public void setLtd(String ltd) {
        this.ltd = ltd;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getPrivicy() {
        return privicy;
    }

    public void setPrivicy(String privicy) {
        this.privicy = privicy;
    }


}
