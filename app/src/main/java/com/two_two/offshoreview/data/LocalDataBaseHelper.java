package com.two_two.offshoreview.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by DmitryBorodin on 04.05.2015.
 * This class will open db to store articles
 * as for 04.05 используем поля id, title, content (2000символов), date created, categories (string array) TODO не забыть проверку на символы сделать
 * еще создать таблицу с категориями и тегами (по 2 поля)
 */
public class LocalDataBaseHelper extends SQLiteOpenHelper {
    private static final String TAG = LocalDataBaseHelper.class.getSimpleName();

    private static  final String DATABASE_NAME = "offshore_view_articles.db";
    private static final int DATABASE_VERSION = 1;                             //hardcoded version
    public static final String OFFSHOREBLOG_TABLENAME = "offshore_view";
    public static final String VENTUREBLOG_TABLENAME = "venture_view";
    public static final String EMONEYBLOG_TABLENAME = "emoney_view";
    public static final String CATIGORIES_TABLENAME = "categories";
    public static final String CATIGORIES_NAME = "catigorie";
    public static final String TAGS_TABLENAME = "tags";
    public static final String TAGS_NAME = "tag";
    public static final String ID = "id";
    public static final String ARTICLETITLE = "title";
    public static final String ARTICLECONTENT = "content";
    public static final String ARTICLEPICTURELINK = "picture";

    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE "+ OFFSHOREBLOG_TABLENAME + " (" + ID + " INTEGER PRIMARY KEY," + ARTICLETITLE + " VARCHAR(255)," + ARTICLECONTENT + " VARCHAR(2000)," + ARTICLEPICTURELINK + " VARCHAR(255));" +
            "CREATE TABLE "+ VENTUREBLOG_TABLENAME + " (" + ID + " INTEGER PRIMARY KEY," + ARTICLETITLE + " VARCHAR(255)," + ARTICLECONTENT + " VARCHAR(2000)" + ARTICLEPICTURELINK + " VARCHAR(255));" +
            "CREATE TABLE "+ EMONEYBLOG_TABLENAME + " (" + ID + " INTEGER PRIMARY KEY," + ARTICLETITLE + " VARCHAR(255)," + ARTICLECONTENT + " VARCHAR(2000)" + ARTICLEPICTURELINK + " VARCHAR(255));" +
            "CREATE TABLE "+ TAGS_TABLENAME + " (" + ID + " INTEGER PRIMARY KEY," + TAGS_NAME + " VARCHAR(255));"+
            "CREATE TABLE "+ CATIGORIES_TABLENAME + " (" + ID + " INTEGER PRIMARY KEY," + CATIGORIES_NAME + " VARCHAR(255));";

    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + OFFSHOREBLOG_TABLENAME + ";DROP TABLE IF EXISTS " + VENTUREBLOG_TABLENAME + ";DROP TABLE IF EXISTS " + EMONEYBLOG_TABLENAME +";DROP TABLE IF EXISTS "
            + CATIGORIES_TABLENAME + ";DROP TABLE IF EXISTS " +TAGS_TABLENAME;


    public LocalDataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(TAG,"DB updating from version "+ oldVersion + " to version " + newVersion + " and deleting all old data"); //TODO make TAG
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}
