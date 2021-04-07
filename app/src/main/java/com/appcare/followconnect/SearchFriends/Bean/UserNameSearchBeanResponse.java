package com.appcare.followconnect.SearchFriends.Bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class UserNameSearchBeanResponse {

   /* { "status":true,
            "message":"All users list",
            "data":[

    {
        "_id":"602367128bb6cc3d0e16dc63",
            "devicetype":"ios",
            "devicetoken":"106497bbd98da5860554583515b9e61ef2dc62c2e2e647f04c6be2d6d47802dc",
            "deviceid":"",
            "social_uid":"114906926799816747377",
            "fullname":"Teja Rangaraju",
            "email":"teja@appcare.co.in",
            "user_id":"210210102442",
            "profile_pic":
        "http://13.126.39.225/socialmedia/uploads/images/profile_pics/1616483784.784968.jpeg",
                "mobile":"",
            "dob":"",
            "password":"",
            "account_status":"Active",
            "cd":"2021-02-10 10:24:42",
            "gender":"",
            "username":"teja rangarajute82",
            "country":"",
            "flag":"",
            "ip":"1",
            "ltd":"1",
            "lng":"1",
            "address":"1",
            "friend_status":"noconnection"
    }
    ]
}*/


    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private ArrayList<UserNameSearchResponseBean1> data = null;

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

    public ArrayList<UserNameSearchResponseBean1> getData() {
        return data;
    }

    public void setData(ArrayList<UserNameSearchResponseBean1> data) {
        this.data = data;
    }
}
