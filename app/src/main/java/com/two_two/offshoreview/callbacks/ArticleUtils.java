package com.two_two.offshoreview.callbacks;

import com.android.volley.RequestQueue;
import com.two_two.offshoreview.data.Article;
import com.two_two.offshoreview.json.Parser;
import com.two_two.offshoreview.json.Requestor;
import com.two_two.offshoreview.volley.MyApplication;

import org.json.JSONObject;

import java.util.ArrayList;

public class ArticleUtils {
    public static ArrayList<Article> loadListArticleOffshore(RequestQueue requestQueue){
        JSONObject response = Requestor.sendJsonRequest(requestQueue, "http://offshoreview.eu/api/get_recent_posts/?json=1&json_unescaped_unicode=1&count=12");
        ArrayList<Article> listArticles = Parser.parseJSONResponse(response);
        MyApplication.getWritableDatabase().insertArticlesInDataBase(listArticles, true, "offshore");
        return listArticles;
    }
    public static ArrayList<Article> loadListArticleVenture(RequestQueue requestQueue){
        JSONObject response = Requestor.sendJsonRequest(requestQueue, "http://ventureview.eu/api/get_recent_posts/?json=1&json_unescaped_unicode=1&count=12");
        ArrayList<Article> listArticles = Parser.parseJSONResponse(response);
        MyApplication.getWritableDatabase().insertArticlesInDataBase(listArticles, true, "venture");
        return listArticles;
    }
    public static ArrayList<Article> loadListArticleEmoney(RequestQueue requestQueue){
        JSONObject response = Requestor.sendJsonRequest(requestQueue, "http://emoneyview.eu/api/get_recent_posts/?json=1&json_unescaped_unicode=1&count=12");
        ArrayList<Article> listArticles = Parser.parseJSONResponse(response);
        MyApplication.getWritableDatabase().insertArticlesInDataBase(listArticles, true, "emoney");
        return listArticles;
    }
    public static ArrayList<Article> loadListArticleCatalog(RequestQueue requestQueue){
        JSONObject response = Requestor.sendJsonRequest(requestQueue, "http://catalog.offshoreview.eu/api/get_recent_posts/?json=1&json_unescaped_unicode=1&count=12");
        ArrayList<Article> listArticles = Parser.parseJSONResponse(response);
        MyApplication.getWritableDatabase().insertArticlesInDataBase(listArticles, true, "catalog");
        return listArticles;
    }
}
