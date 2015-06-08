package com.two_two.offshoreview.task;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.android.volley.RequestQueue;
import com.two_two.offshoreview.callbacks.ArticleLoadListenerOffshore;
import com.two_two.offshoreview.callbacks.ArticleLoadListenerVenture;
import com.two_two.offshoreview.callbacks.ArticleUtils;
import com.two_two.offshoreview.data.Article;
import com.two_two.offshoreview.volley.VolleySingleton;

import java.util.ArrayList;

public class TaskLoadArticlesVenture extends AsyncTask<Void, Void, ArrayList<Article>> {
    private ArticleLoadListenerVenture myComponent;
    private VolleySingleton volleySingleton;
    private RequestQueue requestQueue;
    private Context context;
    private ProgressDialog progressDialog;

    public TaskLoadArticlesVenture(ArticleLoadListenerVenture myComponent, Context context){
        this.context = context;
        this.myComponent = myComponent;
        volleySingleton = VolleySingleton.getInstance();
        requestQueue = volleySingleton.getRequestQueue();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = ProgressDialog.show(context, "Подождите", "Идет загрузка...");
    }

    @Override
    protected ArrayList<Article> doInBackground(Void... voids) {
        ArrayList<Article> listArticles = ArticleUtils.loadListArticleVenture(requestQueue);
        return listArticles;
    }

    @Override
    protected void onPostExecute(ArrayList<Article> articles) {
        if(myComponent != null){
            myComponent.onArticleLoadListenerVenture(articles);
            progressDialog.hide();
        }
    }
}
