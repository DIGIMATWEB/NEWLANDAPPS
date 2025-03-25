package com.NewLandApps.NewlandApps.Login.presenter;

import android.content.Context;

import com.NewLandApps.NewlandApps.Login.interactor.LoginInteractor;
import com.NewLandApps.NewlandApps.Login.interactor.LoginInteractorImpl;
import com.NewLandApps.NewlandApps.Login.view.LoginView;
import com.NewLandApps.NewlandApps.Login.view.LoginViewImpl;

public class LoginPresenterImpl implements  LoginPresenter{
    private LoginView view;
    private Context context;
    private LoginInteractor interactor;
    public LoginPresenterImpl(LoginView view,Context context)
    {
        this.view=view;
        this.context=context;
        this. interactor=new LoginInteractorImpl(this,context);
    }

    @Override
    public void setView(LoginViewImpl view) {
        this.view=view;
    }

    @Override
    public void loginRequest(String telephone,String pass ) {
        interactor.requestLogin(telephone ,pass );
    }

    @Override
    public void succes() {
        if(view!=null)
        {
            view.succesLogin();
        }
    }

    @Override
    public void hideDialog() {
        if(view!=null)
        {
            view.hideLoader();
        }
    }

    @Override
    public void showDialog() {
        if(view!=null)
        {
            view.showLoader();
        }
    }

    @Override
    public void setUser() {
        if(view!=null){
            interactor.setUser();
        }
    }
}
