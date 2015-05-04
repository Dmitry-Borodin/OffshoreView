package com.two_two.offshoreview.example;

import android.graphics.Bitmap;

import java.util.UUID;

/**
 * Created by Stealps on 01.05.2015.
 */
public class Article {
    private int id;
    private String blogName;
    private String title;
    private String content;

//    private Bitmap pic;


    public Article(int id, String blogName, String title, String content) {
        this.id = id;
        this.blogName = blogName;
        this.title = title;
        this.content = content;

    }

    public int getId() {
        return id;
    }

    public String getBlogName() {
        return blogName;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}




