package com.NewLandApps.NewlandApps.ui.vacaciones;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.util.Pair;
import androidx.fragment.app.Fragment;

import com.NewLandApps.NewlandApps.R;
import com.google.android.material.datepicker.MaterialDatePicker;

import java.util.Calendar;

public class vacacionesFragment extends Fragment {
//    private Button btnSeleccionarRango;
//    private TextView txtRangoFechas;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vacaciones, container, false);
        return view;
    }

}

