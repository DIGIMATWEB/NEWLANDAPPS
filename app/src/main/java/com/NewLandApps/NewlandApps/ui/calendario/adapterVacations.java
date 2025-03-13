package com.NewLandApps.NewlandApps.ui.calendario;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.NewLandApps.NewlandApps.R;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class adapterVacations extends RecyclerView.Adapter<adapterVacations.ViewHolder> {

    private Context context;

    public adapterVacations(Context context) {
        //this.puenteFechas = puenteFechas;
        this.context = context;
    }

    @NonNull
    @Override
    public adapterVacations.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vacations, parent, false);
        return new adapterVacations.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adapterVacations.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        // Crear las listas dentro del método para que se genere una nueva para cada vista
    }

    @Override
    public int getItemCount() {
        return 8;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}