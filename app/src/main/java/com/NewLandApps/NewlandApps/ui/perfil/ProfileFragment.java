package com.NewLandApps.NewlandApps.ui.perfil;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.NewLandApps.NewlandApps.R;
import com.NewLandApps.NewlandApps.databinding.FragmentGalleryBinding;
import com.NewLandApps.NewlandApps.databinding.FragmentProfileBinding;
import com.NewLandApps.NewlandApps.retrofit.GeneralConstantsV2;

public class ProfileFragment extends Fragment {

    private TextView textGallery;
    private Spinner spinner;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        // Inicializar vistas con findViewById
        //textGallery = root.findViewById(R.id.textGallery);

        // Simulación de un ViewModel (descomentar si tienes GalleryViewModel)
        // GalleryViewModel galleryViewModel = new ViewModelProvider(this).get(GalleryViewModel.class);
        // galleryViewModel.getText().observe(getViewLifecycleOwner(), textGallery::setText);
        initView(view);
        return view;
    }

    private void initView(View view) {
        spinner = view.findViewById(R.id.spinnerExample);
        // Datos para el Spinner
        String[] opciones = {"Administracion", "Usuario", "RH","Developer"};

// Adaptador para el Spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, opciones);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

// Asignar adaptador al Spinner
        spinner.setAdapter(adapter);

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
    }
}