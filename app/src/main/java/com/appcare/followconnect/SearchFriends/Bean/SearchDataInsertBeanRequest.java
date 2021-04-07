package com.appcare.followconnect.SearchFriends.Bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchDataInsertBeanRequest {

    /*{
        "uid":"210305104633",
            "query":"teja@appcare.co.in",
            "fid":"210210102442"
    }
*/

    @SerializedName("uid")
    @Expose
    private String uid;

    @SerializedName("query")
    @Expose
    private String query;

    @SerializedName("fid")
    @Expose
    private String fid;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }




}
