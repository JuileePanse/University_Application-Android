package com.npu_app.npu_application.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.npu_app.npu_application.R;
import com.npu_app.npu_application.model.Events;
import java.util.ArrayList;
import java.util.List;

public class EventListAdapter extends BaseAdapter {
    private List<Events> listData;
    private LayoutInflater layoutInflater;
    public EventListAdapter(Context aContext, List<Events> listData) {
        this.listData = listData;
        layoutInflater = LayoutInflater.from(aContext);
    }
    @Override
    public int getCount() {
        return listData.size();
    }
    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    public View getView(int position, View v, ViewGroup vg) {
        ViewHolder holder;
        if (v == null) {
            v = layoutInflater.inflate(R.layout.event_list_row, null);
            holder = new ViewHolder();
            holder.eventDetails = (TextView) v.findViewById(R.id.event_details);
            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }
        holder.eventDetails.setText(listData.get(position).getEvent_name() + " \n" +listData.get(position).getEvent_description());

        return v;
    }
    static class ViewHolder {
        TextView eventDetails;
    }
}