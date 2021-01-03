package com.npu_app.npu_application.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.npu_app.npu_application.R;
import java.util.List;

public class HomeScreenAdapter extends BaseAdapter {

    Context context;
    int logos[];
    LayoutInflater inflter;
    List<String> homeList;
    public HomeScreenAdapter(Context applicationContext, int[] logos, List<String> homeList) {
        this.context = applicationContext;
        this.logos = logos;
        this.homeList = homeList;
        inflter = (LayoutInflater.from(applicationContext));
    }
    @Override
    public int getCount() {
        return logos.length;
    }
    @Override
    public Object getItem(int i) {
        return null;
    }
    @Override
    public long getItemId(int i) {
        return 0;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.row_home, null); // inflate the layout
        ImageView icon = (ImageView) view.findViewById(R.id.home_screen_icon); // get the reference of ImageView
        TextView textView = view.findViewById(R.id.text);
        icon.setImageResource(logos[i]); // set logo images
        textView.setText(homeList.get(i));
        return view;
    }

}