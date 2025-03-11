package com.NewLandApps.NewlandApps.Login.view;

public interface LoginView {
    void showLoader();

    void hideLoader();

    void showMessage(String message);

    void succesLogin();
}
