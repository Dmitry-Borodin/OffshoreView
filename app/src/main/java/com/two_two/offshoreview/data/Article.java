package com.two_two.offshoreview.data;

/**
 * This is article class
 */
public class Article {
    private String title, thumbnailUrl, content;
    private String date;
    public enum blogName{OFFSHORE_BLOG, VENTURE_BLOG, EMONEY_BLOG};
    private blogName blogType;
    int id;

    public Article(){
    }

    public Article(String title, String thumbnailUrl, String content, String date, blogName blog) {
        this.title = title;
        this.thumbnailUrl = thumbnailUrl;
        this.content = content;
        this.date = date;
        this.blogType=blog;
    }

    public blogName getBlogType() {
        return blogType;
    }

    public int getId() {
        return id;
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

    public void setId(int id) {
        this.id = id;
    }

    public void setBlogType(blogName blogType) {
        this.blogType = blogType;
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
