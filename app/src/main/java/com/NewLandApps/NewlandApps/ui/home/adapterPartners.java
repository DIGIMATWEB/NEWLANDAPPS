package com.NewLandApps.NewlandApps.ui.home;

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
import com.NewLandApps.NewlandApps.ui.calendario.adapterCalendar;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class adapterPartners extends RecyclerView.Adapter<adapterCalendar.ViewHolder> {

//    private List<String> puenteFechas;
//    private int positionOnClick = 0;
//    private adapterCalendar.OnDateClickListener listener;
    private Context context;

    public adapterPartners( Context context) {
        //this.puenteFechas = puenteFechas;
        this.context = context;
    }

    @NonNull
    @Override
    public adapterCalendar.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_parthners, parent, false);
        return new adapterCalendar.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adapterCalendar.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
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