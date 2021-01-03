package com.npu_app.npu_application.adapters;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.npu_app.npu_application.R;
import com.npu_app.npu_application.interfaces.WeekInterface;
import com.npu_app.npu_application.model.ClassSchedule;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ClassScheduleVH> {
    // store a member variable  for the ClassSchedule
    List<ClassSchedule> classSchedulelist;
    private WeekInterface wListener;

    // pass in the ClassSchedule array into the constructor
    public CourseAdapter(List<ClassSchedule> classSchedulelist, WeekInterface wListener) {
        this.wListener = wListener;
        this.classSchedulelist = classSchedulelist;
    }
//

    @NonNull
    @Override
    public CourseAdapter.ClassScheduleVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_course, parent,false);
        return new CourseAdapter.ClassScheduleVH(view);

    }

    @Override
    public void onBindViewHolder(@NonNull CourseAdapter.ClassScheduleVH holder, int position) {
        // get the data model based on positions
        final ClassSchedule classSchedule = classSchedulelist.get(position);

        //set items view based on your views and data

        holder.courseNameTxt.setText(classSchedule.getCourseName());
        holder.instructorNameTxt.setText(classSchedule.getInstructorName());
        holder.courseCreditTxt.setText(classSchedule.getCourseCredit());
        holder.timeTxt.setText(classSchedule.getTime());
        holder.meetingLinkTxt.setText(Html.fromHtml(classSchedule.getMeetingLink()));
        holder.weekDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("click");
                wListener.onClickAtOKButton(classSchedule.getCourseName());

            }
        });

        boolean isExpandable =classSchedulelist.get(position).isExpandable();
        holder.expandableLayout.setVisibility(isExpandable ? View.VISIBLE :View.GONE);

    }

    @Override
    public int getItemCount() {
        return classSchedulelist.size();
    }

    /* here ClassScheduleVH is viewholder class name */

    public class ClassScheduleVH extends RecyclerView.ViewHolder {
        TextView courseNameTxt, instructorNameTxt, courseCreditTxt, timeTxt, meetingLinkTxt;
        Button weekDetailsButton;
        // get our layouts
        LinearLayout linearLayout;
        RelativeLayout expandableLayout;

        public ClassScheduleVH(@NonNull View itemView) {
            super(itemView);

            courseNameTxt = itemView.findViewById(R.id.Course_name);
            instructorNameTxt = itemView.findViewById(R.id.Instructor_name);
            courseCreditTxt = itemView.findViewById(R.id.Course_credit);
            timeTxt = itemView.findViewById(R.id.time);
            meetingLinkTxt = itemView.findViewById(R.id.meeting_links);
            weekDetailsButton = itemView.findViewById(R.id.week_button);

            linearLayout = itemView.findViewById(R.id.liner_layout);
            expandableLayout = itemView.findViewById(R.id.expandebal_layout);


            // here i am  set a click on listener on linearlayout
            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ClassSchedule classSchedule = classSchedulelist.get(getAdapterPosition());
                    classSchedule.setExpandable(!classSchedule.isExpandable());
                    notifyItemChanged(getAdapterPosition());
                }
            });


        }
    }
}

