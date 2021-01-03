package com.npu_app.npu_application.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.npu_app.npu_application.R;

import androidx.fragment.app.Fragment;

public class FragmentSettings extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, parent, false);

        Switch sw = (Switch) view.findViewById(R.id.switch1);
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(getActivity(), "Notifications enabled", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "Notifications disabled", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }
}
