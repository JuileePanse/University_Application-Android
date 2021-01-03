package com.npu_app.npu_application.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.npu_app.npu_application.R;
import com.npu_app.npu_application.adapters.CourseAdapter;
import com.npu_app.npu_application.controller.DatabaseAccess;
import com.npu_app.npu_application.interfaces.WeekInterface;
import com.npu_app.npu_application.model.ClassSchedule;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;


public class FragmentClassSchedule extends Fragment implements WeekInterface {

    RecyclerView recyclerView;
    List<ClassSchedule> classScheduleList;
    List<ClassSchedule> classSchedule;
    private DatabaseAccess db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_class_schedule, parent, false);
        recyclerView = view.findViewById(R.id.recyclerView);

        db = DatabaseAccess.getInstance(getActivity());
        classSchedule = db.getCourseInfo();
        //initData();
        setRecyclerView();

        return view;
    }

    private void setRecyclerView() {
        CourseAdapter courseAdaptor = new CourseAdapter(classSchedule, this);
        recyclerView.setAdapter(courseAdaptor);
        recyclerView.setHasFixedSize(true);
    }

    private void initData() {
        classScheduleList = new ArrayList<>();

        for(int i=0; i<classSchedule.size(); i++){
            classScheduleList.add(classSchedule.get(i));
        }
    }


    @Override
    public void onClickAtOKButton(String courseName) {
        //Display week Activities &
        Fragment newFragment = new FragmentWeek();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, newFragment).commit();
    }
}
