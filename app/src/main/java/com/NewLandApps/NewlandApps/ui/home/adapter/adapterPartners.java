package com.NewLandApps.NewlandApps.ui.home.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.NewLandApps.NewlandApps.R;
import com.NewLandApps.NewlandApps.ui.home.model.User;
import com.NewLandApps.NewlandApps.ui.home.view.HomeFragment;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class adapterPartners extends RecyclerView.Adapter<adapterPartners.ViewHolder> {

//    private List<String> puenteFechas;
//    private int positionOnClick = 0;
//    private adapterCalendar.OnDateClickListener listener;
    private Context context;
    private List<User> usuarios;
    private HomeFragment mview;

    public adapterPartners(HomeFragment mview, List<User> usuarios, Context context) {
        //this.puenteFechas = puenteFechas;
        this.usuarios=usuarios;
        this.context = context;
        this.mview=mview;
    }

    @NonNull
    @Override
    public adapterPartners.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_parthners, parent, false);
        return new adapterPartners.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adapterPartners.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        String imageUrl = usuarios.get(position).getPhotoUser();
        Log.e("currenPhoto",imageUrl);

        Glide.with(context)
                .load(imageUrl)
                .apply(new RequestOptions()  // Set a placeholder while loading
                        .error(R.drawable.ic_launcher_foreground) // Set fallback if load fails
                        .centerCrop())
                .into(holder.parthnersImage);
        holder.parthnersImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mview.editUser(usuarios.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return usuarios.size();
    }


    public interface OnDateClickListener {
        void onDate(String date, int position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ConstraintLayout constraintDateMapV2,hiddenDate;
        private TextView txvDateDetailCar, txvDateM;
        private ImageView parthnersImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txvDateDetailCar = itemView.findViewById(R.id.txvDateDetailCar3);
            txvDateM = itemView.findViewById(R.id.txvDateM);
            constraintDateMapV2 = itemView.findViewById(R.id.constraintDateMapV2);
            hiddenDate = itemView.findViewById(R.id. hiddenDate);
            parthnersImage= itemView.findViewById(R.id. parthnersImage);
        }
    }
}