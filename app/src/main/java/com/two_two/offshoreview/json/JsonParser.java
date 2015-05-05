package com.two_two.offshoreview.json;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.two_two.offshoreview.adapter.CustomListAdapter;
import com.two_two.offshoreview.data.Article;
import com.two_two.offshoreview.data.LocalDataBaseHelper;
import com.two_two.offshoreview.volley.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by marazm on 05.05.15.
 */
public class JsonParser {
    private static final String TAG = JsonParser.class.getSimpleName();
    public static void jsonParser(final Context context, String url, final String TAG,
                                  final List<Article> articleList,
                                  final CustomListAdapter customListAdapter) {

        //Progress loading...
        ProgressDialogForJson.pDialogProgress(context);
        //Volley parsing
        JsonObjectRequest articleReq = new JsonObjectRequest(url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                cleanDataBase(context);                //TODO add check and delete this!!!
                Log.d(TAG, response.toString());  //do we need this???
                ProgressDialogForJson.hidePDialog();
                //parsing JSON
                try {
                    JSONArray arrayPostsFromJson = response.getJSONArray("posts");
                    for (int i = 0; i < arrayPostsFromJson.length(); i++) {
                        Article article = new Article();
                        JSONObject currentObject = arrayPostsFromJson.getJSONObject(i);
                        article.setTitle(currentObject.getString("title"));
                        article.setThumbnailUrl(currentObject.getString("thumbnail"));
                        article.setDate(currentObject.getString("date"));
                        article.setId(currentObject.getInt("id"));
                        article.setContent(currentObject.getString("content"));
                        article.setBlogType(Article.blogName.OFFSHORE_BLOG);        //TODO this should depend of where are we getting this data;

                        pushArticleToDB(article,context);   //in new Runnable in future

                        articleList.add(article); //TODO for now we store list and db at the same time, it should be changed
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                customListAdapter.notifyDataSetChanged(); //update adapter

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e(TAG, error.getMessage());
            }
        });
        AppController.getInstance().addToRequestQueue(articleReq);
    }

    /*
    *Custom method by Dmotry Borodin. It should be moved avay from this class in future
    * we can uptimise db work if we will open db one time in class, but in correct place for this method
     */
    public static void pushArticleToDB(Article article,Context context){

        // Инициализируем наш класс-обёртку и базу
        LocalDataBaseHelper sqlHelper = new LocalDataBaseHelper(context);
        SQLiteDatabase sdb = sqlHelper.getWritableDatabase();
        //sqlHelper.onUpgrade(sdb,1,2);   //TODO we are cleaning database now!!!
        //заполняем базу
        ContentValues cv = new ContentValues();
        cv.put(LocalDataBaseHelper.ID, article.getId());  //TODO add fields to DB filling
        cv.put(LocalDataBaseHelper.ARTICLETITLE, article.getTitle());
        cv.put(LocalDataBaseHelper.ARTICLECONTENT, article.getContent());
        switch (article.getBlogType()){
            case OFFSHORE_BLOG:
                sdb.insert(LocalDataBaseHelper.OFFSHOREBLOG_TABLENAME, LocalDataBaseHelper.ARTICLETITLE, cv);
                break;
            case VENTURE_BLOG:
                sdb.insert(LocalDataBaseHelper.VENTUREBLOG_TABLENAME, LocalDataBaseHelper.ARTICLETITLE, cv);
                break;
            case EMONEY_BLOG:
                sdb.insert(LocalDataBaseHelper.EMONEYBLOG_TABLENAME, LocalDataBaseHelper.ARTICLETITLE, cv);
                break;
            default:
                Log.d(TAG,"Error in pushToDB Method - cannot find blogType enum variable to bind to db table");
                new Throwable().printStackTrace();
                break;
        }

            // закрываем соединения с базой данных
        sdb.close();
        sqlHelper.close();
    }

    private static void cleanDataBase(Context context){
        LocalDataBaseHelper sqlHelper = new LocalDataBaseHelper(context);
        SQLiteDatabase sdb = sqlHelper.getWritableDatabase();
        sqlHelper.onUpgrade(sdb,1,2);   //TODO we are cleaning database now!!!
        sdb.close();
        sqlHelper.close();
        }
}



