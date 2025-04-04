package com.NewLandApps.NewlandApps.ui.perfil.presenter;

import android.content.Context;

import com.NewLandApps.NewlandApps.ui.perfil.interactor.interactorProfile;
import com.NewLandApps.NewlandApps.ui.perfil.interactor.interactorProfileInterface;
import com.NewLandApps.NewlandApps.ui.perfil.view.profileView;

public class presenterProfile implements  presenterProfileInterface{
    private profileView view;
    private Context context;
    private interactorProfileInterface interactor;
    public presenterProfile(profileView view, Context context) {
        this.view=view;
        this.context=context;
        interactor=new interactorProfile(this,context);
    }
    @Override
    public void updateRole(String email) {
        if(view!=null)
        {
            interactor.updateRole(email);
        }

    }

    @Override
    public void hideDialog() {
        if(view!=null)
        {
            view.hideProgressDialog();
        }
    }

    @Override
    public void showProgresDialog() {
        if(view!=null)
        {
            view.showProgresDialog();
        }
    }

    @Override
    public void succesGetRole() {
        if(view!=null)
        {
            view.succesGetRole();
        }
    }
}
