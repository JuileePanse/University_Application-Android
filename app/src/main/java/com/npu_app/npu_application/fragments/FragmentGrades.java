package com.npu_app.npu_application.fragments;

import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

import com.npu_app.npu_application.R;
import com.npu_app.npu_application.adapters.GradeAdapter;
import com.npu_app.npu_application.interfaces.GradeInterface;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class FragmentGrades extends Fragment implements GradeInterface {
    RecyclerView recyclerView;
    List<String> titles;
    List<String> subtitles;
    List<Integer> images;
    GradeAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_grades, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);

        initData();
        setRecyclerView();
        return view;
    }

    private void initData() {
        titles = new ArrayList<>();
        subtitles = new ArrayList<>();
        images = new ArrayList<>();

        titles.add("Fall 2019");
        titles.add("Spring 2020");
        titles.add("Summer 2020");
        titles.add("Fall 2020");
        titles.add("Spring 2021");
        titles.add("Summer 2021");
        titles.add("Fall 2021");

        subtitles.add(" 3.94");
        subtitles.add(" 3.30");
        subtitles.add(" 3.50");
        subtitles.add(" N/A");
        subtitles.add(" 3.2");
        subtitles.add(" 3.4");
        subtitles.add(" 3.7");

        images.add(R.drawable.fall);
        images.add(R.drawable.spring);
        images.add(R.drawable.summer);
        images.add(R.drawable.fall);
        images.add(R.drawable.spring);
        images.add(R.drawable.summer);
        images.add(R.drawable.fall);
    }

    private void setRecyclerView() {
        adapter = new GradeAdapter(titles,subtitles, images, this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this.getActivity(), 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
    }

    @Override
    public void onClickAtOKButton(String semYear) {
        Fragment newFragment = new FragmentGradeDetails();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, newFragment).commit();
    }
    /*
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_grades);
        recyclerView = findViewById(R.id.recyclerView);




        adapter = new GradeAdapter(this.getActivity(), titles, images);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this.getActivity(),2,GridLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);
    }
 */
}

