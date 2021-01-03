package com.npu_app.npu_application.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.npu_app.npu_application.R;
import com.npu_app.npu_application.adapters.WeekAdapter;
import com.npu_app.npu_application.model.Week;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public class FragmentWeek extends Fragment {

    RecyclerView recyclerView;
    List<Week> weekList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_week, parent, false);
        recyclerView = view.findViewById(R.id.recyclerView);

        initData();
        setRecyclerView();

        return view;
    }

    private void setRecyclerView() {
        WeekAdapter weekAdapter = new WeekAdapter(getActivity(), weekList);
        recyclerView.setAdapter(weekAdapter);
        recyclerView.setHasFixedSize(true);
    }

    private void initData() {
        weekList = new ArrayList<>();
        weekList.add(new Week( "Chapter 1","<a href=\"gs://resetpassword-1e535.appspot.com/Introduction_to_Datascience.pdf\">Introduction_to_Datascience.pdf</a>", 1 , "https://firebasestorage.googleapis.com/v0/b/resetpassword-1e535.appspot.com/o/Introduction_to_Datascience.pdf?alt=media&token=fbdc870b-9d2b-4eb9-9155-233ebbdadf47"));
        weekList.add(new Week( "Chapter 2","<a href=\"gs://resetpassword-1e535.appspot.com/Data_exploration.pdf\">Data_exploration.pdf</a>", 2 , "https://firebasestorage.googleapis.com/v0/b/resetpassword-1e535.appspot.com/o/Data_exploration.pdf?alt=media&token=5dc566c8-c804-4a8c-a28e-1ec9fee5402c"));
        weekList.add(new Week( "Chapter 3","<a href=\"gs://resetpassword-1e535.appspot.com/Regression_methods.pdf\">Regression_methods.pdf</a>", 3 ,"https://firebasestorage.googleapis.com/v0/b/resetpassword-1e535.appspot.com/o/Regression_methods.pdf?alt=media&token=235a74b3-df92-4287-abfe-87c41913c825"));
        weekList.add(new Week( "Chapter 4","<a href=\"gs://resetpassword-1e535.appspot.com/Model_evaluation.pdf\">Model_evaluation.pdf</a>", 4 , "https://firebasestorage.googleapis.com/v0/b/resetpassword-1e535.appspot.com/o/Model_evaluation.pdf?alt=media&token=cb9b3e62-568c-4e01-bc5a-6db53e21ebb7"));
        weekList.add(new Week( "Chapter 5","<a href=\"gs://resetpassword-1e535.appspot.com/deep_learning.pdf\">Deep_learning.pdf</a>", 5 , "https://firebasestorage.googleapis.com/v0/b/resetpassword-1e535.appspot.com/o/deep_learning.pdf?alt=media&token=4de423be-cfba-4177-b17d-82543ff5cfce"));
    }
}
