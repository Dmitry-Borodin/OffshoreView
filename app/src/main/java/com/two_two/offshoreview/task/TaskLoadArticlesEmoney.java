package com.two_two.offshoreview.task;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.two_two.offshoreview.callbacks.ArticleLoadListenerEmoney;
import com.two_two.offshoreview.callbacks.ArticleUtils;
import com.two_two.offshoreview.data.Article;
import com.two_two.offshoreview.volley.VolleySingleton;

import java.util.ArrayList;

/**
 * Created by marazm on 03.06.2015.
 */
public class TaskLoadArticlesEmoney extends AsyncTask<Void, Void, ArrayList<Article>> {
    private ArticleLoadListenerEmoney myComponent;
    private VolleySingleton volleySingleton;
    private RequestQueue requestQueue;

    public TaskLoadArticlesEmoney(ArticleLoadListenerEmoney myComponent){
        this.myComponent = myComponent;
        volleySingleton = VolleySingleton.getInstance();
        requestQueue = volleySingleton.getRequestQueue();
    }
    @Override
    protected ArrayList<Article> doInBackground(Void... voids) {
        ArrayList<Article> listArticles = ArticleUtils.loadListArticleEmoney(requestQueue);
        return listArticles;
    }

    @Override
    protected void onPostExecute(ArrayList<Article> articles) {
        if(myComponent != null){
            myComponent.onArticleLoadListenerEmoney(articles);
        }
    }

}
