package com.two_two.offshoreview.example;

import android.graphics.Bitmap;

/**
 * Created by Stealps on 01.05.2015.
 */
public class Article {
    private String title;
    private String content;
//    private Bitmap pic;


    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }


}




