package com.NewLandApps.NewlandApps.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.NewLandApps.NewlandApps.R;
import com.NewLandApps.NewlandApps.databinding.FragmentGalleryBinding;
import com.NewLandApps.NewlandApps.ui.calendario.adapterVacations;

public class GalleryFragment extends Fragment {

    private TextView textGallery;
    private adapterBirthdaysmotnhs adapter;
    private RecyclerView rvMonths;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gallery, container, false);

         initView(view);


        return view;
    }

    private void initView(View view) {
        rvMonths=view.findViewById(R.id.rvMonths);
        fillAdapter();

    }

    private void fillAdapter(){
        adapter=new adapterBirthdaysmotnhs(getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rvMonths.setLayoutManager(layoutManager);
        rvMonths.setAdapter(adapter);
    }

}