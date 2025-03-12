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
import java.util.HashSet;
import java.util.List;

public class adapterCalendar extends RecyclerView.Adapter<adapterCalendar.ViewHolder> {

    private List<String> puenteFechas;
    private int positionOnClick = 0;
    private OnDateClickListener listener;
    private Context context;

    public adapterCalendar(List<String> puenteFechas, Context context) {
        this.puenteFechas = puenteFechas;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dates, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        // Crear las listas dentro del método para que se genere una nueva para cada vista
        List<String> días = new ArrayList<>();
        List<String> mesesAbreviados = new ArrayList<>();
        String[] meses = {"Ene", "Feb", "Mar", "Abr", "May", "Jun", "Jul", "Ago", "Sep", "Oct", "Nov", "Dic"};

        // Extraemos las fechas de la lista puenteFechas
        String fecha = puenteFechas.get(position);
        String[] partesFecha = fecha.split(" ");

        // Día (primeros dos dígitos)
        días.add(partesFecha[0]);

        // Mes (abreviado)
        int mesIndex = Integer.parseInt(partesFecha[1]) - 1; // Restamos 1 porque los meses en el array empiezan desde 0
        mesesAbreviados.add(meses[mesIndex]);

        // Asignar los valores al TextView
        holder.txvDateDetailCar.setText(TextUtils.join(", ", días));
        holder.txvDateM.setText(TextUtils.join(", ", mesesAbreviados));
// Convertir la fecha del puente a un objeto LocalDate para compararlo
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy");
        LocalDate fechaPuente = LocalDate.parse(partesFecha[0] + " " + partesFecha[1] + " 2025", formatter);
        LocalDate fechaActual = LocalDate.now();

        // Comparar la fecha del puente con la fecha actual
        if (fechaPuente.isAfter(fechaActual)) {
            // Si la fecha es posterior a la fecha actual, hacer visible el TextView hiddenDate
            holder.hiddenDate.setVisibility(View.GONE);
        } else {
            // Si no, ocultarlo
            holder.hiddenDate.setVisibility(View.VISIBLE);
        }
        // Set onClickListener en el item si se requiere
        holder.constraintDateMapV2.setOnClickListener(v -> {
            if (listener != null) {
                listener.onDate(fecha, position); // Llamamos al listener con la fecha y posición
            }
        });
    }

    @Override
    public int getItemCount() {
        return puenteFechas.size();
    }

    public void setPosition1Clicked() {
        // Obtiene la primera fecha, si es necesario
        if (puenteFechas != null && !puenteFechas.isEmpty()) {
            positionOnClick = 0;
            if (listener != null) {
                listener.onDate(puenteFechas.get(0), 0); // Llamamos al listener con la primera fecha
            }
            notifyDataSetChanged();
        }
    }

    public void setOnClickDateListener(OnDateClickListener listener) {
        this.listener = listener;
    }

    public interface OnDateClickListener {
        void onDate(String date, int position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ConstraintLayout constraintDateMapV2,hiddenDate;
        private TextView txvDateDetailCar, txvDateM;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txvDateDetailCar = itemView.findViewById(R.id.txvDateDetailCar3);
            txvDateM = itemView.findViewById(R.id.txvDateM);
            constraintDateMapV2 = itemView.findViewById(R.id.constraintDateMapV2);
            hiddenDate = itemView.findViewById(R.id. hiddenDate);
        }
    }
}