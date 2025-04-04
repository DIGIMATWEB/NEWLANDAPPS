package com.NewLandApps.NewlandApps.ui.home.interactor;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.NewLandApps.NewlandApps.retrofit.GeneralConstantsV2;
import com.NewLandApps.NewlandApps.retrofit.RetrofitClientV3;
import com.NewLandApps.NewlandApps.retrofit.RetrofitValidationsV2;
import com.NewLandApps.NewlandApps.retrofit.serviceDigimat;
import com.NewLandApps.NewlandApps.ui.home.model.User;
import com.NewLandApps.NewlandApps.ui.home.model.requestUsers;
import com.NewLandApps.NewlandApps.ui.home.model.responseUsers;
import com.NewLandApps.NewlandApps.ui.home.presenter.presenterHomeFragment;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class interactorHomeFragmentImpl implements interactorHomeFragment{
    private presenterHomeFragment presenter;
    private Context context;
    private Retrofit retrofitClient;
    private serviceDigimat service;
    public interactorHomeFragmentImpl(presenterHomeFragment presenter, Context context) {
        this.presenter=presenter;
        this.context=context;
        retrofitClient = RetrofitClientV3.getRetrofitInstancev3();
        service = retrofitClient.create(serviceDigimat.class);
    }

    @Override
    public void getUsers() {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String email = preferences.getString(GeneralConstantsV2.EMAIL_PREFERENCES, null);
        requestUsers request=new requestUsers(email);
        Call<responseUsers> call=service.getUsers(request);
        call.enqueue(new Callback<responseUsers>() {
            @Override
            public void onResponse(Call<responseUsers> call, Response<responseUsers> response) {
               validationCodegetUsers(response,context);
            }

            @Override
            public void onFailure(Call<responseUsers> call, Throwable t) {
              //  presenter.hideDialog();
            }
        });
    }

    private void validationCodegetUsers(Response<responseUsers> response, Context context) {
        if (response != null) {
            if (RetrofitValidationsV2.checkSuccessCode(response.code())) {
                getUsersData(response,context);
            }else {
                // Toast.makeText(context, "" + RetrofitValidationsV2.getErrorByStatus(response.code(), context), Toast.LENGTH_SHORT).show();
               // presenter.hideDialog();
            }
        }
    }

    private void getUsersData(Response<responseUsers> response, Context context) {
         responseUsers myresponse=response.body();
                Gson gson=new GsonBuilder().setPrettyPrinting().create();
                String json=gson.toJson(myresponse);
                Log.e("loginresponsev3Data",""+json);
                        if(myresponse!=null) {
                            String code = myresponse.getResponseCode();
                            String message = myresponse.getStatus();
                            List<User> usuarios =  myresponse.getUsers();
                            if (code != null) {
                                if (code.equals("105")) {

                                   if(usuarios!=null) {
                                       presenter.setUsers(usuarios);
                                   }
                                }
                            }else{
                                //presenter.hideDialog();
                            }
                        }else{
                           // presenter.hideDialog();
                        }
    }
}
