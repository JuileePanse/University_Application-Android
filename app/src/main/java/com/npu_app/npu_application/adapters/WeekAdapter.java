package com.npu_app.npu_application.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.npu_app.npu_application.R;
import com.npu_app.npu_application.model.Week;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class WeekAdapter extends RecyclerView.Adapter<WeekAdapter.WeekVH> {
     List<Week> weeklist;
     Context context;
     public WeekAdapter(Context context, List<Week> weeklist) {
         this.weeklist = weeklist;
         this.context = context;
     }

    @NonNull
    @Override
    public WeekAdapter.WeekVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_week,parent,false);
        return new WeekAdapter.WeekVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WeekAdapter.WeekVH holder, int position) {
         final Week w =weeklist.get(position);
         holder.weekNoTxt.setText(w.getWeekNo());
         holder.activityTxt.setText(Html.fromHtml(w.getActivity()));
         holder.handoutTxt.setText(Html.fromHtml(w.getHandout()));

        boolean isExpandable = weeklist.get(position).isExpandable();
        holder.expandableLayout.setVisibility(isExpandable ? View.VISIBLE :View.GONE);

        holder.activityTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.parse(w.getLink()), "application/pdf");
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
        holder.handoutTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.parse(w.getLink()), "application/pdf");
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return weeklist.size();
    }

    /* here WeekVH is viewholder classname*/

    public class WeekVH extends RecyclerView.ViewHolder {
        TextView activityTxt;
        TextView handoutTxt;
        TextView weekNoTxt;
        //get our layouts
        LinearLayout linerarLayout;
        RelativeLayout expandableLayout;

        public WeekVH(View itemView) {
            super(itemView);

            weekNoTxt = itemView.findViewById(R.id.Week_No);
            activityTxt = itemView.findViewById(R.id.activity);
            handoutTxt = itemView.findViewById(R.id.handout);
            linerarLayout = itemView.findViewById(R.id.liner_layout);
            expandableLayout = itemView.findViewById(R.id.expandebal_layout);

            // here i am set a click on listener on linearlayout

            linerarLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Week w = weeklist.get(getAdapterPosition());
                    w.setExpandable(!w.isExpandable());
                    notifyItemChanged(getAdapterPosition());

                }
            });

        }
    }
}
