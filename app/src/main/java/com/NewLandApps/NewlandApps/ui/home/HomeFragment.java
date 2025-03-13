package com.NewLandApps.NewlandApps.ui.home;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
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
import com.NewLandApps.NewlandApps.retrofit.GeneralConstantsV2;
import com.NewLandApps.NewlandApps.ui.calendario.adapterCalendar;

public class HomeFragment extends Fragment implements View.OnClickListener {

    private TextView textHome,userManagement;
    private adapterPartners adapter;
    private RecyclerView rvParthners;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        HomeViewModel homeViewModel =
//                new ViewModelProvider(this).get(HomeViewModel.class);

        View view = inflater.inflate(R.layout.fragment_home, container, false);
       // textHome = view.findViewById(R.id.textHome);

       // homeViewModel.getText().observe(getViewLifecycleOwner(), textHome::setText);
        initView(view);
        return view;
    }

    private void initView(View view) {

        rvParthners=view.findViewById(R.id.rvParthners);
        userManagement =view.findViewById(R.id.userManagement);
        userManagement.setOnClickListener(this);
        fillAdapter();
        SharedPreferences preferences = getContext().getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String role = preferences.getString(GeneralConstantsV2.ROLE_USER, null);
        Log.e("ROLE","userRole: "+role);
        if(role!=null){
            if(role.equals("1")||role.equals("4")){
                userManagement.setVisibility(View.VISIBLE);
            }else{
                userManagement.setVisibility(View.GONE);
            }
        }
    }

    private void fillAdapter() {
        rvParthners.setNestedScrollingEnabled(false);
        adapter  = new adapterPartners(getContext());
//        adapterFreedates.setOnClickDateListener(UnitMapViewImplV3.this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rvParthners.setLayoutManager(layoutManager);
        rvParthners.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {

    }
}