package com.NewLandApps.NewlandApps.ui.calendario;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.util.Pair;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.NewLandApps.NewlandApps.R;
import com.NewLandApps.NewlandApps.databinding.FragmentCalendarBinding;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateValidatorPointForward;
import com.google.android.material.datepicker.MaterialDatePicker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;

public class FragmentCalendar extends Fragment {
    private CalendarView calendarView;
    private Button btnSelectYear;
    private HashSet<Long> highlightedDates;
    private Calendar calendar;
    private RecyclerView rvCalendar,rvVacations;
    private adapterCalendar adapterFreedates;
    private adapterVacations adapterVacations;
    private List<String> puenteFechas = new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_calendar, container, false);

        calendarView = root.findViewById(R.id.calendarView);
        btnSelectYear = root.findViewById(R.id.btnSelectYear);
        rvCalendar =root.findViewById(R.id.rvCalendar);
        rvVacations =root.findViewById(R.id.rvVacations);
        calendar = Calendar.getInstance();

        // Configurar fechas a resaltar
        highlightedDates = new HashSet<>();
        highlightedDates.add(getTimeInMillis(2025, 0, 1));   // Año Nuevo (1 de enero)
        highlightedDates.add(getTimeInMillis(2025, 1, 2));   // Día de la Constitución (2 de febrero)
        highlightedDates.add(getTimeInMillis(2025, 2, 17));  // Natalicio de Benito Juárez (17 de marzo)
       // highlightedDates.add(getTimeInMillis(2025, 3, 17));  // Lunes de la Semana Santa
        highlightedDates.add(getTimeInMillis(2025, 4, 1));   // Día del Trabajo (1 de mayo)
        highlightedDates.add(getTimeInMillis(2025, 4, 18));  // Viernes Santo (18 de abril)
        highlightedDates.add(getTimeInMillis(2025, 8, 16));  // Día de la Independencia (16 de septiembre)
        highlightedDates.add(getTimeInMillis(2025, 10, 20)); // Día de la Revolución Mexicana (20 de noviembre)
        highlightedDates.add(getTimeInMillis(2025, 11, 25)); // 15 de junio de 2025
        puenteFechas.add("01 01 25: Año Nuevo");
        puenteFechas.add("02 02 25: Día de la Constitución");
        puenteFechas.add("17 03 25: Natalicio de Benito Juárez");
       // puenteFechas.add("17 04 25: Lunes de la Semana Santa");
        puenteFechas.add("01 05 25: Día del Trabajo");
        puenteFechas.add("18 05 25: Viernes Santo");
        puenteFechas.add("16 09 25: Día de la Independencia");
        puenteFechas.add("20 11 25: Día de la Revolución Mexicana");
        puenteFechas.add("25 12 25: Navidad");
        // Configurar selección de fecha
        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            long selectedDate = getTimeInMillis(year, month, dayOfMonth);
            if (highlightedDates.contains(selectedDate)) {
                Toast.makeText(getContext(), "¡Esta fecha está destacada!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Fecha seleccionada: " + dayOfMonth + "/" + (month + 1) + "/" + year, Toast.LENGTH_SHORT).show();
            }
        });

        // Botón para seleccionar el año
        btnSelectYear.setVisibility(View.GONE);
        btnSelectYear.setOnClickListener(v -> showDateRangePicker());
        fillAdapter();
        fillVacations();
        return root;
    }

    private void fillVacations  () {
        adapterVacations=new adapterVacations(getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rvVacations.setLayoutManager(layoutManager);
        rvVacations.setAdapter(adapterVacations);
    }

    private void fillAdapter() {
        rvCalendar.setNestedScrollingEnabled(false);
        adapterFreedates  = new adapterCalendar(puenteFechas,getContext());
//        adapterFreedates.setOnClickDateListener(UnitMapViewImplV3.this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rvCalendar.setLayoutManager(layoutManager);
        rvCalendar.setAdapter(adapterFreedates);
    }

    private long getTimeInMillis(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day, 0, 0, 0);
        return calendar.getTimeInMillis();
    }

    private void showDateRangePicker() {
        MaterialDatePicker<Pair<Long, Long>> dateRangePicker =
                MaterialDatePicker.Builder.dateRangePicker() 
                        .setTitleText("Selecciona un rango de fechas")
                        .setSelection(new Pair<>(MaterialDatePicker.thisMonthInUtcMilliseconds(),
                                MaterialDatePicker.todayInUtcMilliseconds()))
                        .build();

        dateRangePicker.addOnPositiveButtonClickListener(selection -> {
            if (selection.first != null && selection.second != null) {
                Calendar calendarStart = Calendar.getInstance();
                Calendar calendarEnd = Calendar.getInstance();
                calendarStart.setTimeInMillis(selection.first);
                calendarEnd.setTimeInMillis(selection.second);

                int startDay = calendarStart.get(Calendar.DAY_OF_MONTH);
                int startMonth = calendarStart.get(Calendar.MONTH) + 1;
                int startYear = calendarStart.get(Calendar.YEAR);

                int endDay = calendarEnd.get(Calendar.DAY_OF_MONTH);
                int endMonth = calendarEnd.get(Calendar.MONTH) + 1;
                int endYear = calendarEnd.get(Calendar.YEAR);

                String selectedRange = "Desde: " + startDay + "/" + startMonth + "/" + startYear +
                        " hasta " + endDay + "/" + endMonth + "/" + endYear;

                Toast.makeText(getContext(), selectedRange, Toast.LENGTH_LONG).show();
            }
        });

        dateRangePicker.show(getParentFragmentManager(), "DATE_RANGE_PICKER");
    }
}
