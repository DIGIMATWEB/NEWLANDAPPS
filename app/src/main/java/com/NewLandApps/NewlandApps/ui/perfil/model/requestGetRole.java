package com.NewLandApps.NewlandApps.ui.perfil.model;

import com.google.gson.annotations.SerializedName;

public class requestGetRole {
    @SerializedName("correoUsuario")
    
    private String correoUsuario;



    public requestGetRole(String correoUsuario) {
        super();
        this.correoUsuario = correoUsuario;
    }

    public String getCorreoUsuario() {
        return correoUsuario;
    }

    public void setCorreoUsuario(String correoUsuario) {
        this.correoUsuario = correoUsuario;
    }

}