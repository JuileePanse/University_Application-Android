package com.npu_app.npu_application.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.npu_app.npu_application.R;
import com.npu_app.npu_application.interfaces.GradeInterface;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GradeAdapter extends RecyclerView.Adapter<GradeAdapter.GradeVH>{

    List<String> titles;
    List<String> subtitle;
    List<Integer> images;
    LayoutInflater inflater;
    GradeInterface gListener;

    public GradeAdapter(List<String> titles, List<String> subtitle, List<Integer> images, GradeInterface gListener) {
        this.titles = titles;
        this.subtitle = subtitle;
        this.images = images;
        this.gListener = gListener;
    }

    @NonNull
    @Override
    public GradeAdapter.GradeVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_grades, parent, false);
        return new GradeAdapter.GradeVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GradeAdapter.GradeVH holder, final int position) {
        holder.title.setText(titles.get(position));
        holder.subtitle.setText((subtitle.get(position)));
        holder.gridIcon.setImageResource(images.get(position));
        /*holder.gridIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Click-GradeAdaptor. .");
                gListener.onClickAtOKButton(titles.get(position));
            }
        });
        */
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

    public class GradeVH extends RecyclerView.ViewHolder {
        TextView title;
        TextView subtitle;
        ImageView gridIcon;

        public GradeVH(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.semYear);
            subtitle = itemView.findViewById(R.id.GPA);
            gridIcon = itemView.findViewById(R.id.semImage);
            itemView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    gListener.onClickAtOKButton(titles.get(getAdapterPosition()));
                }
            });
        }
    }
}
