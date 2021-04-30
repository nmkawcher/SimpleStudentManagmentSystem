package com.example.studentmanagement.Util;

import android.content.Context;
import android.content.SharedPreferences;

public class ShaaredPrefereanceManager {
    private static ShaaredPrefereanceManager mInstance;
    private static Context mCtx;
    public static String USER_LOGIN_STATUS= "login_status";
    public static String USER_LOGIN_STATUS_KEY= "login_status_key";

    public static String USER_LOGGED_EMAIL= "logged_email";
    public static String USER_LOGGED_EMAIL_KEY= "logged_email_key";

    public ShaaredPrefereanceManager(Context context) {
        mCtx = context;
    }

    public static synchronized ShaaredPrefereanceManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new ShaaredPrefereanceManager(context);
        }

        return mInstance;
    }



    public void setLoginStatus(String s) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(USER_LOGIN_STATUS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USER_LOGIN_STATUS_KEY, s);
        editor.apply();
    }

    public String getUserLoginStatus(){
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(USER_LOGIN_STATUS,Context.MODE_PRIVATE);
        String value=sharedPreferences.getString(USER_LOGIN_STATUS_KEY,"false");
        return value;
    }

    public void setLoggedEmail(String s) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(USER_LOGGED_EMAIL, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USER_LOGGED_EMAIL_KEY, s);
        editor.apply();
    }

    public String getLoggedEmail(){
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(USER_LOGGED_EMAIL,Context.MODE_PRIVATE);
        String value=sharedPreferences.getString(USER_LOGGED_EMAIL_KEY,"false");
        return value;
    }
}
