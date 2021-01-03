package com.npu_app.npu_application.custom_views;

import android.content.Context;
import android.database.Cursor;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.npu_app.npu_application.adapters.EventListAdapter;
import com.npu_app.npu_application.adapters.EventRecyclerAdapter;
import com.npu_app.npu_application.adapters.CalendarAdapter;
import com.npu_app.npu_application.R;
import com.npu_app.npu_application.constants.DBConstants;
import com.npu_app.npu_application.controller.DatabaseAccess;
import com.npu_app.npu_application.model.Events;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import androidx.annotation.Nullable;

public class CalendarCustomView extends LinearLayout {
    ImageButton PreviouseButton,NextButton;
    TextView CurrentDate;
    GridView gridView;
    private static final int MAX_CALENDAR_Days = 42;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMMM yyyy", Locale.ENGLISH);
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    SimpleDateFormat monthFormat = new SimpleDateFormat("MMMM", Locale.ENGLISH);
    SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy", Locale.ENGLISH);
    Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
    Context context;
    List<Events> eventsList = new ArrayList<>();
    List<Date> dateList = new ArrayList<>();
    CalendarAdapter adapter;
    ListView listView;


    public CalendarCustomView(Context context) {
        super(context);
    }

    public CalendarCustomView(final Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        intializeUILayout();
        SetupCalendar();
        PreviouseButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.add(Calendar.MONTH,-1);
                SetupCalendar();
                listView.setAdapter(null);
            }
        });

        NextButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.add(Calendar.MONTH,1);
                SetupCalendar();
                listView.setAdapter(null);
            }
        });
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                //parent.notifyAll();
                view.setBackgroundColor(getResources().getColor(R.color.grey));
                List<Events> eventsList = new ArrayList<>();
                System.out.println(dateList.get(position));
                String date = dateFormat.format(dateList.get(position));
                eventsList = DatabaseAccess.getInstance(getContext()).ReadEvents(date);

                listView.setAdapter(new EventListAdapter(context, eventsList));
            }
        });
    }

    public CalendarCustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    private void intializeUILayout(){

        LayoutInflater inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.calendar_layout,this);
        PreviouseButton = view.findViewById(R.id.previousBtn);
        NextButton = view.findViewById(R.id.nextBtn);
        CurrentDate = view.findViewById(R.id.current_Date);
        gridView = view.findViewById(R.id.gridview);
        listView = view.findViewById(R.id.event_list);
    }

    private void SetupCalendar(){
        String StartDate = simpleDateFormat.format(calendar.getTime());
        CurrentDate.setText(StartDate);
        dateList.clear();
        Calendar monthCalendar = (Calendar)calendar.clone();
        monthCalendar.set(Calendar.DAY_OF_MONTH,1);
        int FirstDayOfMonth = monthCalendar.get(Calendar.DAY_OF_WEEK)-1;
        monthCalendar.add(Calendar.DAY_OF_MONTH,-FirstDayOfMonth);

        CollectEventsPerMonth(monthFormat.format(calendar.getTime()),yearFormat.format(calendar.getTime()));

        while (dateList.size() < MAX_CALENDAR_Days){
            dateList.add(monthCalendar.getTime());
            monthCalendar.add(Calendar.DAY_OF_MONTH,1);

        }
        adapter = new CalendarAdapter(context,dateList,calendar,eventsList);
        gridView.setAdapter(adapter);


    }

    private Date convertStringToDate(String dateInString){
        java.text.SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date date = null;
        try {
            date = format.parse(dateInString);

        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    private void CollectEventsPerMonth(String Month, String Year){
        eventsList.clear();
        eventsList = DatabaseAccess.getInstance(getContext()).ReadEventsperMonth(Month,Year);
        System.out.println("Event list " + eventsList);
    }
}