package com.two_two.offshoreview.fragment;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.two_two.offshoreview.R;
import com.two_two.offshoreview.adapter.CustomListAdapter;
import com.two_two.offshoreview.data.Article;
import com.two_two.offshoreview.volley.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by marazm on 12.05.2015.
 */
public class FragmentOffshoreView extends Fragment {

    private static final String url = "http://offshoreview.eu/api/get_recent_posts/?json=1&json_unescaped_unicode=1&count=7";

    private VolleySingleton volleySingleton;
    private RequestQueue requestQueue;
    private ArrayList<Article> listArticle = new ArrayList<>();
    private CustomListAdapter adapter;
    private ListView listViewArticleFragment;

    private ProgressDialog pDialog;

    public void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }

    }

    public FragmentOffshoreView(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        volleySingleton = VolleySingleton.getInstance();
        requestQueue = volleySingleton.getRequestQueue();
        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Загрузка...");
        pDialog.show();
        sendJsonRequest();
    }

    private void sendJsonRequest() {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                listArticle = parseJSONResponse(response);
                adapter.setArticleList(listArticle);
                hidePDialog();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(request);
    }
    //TODO add create list with category
    private ArrayList<Article> parseJSONResponse(JSONObject response) {

        listArticle = new ArrayList<>();

        if(response == null || response.length() > 0){

        }

        try {
            JSONArray arrayArticle = response.getJSONArray("posts");
            for (int i = 0; i < arrayArticle.length(); i++) {
                JSONObject currentArticle = arrayArticle.getJSONObject(i);
                int id = currentArticle.getInt("id");
                String title = currentArticle.getString("title");
                String thumbnail = currentArticle.getString("thumbnail");
                String date = currentArticle.getString("date");
                String content = currentArticle.getString("content");
                JSONArray categoryArray = currentArticle.getJSONArray("categories");
                Article article = new Article();
                for (int j = 0; j < categoryArray.length(); j++) {
                    JSONObject currentCategory = categoryArray.getJSONObject(j);
                    String category = currentCategory.getString("title");
                    article.setCategory(category);
                }




                article.setId(id);
                article.setTitle(title);
                article.setThumbnailUrl(thumbnail);
                article.setDate(date);
                article.setContent(content);

                listArticle.add(article);

            }
        } catch (JSONException e) {

        }
        return listArticle;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tabs, container, false);

        listViewArticleFragment = (ListView) rootView.findViewById(R.id.listArticleFragment);
        adapter = new CustomListAdapter(getActivity(), listArticle);
        listViewArticleFragment.setAdapter(adapter);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.name_fragment_offshore);
    }
}
