package com.appcare.followconnect.LoginWithGoogle.Bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginWithGoogleBeanRequest {

   /* {
        "social_uid": "210106114883",
            "fullname": "oneplus",
            "email": "abcmaple@gmail.com",
            "deviceid": "210106114883",
            "devicetoken": "d1b98b5974d22267e2435387b58db8b4d10e579ebc86a9c494e28cd25721dd82",
            "devicetype": "ios"
    }
*/

    @SerializedName("social_uid")
    @Expose
    private String social_uid="";
    @SerializedName("fullname")
    @Expose
    private String fullname="";
    @SerializedName("email")
    @Expose
    private String email="";
    @SerializedName("deviceid")
    @Expose
    private String deviceid="";
    @SerializedName("devicetoken")
    @Expose
    private String devicetoken="";
    @SerializedName("devicetype")
    @Expose
    private String devicetype="";

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

    public String getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid;
    }

    public String getDevicetoken() {
        return devicetoken;
    }

    public void setDevicetoken(String devicetoken) {
        this.devicetoken = devicetoken;
    }

    public String getDevicetype() {
        return devicetype;
    }

    public void setDevicetype(String devicetype) {
        this.devicetype = devicetype;
    }




}
