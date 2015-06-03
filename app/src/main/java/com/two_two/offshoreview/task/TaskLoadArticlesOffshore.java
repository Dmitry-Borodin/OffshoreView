package com.two_two.offshoreview.task;

import android.os.AsyncTask;

import com.android.volley.RequestQueue;
import com.two_two.offshoreview.callbacks.ArticleLoadListenerOffshore;
import com.two_two.offshoreview.callbacks.ArticleUtils;
import com.two_two.offshoreview.data.Article;
import com.two_two.offshoreview.volley.VolleySingleton;

import java.util.ArrayList;

/**
 * Created by marazm on 03.06.2015.
 */
public class TaskLoadArticlesOffshore extends AsyncTask<Void, Void, ArrayList<Article>> {
    private ArticleLoadListenerOffshore myComponent;
    private VolleySingleton volleySingleton;
    private RequestQueue requestQueue;

    public TaskLoadArticlesOffshore(ArticleLoadListenerOffshore myComponent){
        this.myComponent = myComponent;
        volleySingleton = VolleySingleton.getInstance();
        requestQueue = volleySingleton.getRequestQueue();
    }
    @Override
    protected ArrayList<Article> doInBackground(Void... voids) {
        ArrayList<Article> listArticles = ArticleUtils.loadListArticleOffshore(requestQueue);
        return listArticles;
    }

    @Override
    protected void onPostExecute(ArrayList<Article> articles) {
        if(myComponent != null){
            myComponent.onArticleLoadListenerOffshore(articles);
        }
    }
}
