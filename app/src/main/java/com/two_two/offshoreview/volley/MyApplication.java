package com.two_two.offshoreview.volley;

import android.app.Application;
import android.content.Context;

import com.two_two.offshoreview.data.DBArticles;


public class MyApplication extends Application {
    private static MyApplication sInstance;

    private static DBArticles mDatabase;

    @Override
    public void onCreate() {
        super.onCreate();
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
