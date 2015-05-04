package com.two_two.offshoreview.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stealps on 01.05.2015.
 */
public class FillArticle {
    public static List<Article> list;

    public static List<Article> getArticleList(){
        list = new ArrayList<Article>();
        list.add(new Article(1, "blog1", "first","first content"));
        list.add(new Article(2, "blog1", "second","second content"));
        list.add(new Article(3, "blog1", "third","third content"));
        list.add(new Article(4, "blog1", "fourth","fourth content"));
        return list;
    }


}
