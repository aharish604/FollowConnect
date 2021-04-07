package com.appcare.followconnect.Login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginBean<KEY> {



   /* {"username":"aharish3806@gmail.com","password":"password",
            "deviceid":"deviceid",
            "devicetype":"devicetype",
            "devicetoken":"devicetoken",
            "API-KEY":"APPURLS.apiKey"}*/

    @SerializedName("username")
    @Expose
    private String username="";

    @SerializedName("password")
    @Expose
    private String password="";

    @SerializedName("devicetype")
    @Expose
    private String devicetype="";

    @SerializedName("devicetoken")
    @Expose
    private String devicetoken="";

    public String getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid;
    }

    @SerializedName("API-KEY")
    @Expose
    private String APIKEY="";
    @SerializedName("deviceid")
    @Expose
    private String deviceid="";

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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



}
