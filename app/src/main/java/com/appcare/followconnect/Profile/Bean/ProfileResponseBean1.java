package com.appcare.followconnect.Profile.Bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ProfileResponseBean1 implements Serializable {

 /*   {
        "status": true,
            "message": "user profile deatils.",
            "data": [
        {
            "_id": "605462ea8bb6cc5d95154dc2",
                "devicetype": "ios",
                "devicetoken": "d1b98b5974d22267e2435387b58db8b4d10e579ebc86a9c494e28cd25721dd82",
                "deviceid": "210106114883",
                "social_uid": "210106114883",
                "fullname": "oneplus",
                "email": "abcmaple@gmail.com",
                "user_id": "210319020802",
                "profile_pic": "",
                "mobile": "",
                "dob": "",
                "password": "",
                "account_status": "Active",
                "cd": "2021-03-19 02:08:02",
                "gender": "",
                "username": "oneplusab74",
                "country": "",
                "flag": ""
        }
    ]
    }*/


    @SerializedName("_id")
    @Expose
    public String _id = "";

    @SerializedName("devicetype")
    @Expose
    public String devicetype = "";

    @SerializedName("devicetoken")
    @Expose
    public String devicetoken = "";

    @SerializedName("deviceid")
    @Expose
    public String deviceid = "";

    @SerializedName("social_uid")
    @Expose
    public String social_uid = "";

    @SerializedName("fullname")
    @Expose
    public String fullname = "";

    @SerializedName("email")
    @Expose
    public String email = "";

    @SerializedName("user_id")
    @Expose
    public String user_id = "";

    @SerializedName("profile_pic")
    @Expose
    public String profile_pic = "";

    @SerializedName("mobile")
    @Expose
    public String mobile = "";

    @SerializedName("dob")
    @Expose
    public String dob = "";

    @SerializedName("password")
    @Expose
    public String password = "";

    @SerializedName("account_status")
    @Expose
    public String account_status = "";

    @SerializedName("cd")
    @Expose
    public String cd = "";

    @SerializedName("gender")
    @Expose
    public String gender = "";

    @SerializedName("username")
    @Expose
    public String username = "";

    @SerializedName("country")
    @Expose
    public String country = "";

    @SerializedName("flag")
    @Expose
    public String flag = "";


    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getDevicetype() {
        return devicetype;
    }

    public void setDevicetype(String devicetype) {
        this.devicetype = devicetype;
    }

    public String getDevicetoken() {
        return devicetoken;
    }

    public void setDevicetoken(String devicetoken) {
        this.devicetoken = devicetoken;
    }

    public String getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid;
    }

    public String getSocial_uid() {
        return social_uid;
    }

    public void setSocial_uid(String social_uid) {
        this.social_uid = social_uid;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getProfile_pic() {
        return profile_pic;
    }

    public void setProfile_pic(String profile_pic) {
        this.profile_pic = profile_pic;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccount_status() {
        return account_status;
    }

    public void setAccount_status(String account_status) {
        this.account_status = account_status;
    }

    public String getCd() {
        return cd;
    }

    public void setCd(String cd) {
        this.cd = cd;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }





}
