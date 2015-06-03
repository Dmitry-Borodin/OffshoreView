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
    private static final String TAG = "com.marazmone.DataBase";

    private static  final String DATABASE_NAME = "offshore_view_articles.db";
    private static final int DATABASE_VERSION = 1;                             //hardcoded version
    public static final String OFFSHORE_TABLE = "offshore_view";
    public static final String VENTURE_TABLE = "venture_view";
    public static final String EMONEY_TABLE = "emoney_view";
    public static final String ID = "_id";
    public static final String ARTICLE_ID = "article_id";
    public static final String ARTICLE_TITLE = "title";
    public static final String ARTICLE_CONTENT = "content";
    public static final String ARTICLE_CATEGORY = "category";
    public static final String ARTICLE_DATE = "date";
    public static final String ARTICLE_IMG = "img";

    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE "+ OFFSHORE_TABLE + " ("
            + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + ARTICLE_ID + " INTEGER,"
            + ARTICLE_TITLE + " TEXT,"
            + ARTICLE_CONTENT + " TEXT,"
            + ARTICLE_CATEGORY + " TEXT,"
            + ARTICLE_DATE + " TEXT,"
            + ARTICLE_IMG + " TEXT;" +
            "CREATE TABLE "+ VENTURE_TABLE + " ("
            + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + ARTICLE_ID + " INTEGER,"
            + ARTICLE_TITLE + " TEXT,"
            + ARTICLE_CONTENT + " TEXT,"
            + ARTICLE_CATEGORY + " TEXT,"
            + ARTICLE_DATE + " TEXT,"
            + ARTICLE_IMG + " TEXT;" +
            "CREATE TABLE "+ EMONEY_TABLE + " ("
            + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + ARTICLE_ID + " INTEGER,"
            + ARTICLE_TITLE + " TEXT,"
            + ARTICLE_CONTENT + " TEXT,"
            + ARTICLE_CATEGORY + " TEXT,"
            + ARTICLE_DATE + " TEXT,"
            + ARTICLE_IMG + " TEXT;";

    private static final String SQL_DELETE_OFFSHORE = "DROP TABLE IF EXISTS " + OFFSHORE_TABLE;
    private static final String SQL_DELETE_VENTURE = "DROP TABLE IF EXISTS " + VENTURE_TABLE;
    private static final String SQL_DELETE_EMONEY = "DROP TABLE IF EXISTS " + EMONEY_TABLE;

    public LocalDataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(TAG,"DB updating from version "+ oldVersion + " to version " + newVersion + " and deleting all old data");
        db.execSQL(SQL_DELETE_OFFSHORE);
        db.execSQL(SQL_DELETE_VENTURE);
        db.execSQL(SQL_DELETE_EMONEY);
        onCreate(db);
    }
}
