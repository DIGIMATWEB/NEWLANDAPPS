package com.NewLandApps.NewlandApps.ui.vacaciones;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.util.Pair;
import androidx.fragment.app.Fragment;

import com.NewLandApps.NewlandApps.R;
import com.NewLandApps.NewlandApps.retrofit.GeneralConstantsV2;
import com.google.android.material.datepicker.MaterialDatePicker;

import java.time.LocalDate;
import java.util.Calendar;

public class vacacionesFragment extends Fragment {
//    private Button btnSeleccionarRango;
//    private TextView txtRangoFechas;
    private EditText
                    editTextDateSolicitud,
                    editTextName,
                    editTextPuesto,
                    editTextArea,
                    editTextIngreso,
                    editTextAño,
                    editTextDias,
                    editTextSaldo,
                    editTextSaldoAutorizado,
                    editTextDayInit,
                    editTextDayEnd,
                    editTextDayReturn,
                    editTextSignatureC,
                    editTextSignatureB,
                    editTextAut,
                    editTextAutNo,
                    editTextDesc,
                    editTextRH;



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vacaciones, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        editTextDateSolicitud = view.findViewById(R.id.editTextDateSolicitud);
        editTextName = view.findViewById(R.id.editTextName);
        editTextPuesto = view.findViewById(R.id.editTextPuesto);
        editTextArea = view.findViewById(R.id.editTextArea);
        editTextIngreso = view.findViewById(R.id.editTextIngreso);
        editTextAño = view.findViewById(R.id.editTextAño);
        editTextDias = view.findViewById(R.id.editTextDias);
        editTextSaldo = view.findViewById(R.id.editTextSaldo);
        editTextSaldoAutorizado = view.findViewById(R.id.editTextSaldoAutorizado);
        editTextDayInit = view.findViewById(R.id.editTextDayInit);
        editTextDayEnd = view.findViewById(R.id.editTextDayEnd);
        editTextDayReturn = view.findViewById(R.id.editTextDayReturn);
        editTextSignatureC = view.findViewById(R.id.editTextSignatureC);
        editTextSignatureB = view.findViewById(R.id.editTextSignatureB);
        editTextAut = view.findViewById(R.id.editTextAut);
        editTextAutNo = view.findViewById(R.id.editTextAutNo);
        editTextDesc = view.findViewById(R.id.editTextDesc);
        editTextRH = view.findViewById(R.id.editTextRH);
        setUpatBegining();
    }

    private void setUpatBegining() {
        //TODO estos datos se inician por defecto al iniciar el fragmaneto
        //nombre , cago, area , fecha de ingreso(si es que lo trae en el shared despues de la respuesta del login), año
        //  dias correspondientes//saldo por gozar//

        SharedPreferences preferences = getContext().getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String name = preferences.getString(GeneralConstantsV2.USER_PREFERENCES, null);
        if(name!=null){
            editTextName.setText(name);
        }
        int currentYear = LocalDate.now().getYear();
        editTextAño.setText(String.valueOf("  "+currentYear));

    }

}

