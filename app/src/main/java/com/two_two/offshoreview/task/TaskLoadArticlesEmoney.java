package com.two_two.offshoreview.task;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.android.volley.RequestQueue;
import com.two_two.offshoreview.R;
import com.two_two.offshoreview.callbacks.ArticleLoadListenerEmoney;
import com.two_two.offshoreview.callbacks.ArticleUtils;
import com.two_two.offshoreview.data.Article;
import com.two_two.offshoreview.volley.VolleySingleton;

import java.util.ArrayList;

public class TaskLoadArticlesEmoney extends AsyncTask<Void, Void, ArrayList<Article>> {
    private ArticleLoadListenerEmoney myComponent;
    private VolleySingleton volleySingleton;
    private RequestQueue requestQueue;
    private Context context;
    private ProgressDialog progressDialog;

    public TaskLoadArticlesEmoney(ArticleLoadListenerEmoney myComponent, Context context){
        this.context = context;
        this.myComponent = myComponent;
        volleySingleton = VolleySingleton.getInstance();
        requestQueue = volleySingleton.getRequestQueue();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = ProgressDialog.show(context, context.getString(R.string.progress_bar_title),
                context.getString(R.string.progress_bar_content));
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
            progressDialog.hide();
        }

    }

}
