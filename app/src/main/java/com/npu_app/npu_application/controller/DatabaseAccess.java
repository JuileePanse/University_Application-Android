package com.npu_app.npu_application.controller;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.npu_app.npu_application.constants.DBConstants;
import com.npu_app.npu_application.database.DatabaseOpenHelper;
import com.npu_app.npu_application.model.ClassSchedule;
import com.npu_app.npu_application.model.Events;
import com.npu_app.npu_application.model.StudentProfile;
import com.npu_app.npu_application.shared_preferences.SharedPreferencesManager;

import org.chalup.microorm.MicroOrm;

import java.util.List;
import java.util.Vector;


public class DatabaseAccess {
    private DatabaseOpenHelper databaseOpenHelper;
    private SQLiteDatabase sqLiteDatabase;
    private static DatabaseAccess databaseAccess;
    Context context;

    public DatabaseAccess(Context context) {
        this.context = context;
        this.databaseOpenHelper = new DatabaseOpenHelper(context);
    }

    public static DatabaseAccess getInstance(Context context) {
        if (databaseAccess == null) {
            databaseAccess = new DatabaseAccess(context);
        }
        return databaseAccess;
    }

    public void Open() {
        this.sqLiteDatabase = databaseOpenHelper.getWritableDatabase();
    }

    public void Close() {
        if (sqLiteDatabase != null) {
            this.sqLiteDatabase.close();
        }
    }

    public String checkCredentials(String userName, String password) {
        String student_id = "";
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT student_id FROM users WHERE username = '" + userName + "' AND password ='" + password + "'", null);
        cursor.moveToFirst();
        if (cursor != null) {
            // move cursor to first row
            if (cursor.moveToFirst()) {
                do {
                    // Get version from Cursor
                    student_id = cursor.getString(cursor.getColumnIndex("student_id"));
                    // move to next row
                } while (cursor.moveToNext());
            }
        }
        return student_id;
    }

    public StudentProfile getStudentInfo() {
        String studentID = SharedPreferencesManager.getStudentID(context);
        MicroOrm uOrm = new MicroOrm();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM student WHERE student_id = '" + studentID + "'", null);

        return uOrm.listFromCursor(cursor, StudentProfile.class).get(0);
    }

    public List<Events> ReadEventsperMonth(String month, String year){
        MicroOrm uOrm = new MicroOrm();
        System.out.println("SELECT * FROM " + DBConstants.EVENT_TABLE_NAME + " WHERE "+ DBConstants.MONTH + " = '" + month + "' AND " + DBConstants.YEAR + " = '" + year +"'");
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + DBConstants.EVENT_TABLE_NAME + " WHERE "+ DBConstants.MONTH + " = '" + month + "' AND " + DBConstants.YEAR + " = '" + year +"'", null);
        return uOrm.listFromCursor(cursor, Events.class);
    }

    public List<Events> ReadEvents(String date){

        MicroOrm uOrm = new MicroOrm();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + DBConstants.EVENT_TABLE_NAME + " WHERE "+ DBConstants.DATE + " = '" + date + "'", null);
        return uOrm.listFromCursor(cursor, Events.class);
    }

    public List<ClassSchedule> getCourseInfo(){
        String course_name, instructor_name, course_credit, time, meeting_link, activity, handout;

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT course_name, instructor_name,course_credit,time,meeting_link,activity,handout FROM studCour left join course WHERE studCour.course_id = course.course_id", null);

        cursor.moveToFirst();
        Vector<ClassSchedule> v = new Vector<ClassSchedule>();

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {

                    course_name = cursor.getString(cursor.getColumnIndex("course_name"));
                    instructor_name = cursor.getString(cursor.getColumnIndex("instructor_name"));
                    course_credit = cursor.getString(cursor.getColumnIndex("course_credit"));
                    time = cursor.getString(cursor.getColumnIndex("time"));
                    meeting_link = cursor.getString(cursor.getColumnIndex("meeting_link"));
                    activity = cursor.getString(cursor.getColumnIndex("activity"));
                    handout = cursor.getString(cursor.getColumnIndex("handout"));

                    ClassSchedule classSchedule = new ClassSchedule(course_name,instructor_name,course_credit,time,meeting_link,activity,handout);
                    v.add(classSchedule);
                } while (cursor.moveToNext());
            }
        }
        return v;
    }
}
