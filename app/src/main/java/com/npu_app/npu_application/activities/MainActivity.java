package com.npu_app.npu_application.activities;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.npu_app.npu_application.R;
import com.npu_app.npu_application.controller.DatabaseAccess;
import com.npu_app.npu_application.fragments.FragmentCalendar;
import com.npu_app.npu_application.fragments.FragmentClassSchedule;
import com.npu_app.npu_application.fragments.FragmentGrades;
import com.npu_app.npu_application.fragments.FragmentHomeScreen;
import com.npu_app.npu_application.fragments.FragmentMeetingLinks;
import com.npu_app.npu_application.fragments.FragmentSettings;
import com.npu_app.npu_application.fragments.FragmentStudentProfile;
import com.npu_app.npu_application.model.StudentProfile;
import com.npu_app.npu_application.receivers.MyNotificationPublisher;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private NavigationView nvDrawer;

    private ActionBarDrawerToggle drawerToggle;
    private View headerLayout;
    private ImageView ivHeaderPhoto;
    private TextView tvStudentNameCourse;
    public NavigationView navigationView;
    private FloatingActionButton fab;

    public static final String NOTIFICATION_CHANNEL_ID = "10001" ;
    private final static String default_notification_channel_id = "default" ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setUpDrawer();
        //Set student name and course in the drawer header
        StudentProfile studentProfile = DatabaseAccess.getInstance(getApplicationContext()).getStudentInfo();
        tvStudentNameCourse.setText(studentProfile.getFirst_name() + " " + studentProfile.getLast_name() + "\n" + studentProfile.getProgram());
        selectDrawerItem(navigationView.getMenu().getItem(0));

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ChatBotActivity.class);
                startActivity(intent);
            }
        });

        scheduleNotification(getNotification( "Due date of homework has been extended to 11/4 so you can prepare for midterm. Files must be submitted in a ZIP." ) , 5000 ) ;
    }

    private void setUpDrawer() {
        // Find our drawer view
        drawerToggle = setupDrawerToggle();

        // Setup toggle to display hamburger icon with nice animation
        drawerToggle.setDrawerIndicatorEnabled(true);
        drawerToggle.syncState();

        // Tie DrawerLayout events to the ActionBarToggle
        mDrawer.addDrawerListener(drawerToggle);
        setupDrawerContent(nvDrawer);
    }

    private void initViews() {
        toolbar = findViewById(R.id.toolbar);
        nvDrawer = (NavigationView) findViewById(R.id.nvView);
        mDrawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nvView);
        // Inflate the header view at runtime
        headerLayout = navigationView.inflateHeaderView(R.layout.nav_header);
        // We can now look up items within the header if needed
        ivHeaderPhoto = headerLayout.findViewById(R.id.profile_pic);
        tvStudentNameCourse = headerLayout.findViewById(R.id.student_name_course);
        fab = findViewById(R.id.fab);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // The action bar home/up action should open or close the drawer.
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawer.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    public void selectDrawerItem(MenuItem menuItem) {
        // Create a new fragment and specify the fragment to show based on nav item clicked
        Fragment fragment = null;
        Class fragmentClass;
        switch(menuItem.getItemId()) {
            case R.id.nav_home:
                fragmentClass = FragmentHomeScreen.class;
                break;
            case R.id.nav_class_schedule:
                fragmentClass = FragmentClassSchedule.class;
                break;
            case R.id.nav_grades:
                fragmentClass = FragmentGrades.class;
                break;
            case R.id.nav_meeting_links:
                fragmentClass = FragmentCalendar.class;
                break;
            case R.id.nav_settings:
                fragmentClass = FragmentSettings.class;
                break;
            case R.id.nav_student_profile:
                fragmentClass = FragmentStudentProfile.class;
                break;
            default:
                throw new IllegalStateException("Submit the current work before class " + menuItem.getItemId());
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();

        // Highlight the selected item has been done by NavigationView
        menuItem.setChecked(true);
        // Set action bar title
        setTitle(menuItem.getTitle());
        // Close the navigation drawer
        mDrawer.closeDrawers();
    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        // NOTE: Make sure you pass in a valid toolbar reference.  ActionBarDrawToggle() does not require it
        // and will not render the hamburger icon without it.
        return new ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.drawer_open,  R.string.drawer_close);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        drawerToggle.onConfigurationChanged(newConfig);
    }

    private void scheduleNotification (Notification notification , int delay) {
        Intent notificationIntent = new Intent( this, MyNotificationPublisher. class ) ;
        notificationIntent.putExtra(MyNotificationPublisher. NOTIFICATION_ID , 1 ) ;
        notificationIntent.putExtra(MyNotificationPublisher. NOTIFICATION , notification) ;
        PendingIntent pendingIntent = PendingIntent. getBroadcast ( this, 0 , notificationIntent , PendingIntent. FLAG_UPDATE_CURRENT ) ;
        long futureInMillis = SystemClock. elapsedRealtime () + delay ;
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context. ALARM_SERVICE ) ;
        assert alarmManager != null;
        alarmManager.set(AlarmManager. ELAPSED_REALTIME_WAKEUP , futureInMillis , pendingIntent) ;
    }
    private Notification getNotification (String content) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder( this, default_notification_channel_id ) ;
        builder.setContentTitle( "CS552 Week 6 Homework" ) ;
        builder.setContentText(content) ;
        builder.setSmallIcon(R.mipmap.ic_launcher_round ) ;
        builder.setAutoCancel( true ) ;
        builder.setChannelId( NOTIFICATION_CHANNEL_ID ) ;
        return builder.build() ;
    }
}