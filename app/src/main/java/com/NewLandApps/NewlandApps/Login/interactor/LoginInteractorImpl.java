package com.NewLandApps.NewlandApps.Login.interactor;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.NewLandApps.NewlandApps.Login.model.LoginRequestV2;
import com.NewLandApps.NewlandApps.Login.model.LoginResponseV2;
import com.NewLandApps.NewlandApps.Login.model.SetUpUser.requestSetUpUser;
import com.NewLandApps.NewlandApps.Login.model.SetUpUser.responseSetupUser;
import com.NewLandApps.NewlandApps.Login.model.UserDataV2;
import com.NewLandApps.NewlandApps.Login.presenter.LoginPresenter;
import com.NewLandApps.NewlandApps.retrofit.serviceDigimat;
import com.NewLandApps.NewlandApps.retrofit.GeneralConstantsV2;
import com.NewLandApps.NewlandApps.retrofit.RetrofitClientV3;
import com.NewLandApps.NewlandApps.retrofit.RetrofitValidationsV2;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginInteractorImpl implements LoginInteractor {
    private Context context;
    private LoginPresenter presenter;
    private Retrofit retrofitClient;
    private serviceDigimat service;
    public LoginInteractorImpl(LoginPresenter presenter,Context context)
    {
        this.presenter=presenter;
        this.context=context;
        retrofitClient = RetrofitClientV3.getRetrofitInstancev3();
        service = retrofitClient.create(serviceDigimat.class);
    }


    @Override
    public void requestLogin(String telephone,String pass   ) {

        if(!telephone.equals("")|!pass.equals(""))
        {
            requestokLogin(telephone,pass);
        }
        else{
            Toast.makeText(context, "Informacion incorrecta", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void setUser() {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String name = preferences.getString(GeneralConstantsV2.USER_PREFERENCES, null);
        String urlLogo = preferences.getString(GeneralConstantsV2.URL_USER_IMAGE_PREFERENCES, null);
        String email = preferences.getString(GeneralConstantsV2.EMAIL_PREFERENCES, null);
        seUpUser(name,urlLogo,email);
    }

    private void seUpUser(String name, String urlLogo, String email) {
        requestSetUpUser request=new requestSetUpUser(name,urlLogo,email);
        presenter.showDialog();
        Call<responseSetupUser> call=service.loginv3(request);
        Gson gson=new GsonBuilder().setPrettyPrinting().create();
        String json=gson.toJson(request);
        Log.e("requestLogin",""+json);
        call.enqueue(new Callback<responseSetupUser>() {
            @Override
            public void onResponse(Call<responseSetupUser> call, Response<responseSetupUser> response) {
                validationCodeLoginv3(response,context);
            }

            @Override
            public void onFailure(Call<responseSetupUser> call, Throwable t) {
                presenter.hideDialog();
            }
        });

    }

    private void validationCodeLoginv3(Response<responseSetupUser> response, Context context) {
        if (response != null) {
            if (RetrofitValidationsV2.checkSuccessCode(response.code())) {
                loginresponsev3Data(response,context);
            }else {
                // Toast.makeText(context, "" + RetrofitValidationsV2.getErrorByStatus(response.code(), context), Toast.LENGTH_SHORT).show();
                presenter.hideDialog();
            }
        }
    }

    private void loginresponsev3Data(Response<responseSetupUser> response, Context context) {
        responseSetupUser myresponse=response.body();
        Gson gson=new GsonBuilder().setPrettyPrinting().create();
        String json=gson.toJson(myresponse);
        Log.e("loginresponsev3Data",""+json);
                if(myresponse!=null) {
                    String code = myresponse.getResponseCode();
                    String message = myresponse.getMessage();

                    if (code != null) {
                        if (code.equals("105")) {
                            SharedPreferences preferencias=context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES,Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor=preferencias.edit();
                            editor.putString(GeneralConstantsV2.ROLE_USER, myresponse.getUserRole() );
                            editor.commit();
                            presenter.hideDialog();
                            presenter.succes();
                        }
                    }else{
                        presenter.hideDialog();
                    }
                }else{
                    presenter.hideDialog();
                }
    }

    private void requestokLogin(String user, String pass) {
        LoginRequestV2 request= new LoginRequestV2(user,pass);
        presenter.showDialog();
        Call<LoginResponseV2> call=service.login(request);
        call.enqueue(new Callback<LoginResponseV2>() {
            @Override
            public void onResponse(Call<LoginResponseV2> call, Response<LoginResponseV2> response) {
             //   Toast.makeText(context, "response: "+response.body().getResponseCode(), Toast.LENGTH_SHORT).show();
                validationCodeLogin(response,context,user);

            }

            @Override
            public void onFailure(Call<LoginResponseV2> call, Throwable t) {
                Toast.makeText(context, "response: "+t.getMessage(), Toast.LENGTH_LONG).show();
                presenter.hideDialog();
            }
        });

    }

    private void validationCodeLogin(Response<LoginResponseV2> response, Context context, String email) {
        if (response != null) {
            if (RetrofitValidationsV2.checkSuccessCode(response.code())) {
                loginresponseData(response,context,email);
            }else {
               // Toast.makeText(context, "" + RetrofitValidationsV2.getErrorByStatus(response.code(), context), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void loginresponseData(Response<LoginResponseV2> response, Context context, String email) {
        LoginResponseV2 myresponse=response.body();
        if(myresponse!=null)
        {
            String code=myresponse.getResponseCode();
            String message=myresponse.getMessage();
            if(code!=null) {
                if (code.equals("S001")) {
                    UserDataV2[] data = myresponse.getData();
                    //Toast.makeText(context, "nomnbre "+data.getEmployeeName()+" telofono "+data.getTelefono()+"  token "+data.getToken(), Toast.LENGTH_SHORT).show();
                    //Log.e("login",""+"nomnbre "+data.getEmployeeName()+" telofono "+data.getTelefono()+"  token "+data.getToken());

                    String nombre = data[0].getEmployeeName();
                    String telefono = data[0].getTelefono();
                    String token = data[0].getToken();
                    int permisionID = data[0].getPermissionsId();
                    Log.e("credenciales", "value   " + permisionID);
                    SharedPreferences preferencias = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferencias.edit();

                    editor.putString(GeneralConstantsV2.USER_PREFERENCES, nombre);
                    editor.putString(GeneralConstantsV2.TELEPHONE_PREFERENCE, telefono);
                    editor.putString(GeneralConstantsV2.EMAIL_PREFERENCES, email);
                    editor.putString(GeneralConstantsV2.TOKEN_PREFERENCES, token);
                    editor.putString(GeneralConstantsV2.LEVEL_PERMISIONS, String.valueOf(permisionID));

                    editor.commit();
                    presenter.succes();
                    presenter.hideDialog();
                    //Log.e("login",""+preferencias.getString(GeneralConstantsV2.USER_PREFERENCES, null));

                } else {
                    Toast.makeText(context, "response : " + code + " " + message, Toast.LENGTH_SHORT).show();
                    presenter.hideDialog();
                }
            }else{
                Toast.makeText(context, "Usuario no registrado", Toast.LENGTH_SHORT).show();
                presenter.hideDialog();
            }
        }
    }
}
