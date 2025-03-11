package com.NewLandApps.NewlandApps.Login.presenter;

import com.NewLandApps.NewlandApps.Login.view.LoginViewImpl;

public interface LoginPresenter {
    void setView(LoginViewImpl view);
    void loginRequest(String telephone,String pass );

    void succes();

    void hideDialog();

    void showDialog();
}
