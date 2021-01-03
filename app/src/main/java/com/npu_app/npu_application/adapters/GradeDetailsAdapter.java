package com.npu_app.npu_application.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.npu_app.npu_application.R;
import com.npu_app.npu_application.model.GradeDetails;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GradeDetailsAdapter extends RecyclerView.Adapter<GradeDetailsAdapter.GradeDetailsVH> {
    private List<GradeDetails> gradeDetailList;

    public GradeDetailsAdapter(List<GradeDetails> gradeDetailList) {
        this.gradeDetailList = gradeDetailList;
    }

    @NonNull
    @Override
    public GradeDetailsAdapter.GradeDetailsVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_gradedetails, parent, false);
        return new GradeDetailsVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GradeDetailsAdapter.GradeDetailsVH holder, int position) {
        GradeDetails gd = gradeDetailList.get(position);
        System.out.println(gd.getCourseName());
        holder.coNameTxt.setText(gd.getCourseName());
        holder.coCreditTxt.setText(gd.getCourseCredit());
        holder.coGpa.setText(gd.getGpa());
        holder.coGrade.setText(gd.getGrade());
    }

    @Override
    public int getItemCount() {
    return gradeDetailList.size();
    }

    public class GradeDetailsVH extends RecyclerView.ViewHolder {
        public TextView coNameTxt;
        public TextView coCreditTxt;
        public TextView coGpa;
        public TextView coGrade;

        public View layout;
        public GradeDetailsVH(View v) {
            super(v);

            layout = v;
            coNameTxt = (TextView) v.findViewById(R.id.Course_name);
            coCreditTxt = (TextView) v.findViewById(R.id.Course_credit);
            coGpa = (TextView) v.findViewById(R.id.GPA);
            coGrade = (TextView) v.findViewById(R.id.grade);
        }


    }
}
