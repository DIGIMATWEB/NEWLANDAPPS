package com.NewLandApps.NewlandApps.ui.Splash.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.NewLandApps.NewlandApps.Login.view.LoginContainer;
import com.NewLandApps.NewlandApps.MainUI.MainActivity;
import com.NewLandApps.NewlandApps.R;
import com.NewLandApps.NewlandApps.retrofit.GeneralConstantsV2;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class Splash extends AppCompatActivity {
    private ImageView logotipo;
    private String urlLogo;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashcreen);
        initView();
    }

    private void initView() {
        logotipo=findViewById(R.id.logotipo);
        urlLogo= "https://newlandapps.com/img/Logo_Newland_aprobado-01.png";
        Glide.with(this)
                .load(R.drawable.logo)
                .apply(new RequestOptions()
                        .placeholder(R.drawable.ic_launcher_foreground)  // Image while loading
                        .error(R.drawable.logo)       // Image if load fails
                        .centerCrop())                      // Scale type
                .into(logotipo);
        goMain();

    }

    private void goMain() {
        new Handler().postDelayed(() -> {

            SharedPreferences preferences = getApplicationContext().getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
            String token = preferences.getString(GeneralConstantsV2.TOKEN_PREFERENCES, null);
            if(token!=null){
                Intent intent2 = new Intent(this, MainActivity.class);
                intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_CLEAR_TOP);//
                intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent2);
            }else {

                Intent intent = new Intent(this, LoginContainer.class);
                startActivity(intent);
            }



            finish(); // Prevents user from going back to SplashActivity
        }, 2000);
    }
}
