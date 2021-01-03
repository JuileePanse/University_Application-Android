package com.npu_app.npu_application.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import com.npu_app.npu_application.R;
import com.npu_app.npu_application.activities.MainActivity;
import com.npu_app.npu_application.adapters.HomeScreenAdapter;
import com.npu_app.npu_application.adapters.ImageAdapter;

import java.util.ArrayList;
import java.util.List;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

public class FragmentHomeScreen extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_screen, parent, false);

        List<String> homelist = new ArrayList<>();
        homelist.add("Home");
        homelist.add("Class Schedule");
        homelist.add("Grades");
        homelist.add("Calender");
        homelist.add("Profile");
        homelist.add("Settings");

        int logos[] = {R.drawable.home_large, R.drawable.class_schedule_large, R.drawable.grades_large, R.drawable.calender_large,
                R.drawable.profile_large, R.drawable.settings_large};

        GridView gridView = (GridView)view.findViewById(R.id.gridview);
        HomeScreenAdapter booksAdapter = new HomeScreenAdapter(getActivity(),logos, homelist);
        gridView.setAdapter(booksAdapter);

        // implement setOnItemClickListener event on GridView
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ((MainActivity)getActivity()).selectDrawerItem(((MainActivity)getActivity()).navigationView.getMenu().getItem(position));
            }
        });

        setUpViewpager(view);
        return view;
    }

    void setUpViewpager(View view){
        ViewPager mViewPager = (ViewPager) view.findViewById(R.id.viewPager);
        ImageAdapter adapterView = new ImageAdapter(getActivity());
        mViewPager.setAdapter(adapterView);

    }
}
