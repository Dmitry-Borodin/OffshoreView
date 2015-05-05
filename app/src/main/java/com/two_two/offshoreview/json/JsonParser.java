package com.two_two.offshoreview.json;

import android.content.Context;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.two_two.offshoreview.adapter.CustomListAdapter;
import com.two_two.offshoreview.data.Articles;
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
                                  final List<Articles> articlesList,
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
                        Articles articles = new Articles();
                        JSONObject currentObject = arrayPostsFromJson.getJSONObject(i);
                        articles.setTitle(currentObject.getString("title"));
                        articles.setThumbnailUrl(currentObject.getString("thumbnail"));
                        articles.setDate(currentObject.getString("date"));
                        articles.setContent(currentObject.getString("content"));
                        articlesList.add(articles);
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



