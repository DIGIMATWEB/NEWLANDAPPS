package com.NewLandApps.NewlandApps.ui.home.interactor;

import android.content.Context;

import com.NewLandApps.NewlandApps.retrofit.LoginServicesV2;
import com.NewLandApps.NewlandApps.ui.home.presenter.presenterHomeFragment;
import com.NewLandApps.NewlandApps.ui.home.presenter.presenterHomeFragmentImpl;

import retrofit2.Retrofit;

public class interactorHomeFragmentImpl implements interactorHomeFragment{
    private presenterHomeFragment presenter;
    private Context context;
    private Retrofit retrofitClient;
    private LoginServicesV2 service;
    public interactorHomeFragmentImpl(presenterHomeFragment presenter, Context context) {
        this.presenter=presenter;
        this.context=context;

    }
}
