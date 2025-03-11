package com.NewLandApps.NewlandApps;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import com.NewLandApps.NewlandApps.Dialogs.view.SecureCode;
import com.NewLandApps.NewlandApps.Dialogs.view.progressDialog;
import com.NewLandApps.NewlandApps.retrofit.GeneralConstantsV2;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.NewLandApps.NewlandApps.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder( R.id.nav_profile,R.id.nav_vacaciones,R.id.nav_calendario,
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        Menu menu = navigationView.getMenu();
        MenuItem galleryItem = menu.findItem(R.id.nav_gallery);
        //galleryItem.setVisible(false);
        SharedPreferences preferences = getApplicationContext().getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String verifiationCode = preferences.getString(GeneralConstantsV2.VERIFICATIONCODE, null);
        if(verifiationCode==null) {
            codigoVerificacion();
        }
        setUpBar();
    }

    private void codigoVerificacion() {
        SecureCode dialog = new SecureCode();
        dialog.show(getSupportFragmentManager(), "SecureCode");
    }

    private void setUpBar() {
        SharedPreferences preferences = getApplicationContext().getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String urlLogo = preferences.getString(GeneralConstantsV2.URL_USER_IMAGE_PREFERENCES, null);
        String email = preferences.getString(GeneralConstantsV2.EMAIL_PREFERENCES,null);
        String name = preferences.getString(GeneralConstantsV2.USER_PREFERENCES,null);
        // Get the header view from NavigationView
        View headerView = binding.navView.getHeaderView(0);
        ImageView imageView = headerView.findViewById(R.id.imageView); // Get ImageView from header

        // Load image using Glide
        Glide.with(this)
                .load(urlLogo)
                .apply(new RequestOptions()
                        .placeholder(R.drawable.ic_launcher_foreground)  // Placeholder while loading
                        .error(R.drawable.ic_launcher_foreground)       // Fallback if load fails
                        .centerCrop())                                  // Crop image to fill
                .into(imageView);  // Set image into ImageView
        TextView emailText=headerView.findViewById(R.id.textView);
        TextView nameText=headerView.findViewById(R.id.nameUser);
        nameText.setText(name);
        emailText.setText(email);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}