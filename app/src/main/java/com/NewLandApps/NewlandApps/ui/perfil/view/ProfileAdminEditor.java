package com.NewLandApps.NewlandApps.ui.perfil.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.NewLandApps.NewlandApps.MainUI.MainActivity;
import com.NewLandApps.NewlandApps.R;
import com.NewLandApps.NewlandApps.retrofit.GeneralConstantsV2;
import com.NewLandApps.NewlandApps.ui.home.model.User;
import com.NewLandApps.NewlandApps.ui.perfil.presenter.presenterProfile;
import com.NewLandApps.NewlandApps.ui.perfil.presenter.presenterProfileInterface;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class ProfileAdminEditor extends Fragment implements profileView{
    public static final String TAG = ProfileAdminEditor.class.getSimpleName();
    private TextView textGallery;
    private Spinner spinner;
    private ImageView profilePic;
    private TextView textName,textViewCharge,textViewEmail;
    private MainActivity menuView;
    private presenterProfileInterface presenter;
    private User user;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile_editor, container, false);

        // Inicializar vistas con findViewById
        //textGallery = root.findViewById(R.id.textGallery);
        user= (User) getArguments().getSerializable("user");
        // Simulación de un ViewModel (descomentar si tienes GalleryViewModel)
        // GalleryViewModel galleryViewModel = new ViewModelProvider(this).get(GalleryViewModel.class);
        // galleryViewModel.getText().observe(getViewLifecycleOwner(), textGallery::setText);
        menuView=(MainActivity) getContext();

        initView(view);
        return view;
    }

    private void initView(View view) {
        spinner = view.findViewById(R.id.spinnerExample);
        profilePic= view.findViewById(R.id. profilePic);
        textName= view.findViewById(R.id.textName);
        textViewEmail= view.findViewById(R.id. textViewEmail);
        // Datos para el Spinner
        String[] opciones = {"Administracion", "Usuario", "RH","Developer"};

// Adaptador para el Spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, opciones);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

// Asignar adaptador al Spinner


        SharedPreferences preferences = getContext().getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);

        String role = preferences.getString(GeneralConstantsV2.ROLE_USER,null);
        textName.setText(user.getNameUser());
        textViewEmail.setText(user.getCorreoUsuario());
        Glide.with(this)
                .load(user.getPhotoUser())
                .apply(new RequestOptions()
                        .placeholder(R.drawable.ic_launcher_foreground)  // Placeholder while loading
                        .error(R.drawable.ic_launcher_foreground)       // Fallback if load fails
                        .centerCrop())                                  // Crop image to fill
                .into(profilePic);
        presenter=new presenterProfile(this,getContext());
        presenter.updateRole(user.getCorreoUsuario());
        spinner.setAdapter(adapter);
        if(role!=null) {
            if (role.equals("2") && !user.getCorreoUsuario().equals("newlandappscontact@gmail.com")) {
                spinner.setVisibility(View.GONE);
            }
        }
// Manejar selección de ítems
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String seleccion = opciones[position];
                // Toast.makeText(getContext(), "Seleccionaste: " + seleccion, Toast.LENGTH_SHORT).show();
                setUpRole(seleccion);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Acción cuando no se selecciona nada (opcional)
            }
        });

    }

    private void setUpRole(String seleccion) {
        String role="";
        if(seleccion.equals("Administracion")){
            role="1";
        }else if(seleccion.equals( "Usuario")) {
            role="2";
        }else if(seleccion.equals("RH")){
            role="3";
        }else if(seleccion.equals("Developer")){
            role="4";
        }
        SharedPreferences preferencias=getContext().getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferencias.edit();
        editor.putString(GeneralConstantsV2.ROLE_USER, role);
        editor.commit();
        menuView.updateRole();
    }

    @Override
    public void setUpRole() {
        menuView.updateRole();
    }

    @Override
    public void hideProgressDialog() {

    }

    @Override
    public void showProgresDialog() {

    }

    @Override
    public void succesGetRole() {

    }
    private void removethisFragment(){
        requireActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .remove(ProfileAdminEditor.this)
                .commit();
    }

    @Override
    public void onPause() {

        super.onPause();
    }
    @Override
    public void onDestroyView() {
        removethisFragment();
        super.onDestroyView();

        // Cleanup views, stop listeners, etc.
    }

    @Override
    public void onDestroy() {
        removethisFragment();
        super.onDestroy();
        // Final cleanup, releasing resources, etc.
    }

    @Override
    public void onDetach() {
        removethisFragment();
        super.onDetach();
        // Fragment is detached from the activity
    }
}