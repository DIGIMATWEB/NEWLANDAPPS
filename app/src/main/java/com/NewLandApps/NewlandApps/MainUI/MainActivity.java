package com.NewLandApps.NewlandApps.MainUI;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.NewLandApps.NewlandApps.Dialogs.view.SecureCode;
import com.NewLandApps.NewlandApps.Login.view.LoginContainer;
import com.NewLandApps.NewlandApps.R;
import com.NewLandApps.NewlandApps.retrofit.GeneralConstantsV2;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.fragment.app.FragmentManager;
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
    private GoogleSignInClient mSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mSignInClient = GoogleSignIn.getClient(this, gso);
        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        binding.closeSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                revoke();
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


        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {//TODO este es el colapsing floating button
            if (destination.getId() == R.id.nav_profile || destination.getId() == R.id.nav_home|| destination.getId() == R.id.nav_calendario||destination.getId()==R.id.nav_gallery) {
                binding.appBarMain.fab.setVisibility(View.GONE); // Oculta el botón
                if(destination.getId() ==R.id.nav_home){
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    if (fragmentManager.getBackStackEntryCount() > 0) {
                        fragmentManager.popBackStack(); // Remove the top fragment
                    }
                }
            } else {
                binding.appBarMain.fab.setVisibility(View.VISIBLE); // Muestra el botón en otros casos
            }
        });

        // Acción para el FloatingActionButton
        binding.appBarMain.fab.setOnClickListener(view ->
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
        );

        Menu menu = navigationView.getMenu();
        MenuItem OficinaItem = menu.findItem(R.id.nav_home);
        MenuItem VacacionesItem = menu.findItem(R.id.nav_vacaciones);
        MenuItem CalendarioItem = menu.findItem(R.id.nav_calendario);
        MenuItem CumpleañosItem = menu.findItem(R.id.nav_gallery);
        MenuItem NotificacionesItem = menu.findItem(R.id.nav_slideshow);
        MenuItem ProfileItem = menu.findItem(R.id.nav_profile);

        //R.id.nav_profile,R.id.nav_vacaciones,R.id.nav_calendario,
        //                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow

        SharedPreferences preferences = getApplicationContext().getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String verifiationCode = preferences.getString(GeneralConstantsV2.VERIFICATIONCODE, null);
        String userRole= preferences.getString(GeneralConstantsV2.ROLE_USER, null);
//        if(verifiationCode==null) {
//            codigoVerificacion();
//        }
        if(userRole==null||userRole.equals("")){
            OficinaItem.setVisible(false);
            VacacionesItem.setVisible(false);
            CalendarioItem.setVisible(false);
            CumpleañosItem.setVisible(false);
            NotificacionesItem.setVisible(false);
            navController.navigate(R.id.nav_profile);
        }


        setUpBar();
       //
    }
    private void revoke() {
        SharedPreferences preferences = getApplicationContext().getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.clear();
        editor.apply();
        mSignInClient.revokeAccess().addOnCompleteListener(this, task -> {
            if (task.isSuccessful()) {
                clearLocalData();
                Toast.makeText(getApplicationContext(), "Logout", Toast.LENGTH_SHORT).show();
                navigateToLoginScreen();
            } else {
                Toast.makeText(getApplicationContext(), "Failed to revoke access", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void navigateToLoginScreen() {
        Intent intent = new Intent(this, LoginContainer.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
    private void clearLocalData() {
        SharedPreferences preferences = getApplicationContext().getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear(); // Clears all saved data
        editor.apply();
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
    public void onBackPressed() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStack(); // Remove the top fragment
        } else {
            super.onBackPressed(); // Default behavior
        }
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

    public void updateRole() {
        SharedPreferences preferences = getApplicationContext().getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String userRole = preferences.getString(GeneralConstantsV2.ROLE_USER, null);

        Menu menu = binding.navView.getMenu();
        MenuItem OficinaItem = menu.findItem(R.id.nav_home);
        MenuItem VacacionesItem = menu.findItem(R.id.nav_vacaciones);
        MenuItem CalendarioItem = menu.findItem(R.id.nav_calendario);
        MenuItem CumpleañosItem = menu.findItem(R.id.nav_gallery);
        MenuItem NotificacionesItem = menu.findItem(R.id.nav_slideshow);
        MenuItem ProfileItem = menu.findItem(R.id.nav_profile);

        boolean isUserRoleValid = userRole != null && !userRole.isEmpty();

        OficinaItem.setVisible(isUserRoleValid);
        VacacionesItem.setVisible(isUserRoleValid);
        CalendarioItem.setVisible(isUserRoleValid);
        CumpleañosItem.setVisible(isUserRoleValid);
        NotificacionesItem.setVisible(isUserRoleValid);
        if(userRole==null||userRole.equals("")||userRole.equals("0")){
            OficinaItem.setVisible(false);
            VacacionesItem.setVisible(false);
            CalendarioItem.setVisible(false);
            CumpleañosItem.setVisible(false);
            NotificacionesItem.setVisible(false);
            ProfileItem.setVisible(true);
        }else if(userRole.equals("1")){//TODO Administrador
            OficinaItem.setVisible(true);
            VacacionesItem.setVisible(true);
            CalendarioItem.setVisible(true);
            CumpleañosItem.setVisible(true);
            NotificacionesItem.setVisible(true);
            ProfileItem.setVisible(true);
        }else if(userRole.equals("2")){//TODO usuario
            OficinaItem.setVisible(false);
            VacacionesItem.setVisible(true);
            CalendarioItem.setVisible(true);
            CumpleañosItem.setVisible(true);
            NotificacionesItem.setVisible(true);
            ProfileItem.setVisible(true);
        }else if(userRole.equals("3")){ //TODO RH
            OficinaItem.setVisible(true);
            VacacionesItem.setVisible(true);
            CalendarioItem.setVisible(true);
            CumpleañosItem.setVisible(true);
            NotificacionesItem.setVisible(true);
            ProfileItem.setVisible(true);
        }else if(userRole.equals("4")){ //TODO developer
            OficinaItem.setVisible(true);
            VacacionesItem.setVisible(true);
            CalendarioItem.setVisible(true);
            CumpleañosItem.setVisible(true);
            NotificacionesItem.setVisible(true);
            ProfileItem.setVisible(true);
        }
        //String role="";
        //        if(seleccion.equals("Administracion")){
        //            role="1";
        //        }else if(seleccion.equals( "Usuario")) {
        //            role="2";
        //        }else if(seleccion.equals("RH")){
        //            role="3";
        //        }else if(seleccion.equals("Developer")){
        //            role="4";
        //        }
    }
}