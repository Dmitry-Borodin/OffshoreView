package com.two_two.offshoreview.example;

import android.graphics.Bitmap;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Stealps on 01.05.2015.
 */
public class Article {

    private int pid;
    private String title;
    private String dateTime;
//    private Bitmap pic;
    private String content;

    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Article (JSONObject article){
        try {
            pid = article.getInt("pid");
            title = article.getString("title");
            dateTime = article.getString("date_public");
        }
        catch (JSONException e){
            e.printStackTrace();
        }
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public int getPid() {
        return pid;
    }

    public String getDateTime() {
        return dateTime;
    }
}




