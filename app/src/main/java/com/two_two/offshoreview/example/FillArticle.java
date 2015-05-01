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
        list.add(new Article("first","first content"));
        list.add(new Article("second","second content"));
        list.add(new Article("third","third content"));
        list.add(new Article("fourth","fourth content"));
        return list;
    }


}
