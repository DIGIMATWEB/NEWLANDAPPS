package com.NewLandApps.NewlandApps.ui.calendario;

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

import com.NewLandApps.NewlandApps.databinding.FragmentCalendarBinding;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateValidatorPointForward;
import com.google.android.material.datepicker.MaterialDatePicker;

import java.util.Calendar;
import java.util.HashSet;

public class FragmentCalendar extends Fragment {
    private FragmentCalendarBinding binding;
    private CalendarView calendarView;
    private Button btnSelectYear;
    private HashSet<Long> highlightedDates;
    private Calendar calendar;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCalendarBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        calendarView = binding.calendarView;
        btnSelectYear = binding.btnSelectYear;
        calendar = Calendar.getInstance();

        // Configurar fechas a resaltar
        highlightedDates = new HashSet<>();
        highlightedDates.add(getTimeInMillis(2025, 2, 10)); // 10 de marzo de 2025
        highlightedDates.add(getTimeInMillis(2025, 5, 15)); // 15 de junio de 2025

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
        btnSelectYear.setOnClickListener(v ->showDateRangePicker()); //showYearPicker());

        return root;
    }

    // Método para mostrar el selector de año
    private void showYearPicker() {
        int currentYear = calendar.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                getContext(),
                (view, year, month, dayOfMonth) -> {
                    calendar.set(year, Calendar.JANUARY, 1);
                    calendarView.setDate(calendar.getTimeInMillis()); // Cambia el año en el calendario
                },
                currentYear, Calendar.JANUARY, 1
        );

        DatePicker datePicker = datePickerDialog.getDatePicker();

        // Ocultar día y mes si existen
        View dayView = datePicker.findViewById(getResources().getIdentifier("day", "id", "android"));
        if (dayView != null) {
            dayView.setVisibility(View.GONE);
        }

        View monthView = datePicker.findViewById(getResources().getIdentifier("month", "id", "android"));
        if (monthView != null) {
            monthView.setVisibility(View.GONE);
        }

        datePickerDialog.show();
    }


    // Método para convertir año, mes y día en milisegundos
    private long getTimeInMillis(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day, 0, 0, 0);
        return calendar.getTimeInMillis();
    }
    private void showMonthPicker() {
        CalendarConstraints.Builder constraintsBuilder = new CalendarConstraints.Builder()
                .setValidator(DateValidatorPointForward.now());

        MaterialDatePicker<Long> datePicker = MaterialDatePicker.Builder
                .datePicker()
                .setTitleText("Selecciona una fecha")
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .setCalendarConstraints(constraintsBuilder.build())
                .build();

        datePicker.addOnPositiveButtonClickListener(selection -> {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(selection);
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH) + 1;
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            Toast.makeText(getContext(), "Fecha seleccionada: " + day + "/" + month + "/" + year, Toast.LENGTH_SHORT).show();
        });

        datePicker.show(getParentFragmentManager(), "MONTH_PICKER");
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}