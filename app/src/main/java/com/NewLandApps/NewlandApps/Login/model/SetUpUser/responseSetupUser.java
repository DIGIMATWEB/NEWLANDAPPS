package com.NewLandApps.NewlandApps.Login.model.SetUpUser;

import com.google.gson.annotations.SerializedName;

public class responseSetupUser {

    @SerializedName("status")
    
    private String status;
    @SerializedName("responseCode")
    
    private String responseCode;
    @SerializedName("message")
    
    private String message;
    @SerializedName("userRole")
    
    private String userRole;
    public responseSetupUser(String status, String responseCode, String message, String userRole) {
        super();
        this.status = status;
        this.responseCode = responseCode;
        this.message = message;
        this.userRole = userRole;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

}