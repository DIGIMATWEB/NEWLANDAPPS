package com.NewLandApps.NewlandApps.ui.home.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class responseUsers {
    @SerializedName("status")
    
    private String status;
    @SerializedName("responseCode")
    
    private String responseCode;
    @SerializedName("users")
    
    private List<User> users;
    public responseUsers(String status, String responseCode, List<User> users) {
        super();
        this.status = status;
        this.responseCode = responseCode;
        this.users = users;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

}
