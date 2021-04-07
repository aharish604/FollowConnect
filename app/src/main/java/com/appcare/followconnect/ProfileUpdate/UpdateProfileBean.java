package com.appcare.followconnect.ProfileUpdate;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateProfileBean {

    /*{"fullname":"Kartheek",
    "username":"kittu04177",
            "email":"naresh@appcare.co.in",
            "mobile":"9012345667",
            "gender":"male",
            "dob":"04/02/1992",
            "password":"12345",
            "country":"india",
            "CountryCode":"91",
            "deviceid":"151513",
            "devicetype":"abc",
            "devicetoken":"abc",
            "API-KEY": "827ccb0eea8a706c4c34a16891f84e7b",
            "user_id":"210107091749"}*/


    @SerializedName("fullname")
    @Expose
    public String fullname = "";

    @SerializedName("username")
    @Expose
    public String username = "";

    @SerializedName("email")
    @Expose
    public String email = "";

    @SerializedName("mobile")
    @Expose
    public String mobile = "";

    @SerializedName("gender")
    @Expose
    public String gender = "";

    @SerializedName("dob")
    @Expose
    public String dob = "";

    @SerializedName("password")
    @Expose
    public String password = "";

    @SerializedName("country")
    @Expose
    public String country = "";


    @SerializedName("CountryCode")
    @Expose
    public String countryCode = "";


    @SerializedName("deviceid")
    @Expose
    public String deviceid = "";


    @SerializedName("devicetype")
    @Expose
    public String devicetype = "";


    @SerializedName("devicetoken")
    @Expose
    public String devicetoken = "";


    @SerializedName("API-KEY")
    @Expose
    public String APIKEY = "";

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        countryCode = countryCode;
    }

    public String getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid;
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

    public String getAPIKEY() {
        return APIKEY;
    }

    public void setAPIKEY(String APIKEY) {
        this.APIKEY = APIKEY;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    @SerializedName("user_id")
    @Expose
    public String user_id = "";


}
