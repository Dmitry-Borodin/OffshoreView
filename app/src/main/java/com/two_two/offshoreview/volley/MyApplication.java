package com.two_two.offshoreview.volley;

import android.app.Application;
import android.content.Context;

import com.parse.Parse;
import com.parse.ParseInstallation;
import com.two_two.offshoreview.data.DBArticles;


public class MyApplication extends Application {
    private static MyApplication sInstance;

    private static DBArticles mDatabase;

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(this, "a3Sdnq0iNHBK4QMwVtRIjv4rer0t7xUwE5qqdZcJ", "YLbciBKwvp4jLq9ArHTnX4z97NfylCbkK2Nv1RYg");
        ParseInstallation.getCurrentInstallation().saveInBackground();

        sInstance = this;
        mDatabase = new DBArticles(this);
    }
    public static MyApplication getInstance(){
        return sInstance;
    }
    public static Context getAppContext(){
        return sInstance.getApplicationContext();
    }
    public synchronized static DBArticles getWritableDatabase() {
        if (mDatabase == null) {
            mDatabase = new DBArticles(getAppContext());
        }
        return mDatabase;
    }
}
