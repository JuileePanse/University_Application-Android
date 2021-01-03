package com.npu_app.npu_application.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.npu_app.npu_application.R;
import com.npu_app.npu_application.adapters.GradeDetailsAdapter;
import com.npu_app.npu_application.model.GradeDetails;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FragmentGradeDetails extends Fragment {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    List<GradeDetails> gradeDetailsList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_grade_details, container, false);
        recyclerView = view.findViewById(R.id.horizontalRecyclerView);
        initData();
        setRecyclerView();
        return view;
    }

    public void setRecyclerView() {
        GradeDetailsAdapter gradeDetailsAdapter = new GradeDetailsAdapter(gradeDetailsList);
        recyclerView.setAdapter(gradeDetailsAdapter);
        layoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        DividerItemDecoration itemDecorator = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        itemDecorator.setDrawable(ContextCompat.getDrawable(getContext(), R.drawable.divider));

        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL));
    }

    private void initData() {
        gradeDetailsList = new ArrayList<>();
        gradeDetailsList.add(new GradeDetails("CS480 Java and Internet Programming", "3", "4.0", "A"));
        gradeDetailsList.add(new GradeDetails("CS481 Introduction to Data Science", "3", "3.7", "A-"));
    }
}

