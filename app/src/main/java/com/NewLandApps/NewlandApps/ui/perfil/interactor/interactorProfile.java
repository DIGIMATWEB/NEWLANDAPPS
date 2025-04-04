package com.NewLandApps.NewlandApps.ui.perfil.interactor;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.NewLandApps.NewlandApps.retrofit.GeneralConstantsV2;
import com.NewLandApps.NewlandApps.retrofit.serviceDigimat;
import com.NewLandApps.NewlandApps.retrofit.RetrofitClientV3;
import com.NewLandApps.NewlandApps.retrofit.RetrofitValidationsV2;
import com.NewLandApps.NewlandApps.ui.perfil.model.requestGetRole;
import com.NewLandApps.NewlandApps.ui.perfil.model.responseGetRole;
import com.NewLandApps.NewlandApps.ui.perfil.presenter.presenterProfileInterface;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class interactorProfile implements interactorProfileInterface{
    private presenterProfileInterface presenter;
    private Context context;
      private Retrofit retrofitClient;
        private serviceDigimat service;
    public interactorProfile(presenterProfileInterface presenter, Context context) {
        this.context=context;
        this.presenter=presenter;
        retrofitClient = RetrofitClientV3.getRetrofitInstancev3();
        service = retrofitClient.create(serviceDigimat.class);
    }

    @Override
    public void updateRole(String email) {
        requestGetRole request=new requestGetRole(email);
        Call<responseGetRole> call=service.getRole(request);
        call.enqueue(new Callback<responseGetRole>() {
            @Override
            public void onResponse(Call<responseGetRole> call, Response<responseGetRole> response) {
                validateGetRole(response,context);
            }

            @Override
            public void onFailure(Call<responseGetRole> call, Throwable t) {
                presenter.hideDialog();
            }
        });

    }

    private void validateGetRole(Response<responseGetRole> response, Context context) {
        if (response != null) {
            if (RetrofitValidationsV2.checkSuccessCode(response.code())) {
                validateGetRoleData(response,context);
            }else {
                // Toast.makeText(context, "" + RetrofitValidationsV2.getErrorByStatus(response.code(), context), Toast.LENGTH_SHORT).show();
                presenter.hideDialog();
            }
        }
    }

    private void validateGetRoleData(Response<responseGetRole> response, Context context) {
                responseGetRole myresponse=response.body();
                Gson gson=new GsonBuilder().setPrettyPrinting().create();
               String json=gson.toJson(myresponse);
                Log.e("loginresponsev3Data",""+json);
                        if(myresponse!=null) {
                            String code = myresponse.getResponseCode();
                            String message = myresponse.getStatus();


                            if (code != null) {
                                if (code.equals("105")) {
                                    SharedPreferences preferencias=context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES,Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor=preferencias.edit();
                                    editor.putString(GeneralConstantsV2.ROLE_USER, myresponse.getUserRole() );
                                    editor.commit();
                                    presenter.hideDialog();
                                    presenter.succesGetRole();
                                }
                            }else{
                                presenter.hideDialog();
                            }
                        }else{
                            presenter.hideDialog();
                        }
    }
}
