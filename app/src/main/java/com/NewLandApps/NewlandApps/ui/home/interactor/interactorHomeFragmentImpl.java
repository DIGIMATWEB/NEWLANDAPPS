package com.NewLandApps.NewlandApps.ui.home.interactor;

import android.content.Context;

import com.NewLandApps.NewlandApps.ui.home.presenter.presenterHomeFragment;
import com.NewLandApps.NewlandApps.ui.home.presenter.presenterHomeFragmentImpl;

public class interactorHomeFragmentImpl implements interactorHomeFragment{
    private presenterHomeFragment presenter;
    private Context context;
    public interactorHomeFragmentImpl(presenterHomeFragment presenter, Context context) {
        this.presenter=presenter;
        this.context=context;
        
    }
}
