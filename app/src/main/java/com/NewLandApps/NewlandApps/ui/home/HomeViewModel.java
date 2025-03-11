package com.NewLandApps.NewlandApps.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
      //  mText.setValue("Sala de juntas");
    }

    public LiveData<String> getText() {
        return mText;
    }
}