package com.two_two.offshoreview.data;

import com.two_two.offshoreview.example.Article;

/**
 * Created by marazm on 04.05.15.
 */
public class Articles {
    private String title, thumbnailUrl, content;
    private String date;

    public Articles(){

    }

    public Articles(String title, String thumbnailUrl, String content, String date) {
        this.title = title;
        this.thumbnailUrl = thumbnailUrl;
        this.content = content;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public String getContent() {
        return content;
    }

    public String getDate() {
        return date;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
