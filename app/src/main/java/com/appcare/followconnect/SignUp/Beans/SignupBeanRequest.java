package com.appcare.followconnect.SignUp.Beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SignupBeanRequest {

    /*["fullname":fullname,
            "username":username,
            "email":email,
            "mobile":mobile,
            "gender":gender,
            "dob":dob,
            "password":password,
            "country":country,
            "deviceid":deviceid,
            "devicetype":devicetype,
            "devicetoken":devicetoken,
            "CountryCode":countryCodelbl.text ?? "",
            "ip":ip,
            "lat":lat,
            "lng":lng,
            "address":Locaddress]*/


   /* {"fullname":"fullname",
            "username":"username",
            "email":"aharish604@gmail.com",
            "mobile":"mobile",
            "gender":"gender",

            "dob":"dob",
            "password":"password",
            "country":"country",
            "deviceid":"deviceid",
            "devicetype":"devicetype",

            "devicetoken":"devicetype",
            "CountryCode":"",
            "ip":"ip",
            "lat":"lat",
            "lng":"lng",

            "address":"Locaddress"}*/


    @SerializedName("fullname")
    @Expose
    private String fullname="";

    @SerializedName("username")
    @Expose
    private String username="";

    @SerializedName("email")
    @Expose
    private String email="";

    @SerializedName("mobile")
    @Expose
    private String mobile="";

    @SerializedName("gender")
    @Expose
    private String gender="";

    @SerializedName("dob")
    @Expose
    private String dob="";

    @SerializedName("password")
    @Expose
    private String password="";

    @SerializedName("country")
    @Expose
    private String country="";


    @SerializedName("deviceid")
    @Expose
    private String deviceid="";

    @SerializedName("devicetype")
    @Expose
    private String devicetype="";

    @SerializedName("devicetoken")
    @Expose
    private String devicetoken="";

    @SerializedName("CountryCode")
    @Expose
    private String CountryCode="";

    @SerializedName("ip")
    @Expose
    private String ip="";

    @SerializedName("lat")
    @Expose
    private String lat="";

    @SerializedName("lng")
    @Expose
    private String lng="";

    @SerializedName("address")
    @Expose
    private String address="";

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

    public String getCountryCode() {
        return CountryCode;
    }

    public void setCountryCode(String countryCode) {
        CountryCode = countryCode;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
