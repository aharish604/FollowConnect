package com.appcare.followconnect.LoginWithGoogle.Bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginWithGoogleBeanResponse {

  /*  public class Datum{

    }*/

   /* public class Root{
        public boolean status;
        public String message;
        public List<Datum> data;
    }
*/


    @SerializedName("_id")
    @Expose
    private String _id="";

    @SerializedName("devicetype")
    @Expose
    private String devicetype="";

    @SerializedName("devicetoken")
    @Expose
    private String devicetoken="";

    @SerializedName("deviceid")
    @Expose
    private String deviceid="";

    @SerializedName("social_uid")
    @Expose
    private String social_uid="";

    @SerializedName("fullname")
    @Expose
    private String fullname="";

    @SerializedName("email")
    @Expose
    private String email="";

    @SerializedName("user_id")
    @Expose
    private String user_id="";

    @SerializedName("profile_pic")
    @Expose
    private String profile_pic="";


    @SerializedName("mobile")
    @Expose
    private String mobile="";

    @SerializedName("dob")
    @Expose
    private String dob="";

    @SerializedName("password")
    @Expose
    private String password="";


    @SerializedName("account_status")
    @Expose
    private String account_status="";
    @SerializedName("cd")
    @Expose
    private String cd="";


    @SerializedName("gender")
    @Expose
    private String gender="";

    @SerializedName("username")
    @Expose
    private String username="";

    @SerializedName("country")
    @Expose
    private String country="";


    @SerializedName("flag")
    @Expose
    private String flag="";


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
