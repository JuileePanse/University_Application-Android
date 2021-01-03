package com.npu_app.npu_application.shared_preferences;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesManager {

    private static final String APP_SETTINGS = "NPU_SETTINGS";

    private static final String KEY_STUDENT_ID = "STUDENT_ID";

    private SharedPreferencesManager() {}

    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(APP_SETTINGS, Context.MODE_PRIVATE);
    }

    public static String getStudentID(Context context) {
        return getSharedPreferences(context).getString(KEY_STUDENT_ID , null);
    }

    public static void setStudentID(Context context, String stud_id) {
        final SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(KEY_STUDENT_ID , stud_id);
        editor.commit();
    }

    // other getters/setters
}