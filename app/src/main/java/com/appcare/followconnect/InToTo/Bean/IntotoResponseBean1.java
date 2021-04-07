package com.appcare.followconnect.InToTo.Bean;

import com.appcare.followconnect.MyviewPostdisplay.bean.Id;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class IntotoResponseBean1 {

    @SerializedName("Public")
    @Expose
    private ArrayList<intotopublic> _public = null;
    @SerializedName("Private")
    @Expose
    private ArrayList<intotoprivate> _private = null;
    @SerializedName("Date")
    @Expose
    private String date;

    public ArrayList<intotopublic> get_public() {
        return _public;
    }

    public void set_public(ArrayList<intotopublic> _public) {
        this._public = _public;
    }

    public ArrayList<intotoprivate> get_private() {
        return _private;
    }

    public void set_private(ArrayList<intotoprivate> _private) {
        this._private = _private;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
