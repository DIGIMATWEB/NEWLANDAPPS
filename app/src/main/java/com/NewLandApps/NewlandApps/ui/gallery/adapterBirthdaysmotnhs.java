package com.NewLandApps.NewlandApps.ui.gallery;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.NewLandApps.NewlandApps.R;

public class adapterBirthdaysmotnhs extends RecyclerView.Adapter<adapterBirthdaysmotnhs.ViewHolder> {

    //    private List<String> puenteFechas;
//    private int positionOnClick = 0;
//    private adapterCalendar.OnDateClickListener listener;
    private Context context;
    private  String[] months = {"Enero", "Febrero", "Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"};

    public adapterBirthdaysmotnhs( Context context) {
        //this.puenteFechas = puenteFechas;
        this.context = context;
    }

    @NonNull
    @Override
    public adapterBirthdaysmotnhs.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_months, parent, false);
        return new adapterBirthdaysmotnhs.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adapterBirthdaysmotnhs.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
            holder.textMonth.setText(months[position]);
            holder.cardView5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(holder.rvPeopleOnMonth.getVisibility()==View.VISIBLE){
                        holder.rvPeopleOnMonth.setVisibility(View.GONE);
                    }else {
                        holder.rvPeopleOnMonth.setVisibility(View.VISIBLE);
                        adapterPeopleMonths adapter;
                        holder.rvPeopleOnMonth.setNestedScrollingEnabled(false);
                        adapter  = new adapterPeopleMonths(context);
//        adapterFreedates.setOnClickDateListener(UnitMapViewImplV3.this);
                        LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
                        holder.rvPeopleOnMonth.setLayoutManager(layoutManager);
                        holder.rvPeopleOnMonth.setAdapter(adapter);
                    }
                }
            });

    }

    @Override
    public int getItemCount() {
        return months.length;
    }


    public interface OnDateClickListener {
        void onDate(String date, int position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private Spinner spinnerMonth;
        private TextView textMonth;
        private RecyclerView rvPeopleOnMonth;
        private CardView cardView5;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView5=itemView.findViewById(R.id. cardView5);
            textMonth=itemView.findViewById(R.id.textMonth);
            spinnerMonth = itemView.findViewById(R.id.spinnerMonth);
            rvPeopleOnMonth =itemView.findViewById(R.id.rvPeopleOnMonth);
            fillAdapterMonthsBirthdays();
        }

        private void fillAdapterMonthsBirthdays() {

            }

    }
}