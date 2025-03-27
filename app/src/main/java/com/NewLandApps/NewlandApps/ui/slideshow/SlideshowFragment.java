package com.NewLandApps.NewlandApps.ui.slideshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.NewLandApps.NewlandApps.R;
import com.NewLandApps.NewlandApps.databinding.FragmentSlideshowBinding;

public class SlideshowFragment extends Fragment {

    private TextView textSlideshow;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SlideshowViewModel slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);

        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);
        textSlideshow = root.findViewById(R.id.text_slideshow);

        slideshowViewModel.getText().observe(getViewLifecycleOwner(), textSlideshow::setText);
        return root;
    }
}