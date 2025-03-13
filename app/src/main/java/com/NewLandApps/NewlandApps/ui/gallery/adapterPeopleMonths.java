package com.NewLandApps.NewlandApps.ui.gallery;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.NewLandApps.NewlandApps.R;

import java.util.ArrayList;
import java.util.List;

public class adapterPeopleMonths  extends RecyclerView.Adapter<adapterPeopleMonths.ViewHolder> {

    //    private List<String> puenteFechas;
//    private int positionOnClick = 0;
//    private adapterCalendar.OnDateClickListener listener;
    private Context context;

    public adapterPeopleMonths( Context context) {
        //this.puenteFechas = puenteFechas;
        this.context = context;

    }

    @NonNull
    @Override
    public adapterPeopleMonths.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_people_months, parent, false);
        return new adapterPeopleMonths.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adapterPeopleMonths.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {

    }

    @Override
    public int getItemCount() {
        return 4;
    }


    public interface OnDateClickListener {
        void onDate(String date, int position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private Spinner spinnerMonth;
        private TextView textMonth;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textMonth=itemView.findViewById(R.id.textMonth);
            spinnerMonth = itemView.findViewById(R.id.spinnerMonth);
        }
    }
}