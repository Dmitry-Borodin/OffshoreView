package com.two_two.offshoreview.json;

import android.content.Context;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.two_two.offshoreview.adapter.CustomListAdapter;
import com.two_two.offshoreview.data.Article;
import com.two_two.offshoreview.volley.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by marazm on 05.05.15.
 */
public class JsonParser {
    public static void jsonParser(Context context, String url, final String TAG,
                                  final List<Article> articleList,
                                  final CustomListAdapter customListAdapter) {

        //Progress loading...
        ProgressDialogForJson.pDialogProgress(context);
        //Volley parsing
        JsonObjectRequest articleReq = new JsonObjectRequest(url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, response.toString());
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
                        article.setContent(currentObject.getString("content"));
                        article.setBlogType(Article.blogName.offshore_blog);        //TODO this should depend of where are we getting this data;
                        articleList.add(article);
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
}



