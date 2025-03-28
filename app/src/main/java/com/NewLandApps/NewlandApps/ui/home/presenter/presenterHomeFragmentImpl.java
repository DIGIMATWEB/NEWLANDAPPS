package com.NewLandApps.NewlandApps.ui.home.presenter;

import android.content.Context;

import com.NewLandApps.NewlandApps.ui.home.interactor.interactorHomeFragment;
import com.NewLandApps.NewlandApps.ui.home.interactor.interactorHomeFragmentImpl;
import com.NewLandApps.NewlandApps.ui.home.view.HomeFragment;
import com.NewLandApps.NewlandApps.ui.home.view.homeFragmentView;

public class presenterHomeFragmentImpl implements presenterHomeFragment {
    private Context context;
    private homeFragmentView view;
    private interactorHomeFragment interactor;
    public presenterHomeFragmentImpl(homeFragmentView view, Context context){
        this.view=view;
        this.context=context;
        interactor=new interactorHomeFragmentImpl(this,context);

    }
    @Override
    public void getUsers() {
        if(view!=null){

        }
    }
}
