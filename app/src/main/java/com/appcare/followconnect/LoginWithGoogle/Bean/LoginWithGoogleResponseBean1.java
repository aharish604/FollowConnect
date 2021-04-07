package com.appcare.followconnect.LoginWithGoogle.Bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class LoginWithGoogleResponseBean1 {

    /* {
        "status": true,
            "message": "Login successfully.",
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


    @SerializedName("message")
    @Expose
    private String message="";
    @SerializedName("status")
    @Expose
    private boolean status;
    @SerializedName("data")
    @Expose
    private ArrayList<LoginWithGoogleBeanResponse> data;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }



    public ArrayList<LoginWithGoogleBeanResponse> getData() {
        return data;
    }

    public void setData(ArrayList<LoginWithGoogleBeanResponse> data) {
        this.data = data;
    }










}
