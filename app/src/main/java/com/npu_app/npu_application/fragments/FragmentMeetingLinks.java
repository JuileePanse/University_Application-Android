package com.npu_app.npu_application.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.npu_app.npu_application.R;

import androidx.fragment.app.Fragment;

public class FragmentMeetingLinks extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_meeting_links, parent, false);
        return view;
    }
}
