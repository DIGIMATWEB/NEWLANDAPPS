package com.NewLandApps.NewlandApps.ui.perfil.model;

import com.google.gson.annotations.SerializedName;

public class responseGetRole {
    @SerializedName("status")
    
    private String status;
    @SerializedName("responseCode")
    
    private String responseCode;
    @SerializedName("userRole")
    
    private String userRole;


    public responseGetRole(String status, String responseCode, String userRole) {
        super();
        this.status = status;
        this.responseCode = responseCode;
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

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
}
