package com.liuxueliang.measurementtoolboxes.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.liuxueliang.measurementtoolboxes.R;

import org.jetbrains.annotations.Nullable;

public class aboutFragment extends Fragment {

    public static aboutFragment newInstance() {
        return new aboutFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.about_fragment, container, false);




    }


}
