package com.NewLandApps.NewlandApps.Login.model.SetUpUser;

import com.google.gson.annotations.SerializedName;

public class requestSetUpUser {

    @SerializedName("nameUser")
    private String nameUser;
    @SerializedName("photoUser")
    private String photoUser;
    @SerializedName("correoUsuario")
    private String correoUsuario;
    public requestSetUpUser(String nameUser, String photoUser, String correoUsuario) {
        super();
        this.nameUser = nameUser;
        this.photoUser = photoUser;
        this.correoUsuario = correoUsuario;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getPhotoUser() {
        return photoUser;
    }

    public void setPhotoUser(String photoUser) {
        this.photoUser = photoUser;
    }

    public String getCorreoUsuario() {
        return correoUsuario;
    }

    public void setCorreoUsuario(String correoUsuario) {
        this.correoUsuario = correoUsuario;
    }

}
