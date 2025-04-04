package com.NewLandApps.NewlandApps.ui.home.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class User implements Serializable {
    @SerializedName("nameUser")
    
    private String nameUser;
    @SerializedName("photoUser")
    
    private String photoUser;
    @SerializedName("userRole")
    
    private String userRole;
    @SerializedName("cargoUsuario")
    
    private String cargoUsuario;
    @SerializedName("areaUsuario")
    
    private String areaUsuario;
    @SerializedName("correoUsuario")
    
    private String correoUsuario;
    @SerializedName("correoEmpresarial")
    
    private String correoEmpresarial;
    @SerializedName("NSS")
    
    private String nss;
    @SerializedName("incorporacion")
    
    private String incorporacion;
    @SerializedName("jefeInmediato")
    
    private String jefeInmediato;
    @SerializedName("Cumplea\u00f1os")
    
    private String cumpleaOs;
    @SerializedName("telefono")
    
    private String telefono;
    @SerializedName("status")
    
    private Integer status;
    @SerializedName("vacations")
    
    private String vacations;
    public User(String nameUser, String photoUser, String userRole, String cargoUsuario, String areaUsuario, String correoUsuario, String correoEmpresarial, String nss, String incorporacion, String jefeInmediato, String cumpleaOs, String telefono, Integer status, String vacations) {
        super();
        this.nameUser = nameUser;
        this.photoUser = photoUser;
        this.userRole = userRole;
        this.cargoUsuario = cargoUsuario;
        this.areaUsuario = areaUsuario;
        this.correoUsuario = correoUsuario;
        this.correoEmpresarial = correoEmpresarial;
        this.nss = nss;
        this.incorporacion = incorporacion;
        this.jefeInmediato = jefeInmediato;
        this.cumpleaOs = cumpleaOs;
        this.telefono = telefono;
        this.status = status;
        this.vacations = vacations;
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

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getCargoUsuario() {
        return cargoUsuario;
    }

    public void setCargoUsuario(String cargoUsuario) {
        this.cargoUsuario = cargoUsuario;
    }

    public String getAreaUsuario() {
        return areaUsuario;
    }

    public void setAreaUsuario(String areaUsuario) {
        this.areaUsuario = areaUsuario;
    }

    public String getCorreoUsuario() {
        return correoUsuario;
    }

    public void setCorreoUsuario(String correoUsuario) {
        this.correoUsuario = correoUsuario;
    }

    public String getCorreoEmpresarial() {
        return correoEmpresarial;
    }

    public void setCorreoEmpresarial(String correoEmpresarial) {
        this.correoEmpresarial = correoEmpresarial;
    }

    public String getNss() {
        return nss;
    }

    public void setNss(String nss) {
        this.nss = nss;
    }

    public String getIncorporacion() {
        return incorporacion;
    }

    public void setIncorporacion(String incorporacion) {
        this.incorporacion = incorporacion;
    }

    public String getJefeInmediato() {
        return jefeInmediato;
    }

    public void setJefeInmediato(String jefeInmediato) {
        this.jefeInmediato = jefeInmediato;
    }

    public String getCumpleaOs() {
        return cumpleaOs;
    }

    public void setCumpleaOs(String cumpleaOs) {
        this.cumpleaOs = cumpleaOs;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getVacations() {
        return vacations;
    }

    public void setVacations(String vacations) {
        this.vacations = vacations;
    }

}
