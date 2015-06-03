package com.two_two.offshoreview.data;

import android.os.Parcel;
import android.os.Parcelable;

public class Article implements Parcelable{


    private long articleId;
    private String title;
    private String thumbnailUrl;
    private String content;
    private String date;
    private String category;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(thumbnailUrl);
        parcel.writeString(content);
        parcel.writeString(date);
        parcel.writeString(category);
    }


    public static final Parcelable.Creator<Article> CREATOR
            = new Parcelable.Creator<Article>() {

        @Override
        public Article createFromParcel(Parcel parcel) {
            return new Article(parcel);
        }

        @Override
        public Article[] newArray(int i) {
            return new Article[i];
        }
    };

    @Override
    public String toString() {
        return "Article{" +
                "title='" + title + '\'' +
                ", thumbnailUrl='" + thumbnailUrl + '\'' +
                ", content='" + content + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

    public Article(){
    }
    public Article(Parcel input){
        this.title = input.readString();
        this.thumbnailUrl = input.readString();
        this.content = input.readString();
        this.date = input.readString();
        this.category = input.readString();
    }

    public long getArticleId() {
        return articleId;
    }

    public void setArticleId(long articleId) {
        this.articleId = articleId;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
