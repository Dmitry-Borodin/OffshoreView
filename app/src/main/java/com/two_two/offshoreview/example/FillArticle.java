package com.two_two.offshoreview.example;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.two_two.offshoreview.fillingClasses.localDataBaseHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stealps on 01.05.2015.
 * templorary class to fill articles while we don't have internet filling
 */
public class FillArticle {
    public static List<Article> list;

    public static List<Article> getArticleList(Context context){
        list = new ArrayList<Article>();
        list.add(new Article(1, "blog1", "first","first content"));
        list.add(new Article(2, "blog1", "second","second content"));
        list.add(new Article(3, "blog1", "third","third content"));
        list.add(new Article(4, "blog1", "fourth","fourth content"));
        //TODO for now we store list and db at the same time


        // Инициализируем наш класс-обёртку
        localDataBaseHelper sqlHelper = new localDataBaseHelper(context);

        // База нам нужна для записи и чтения
        SQLiteDatabase sdb = sqlHelper.getWritableDatabase();
        sqlHelper.onUpgrade(sdb,1,2);
        //заполняем базу
        ContentValues cv = new ContentValues();
        for (Article x:list) {

            cv.put(localDataBaseHelper.ID, x.getId());
            cv.put(localDataBaseHelper.ARTICLETITLE,x.getTitle());
            cv.put(localDataBaseHelper.ARTICLECONTENT,x.getContent());
            sdb.insert(localDataBaseHelper.OFFSHOREBLOG_TABLENAME,localDataBaseHelper.ARTICLETITLE,cv);
        }
        // закрываем соединения с базой данных
        sdb.close();
        sqlHelper.close();


        return list;
    }


}
