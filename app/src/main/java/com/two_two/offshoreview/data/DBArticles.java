package com.two_two.offshoreview.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import java.util.ArrayList;


public class DBArticles {
    private ArticleHelper mHelper;
    private SQLiteDatabase mDatabase;

    public DBArticles(Context context){
        mHelper = new ArticleHelper(context);
        mDatabase = mHelper.getWritableDatabase();
    }



    public void insertArticlesInDataBase(ArrayList<Article> listArticles, boolean clearDataBase, String blogName){
        if(clearDataBase){
            if(blogName.equals("offshore")){
                deleteTable(blogName);
            } else if (blogName.equals("venture")){
                deleteTable(blogName);
            } else {
                deleteTable(blogName);
            }
        }
        String dataBase;
        if(blogName.equals("offshore")){
            dataBase = ArticleHelper.OFFSHORE_TABLE;
        } else if (blogName.equals("venture")){
            dataBase = ArticleHelper.VENTURE_TABLE;
        } else {
            dataBase = ArticleHelper.EMONEY_TABLE;
        }
            String sql = "INSERT INTO " + dataBase + " VALUES (?,?,?,?,?,?,?,?);";
            SQLiteStatement statement = mDatabase.compileStatement(sql);
            mDatabase.beginTransaction();
            for (int i = 0; i < listArticles.size(); i++) {
                Article currentArticle = listArticles.get(i);
                statement.clearBindings();
                statement.bindLong(2, currentArticle.getArticleId());
                statement.bindString(3, currentArticle.getTitle());
                statement.bindString(4, currentArticle.getContent());
                statement.bindString(5, currentArticle.getCategory());
                statement.bindString(6, currentArticle.getDate());
                statement.bindString(7, currentArticle.getThumbnailUrl());
                statement.bindString(8, currentArticle.getUrlArticle());
                statement.execute();
        }
        mDatabase.setTransactionSuccessful();
        mDatabase.endTransaction();
    }


