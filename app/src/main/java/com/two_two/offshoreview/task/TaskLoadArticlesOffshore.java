package com.two_two.offshoreview.task;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.android.volley.RequestQueue;
import com.two_two.offshoreview.R;
import com.two_two.offshoreview.callbacks.ArticleLoadListenerOffshore;
import com.two_two.offshoreview.callbacks.ArticleUtils;
import com.two_two.offshoreview.data.Article;
import com.two_two.offshoreview.volley.VolleySingleton;

import java.util.ArrayList;

public class TaskLoadArticlesOffshore extends AsyncTask<Void, Void, ArrayList<Article>> {
    private ArticleLoadListenerOffshore myComponent;
    private VolleySingleton volleySingleton;
    private RequestQueue requestQueue;
    private Context context;
    private ProgressDialog progressDialog;
    private boolean loadDialog;

    public TaskLoadArticlesOffshore(ArticleLoadListenerOffshore myComponent, Context context,
                                    boolean loadDialog){
        this.loadDialog = loadDialog;
        this.context = context;
        this.myComponent = myComponent;
        volleySingleton = VolleySingleton.getInstance();
        requestQueue = volleySingleton.getRequestQueue();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (loadDialog){
            progressDialog = ProgressDialog.show(context, context.getString(R.string.progress_bar_title),
                    context.getString(R.string.progress_bar_content));
        }
    }

    @Override
    protected ArrayList<Article> doInBackground(Void... params) {
        ArrayList<Article> listArticles = ArticleUtils.loadListArticleOffshore(requestQueue);
        return listArticles;
    }

    @Override
    protected void onPostExecute(ArrayList<Article> articles) {
        if(myComponent != null){
            myComponent.onArticleLoadListenerOffshore(articles);
            if(loadDialog){
                progressDialog.hide();
            }
        }
    }

}