    public ArrayList<Article> getArticleWithDataBase(String blogName) {
        ArrayList<Article> listArticles = new ArrayList<>();
        String dataBase;
        if(blogName.equals("offshore")){
            dataBase = ArticleHelper.OFFSHORE_TABLE;
        } else if (blogName.equals("venture")) {
            dataBase = ArticleHelper.VENTURE_TABLE;
        } else {
            dataBase = ArticleHelper.EMONEY_TABLE;
        }
        String[] columns = {ArticleHelper.ID,
                 ArticleHelper.ARTICLE_ID,
                 ArticleHelper.ARTICLE_TITLE,
                 ArticleHelper.ARTICLE_CONTENT,
                 ArticleHelper.ARTICLE_CATEGORY,
                 ArticleHelper.ARTICLE_DATE,
                 ArticleHelper.ARTICLE_IMG,
                 ArticleHelper.ARTICLE_URL
        };

        Cursor cursor = mDatabase.query(dataBase, columns, null, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                Article article = new Article();

                article.setArticleId(cursor.getLong(cursor.getColumnIndex(ArticleHelper.ARTICLE_ID)));
                article.setTitle(cursor.getString(cursor.getColumnIndex(ArticleHelper.ARTICLE_TITLE)));
                article.setContent(cursor.getString(cursor.getColumnIndex(ArticleHelper.ARTICLE_CONTENT)));
                article.setCategory(cursor.getString(cursor.getColumnIndex(ArticleHelper.ARTICLE_CATEGORY)));
                article.setDate(cursor.getString(cursor.getColumnIndex(ArticleHelper.ARTICLE_DATE)));
                article.setThumbnailUrl(cursor.getString(cursor.getColumnIndex(ArticleHelper.ARTICLE_IMG)));
                article.setUrlArticle(cursor.getString(cursor.getColumnIndex(ArticleHelper.ARTICLE_URL)));

                listArticles.add(article);
            }
            while (cursor.moveToNext());
        }
        return listArticles;
    }

    public void deleteTable(String table){
        if(table.equals("offshore")){
            mDatabase.delete(ArticleHelper.OFFSHORE_TABLE, null, null);
        } else if(table.equals("venture")){
            mDatabase.delete(ArticleHelper.VENTURE_TABLE, null, null);
        } else {
            mDatabase.delete(ArticleHelper.EMONEY_TABLE, null, null);
        }
    }

    private static class ArticleHelper extends SQLiteOpenHelper {
        private static final String TAG = "com.marazmone.DataBase";

        private static  final String DATABASE_NAME = "offshore_view_articles.db";
        private static final int DATABASE_VERSION = 2;                             //hardcoded version
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
        public static final String ARTICLE_URL = "url";

        private static final String SQL_CREATE_OFFSHORE = "CREATE TABLE "+ OFFSHORE_TABLE + " ("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + ARTICLE_ID + " LONG,"
                + ARTICLE_TITLE + " TEXT,"
                + ARTICLE_CONTENT + " TEXT,"
                + ARTICLE_CATEGORY + " TEXT,"
                + ARTICLE_DATE + " TEXT,"
                + ARTICLE_IMG + " TEXT,"
                + ARTICLE_URL + " TEXT);";
        private static final String SQL_CREATE_VENTURE = "CREATE TABLE "+ VENTURE_TABLE + " ("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + ARTICLE_ID + " LONG,"
                + ARTICLE_TITLE + " TEXT,"
                + ARTICLE_CONTENT + " TEXT,"
                + ARTICLE_CATEGORY + " TEXT,"
                + ARTICLE_DATE + " TEXT,"
                + ARTICLE_IMG + " TEXT,"
                + ARTICLE_URL + " TEXT);";
        private static final String SQL_CREATE_EMONEY = "CREATE TABLE "+ EMONEY_TABLE + " ("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + ARTICLE_ID + " LONG,"
                + ARTICLE_TITLE + " TEXT,"
                + ARTICLE_CONTENT + " TEXT,"
                + ARTICLE_CATEGORY + " TEXT,"
                + ARTICLE_DATE + " TEXT,"
                + ARTICLE_IMG + " TEXT,"
                + ARTICLE_URL + " TEXT);";

        private static final String SQL_DELETE_OFFSHORE = "DROP TABLE IF EXISTS " + OFFSHORE_TABLE;
        private static final String SQL_DELETE_VENTURE = "DROP TABLE IF EXISTS " + VENTURE_TABLE;
        private static final String SQL_DELETE_EMONEY = "DROP TABLE IF EXISTS " + EMONEY_TABLE;

        /* ---- START update dataBase ver.2 ---- */
        private static final String SQL_ALTER_COLUMN_OFFSHORE = "ALTER TABLE " + OFFSHORE_TABLE
                + " ADD COLUMN " + ARTICLE_URL
                + " TEXT;";

        private static final String SQL_ALTER_COLUMN_VENTURE = "ALTER TABLE " + VENTURE_TABLE
                + " ADD COLUMN " + ARTICLE_URL
                + " TEXT;";

        private static final String SQL_ALTER_COLUMN_EMONEY = "ALTER TABLE " + EMONEY_TABLE
                + " ADD COLUMN " + ARTICLE_URL
                + " TEXT;";


        public ArticleHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_OFFSHORE);
            db.execSQL(SQL_CREATE_VENTURE);
            db.execSQL(SQL_CREATE_EMONEY);
        }



        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "DB updating from version " + oldVersion + " to version " + newVersion + " and deleting all old data");
            if (newVersion > oldVersion){
                db.execSQL(SQL_ALTER_COLUMN_OFFSHORE);
                db.execSQL(SQL_ALTER_COLUMN_VENTURE);
                db.execSQL(SQL_ALTER_COLUMN_EMONEY);
            }
            db.execSQL(SQL_DELETE_OFFSHORE);
            db.execSQL(SQL_DELETE_VENTURE);
            db.execSQL(SQL_DELETE_EMONEY);
        }
    }
}


