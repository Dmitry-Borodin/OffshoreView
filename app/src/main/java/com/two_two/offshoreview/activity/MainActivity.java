package com.two_two.offshoreview.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.two_two.offshoreview.R;
import com.two_two.offshoreview.volley.AppController;
import com.two_two.offshoreview.data.Articles;
import com.two_two.offshoreview.adapter.CustomListAdapter;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String url = "http://offshoreview.eu/api/get_recent_posts/?json=1&json_unescaped_unicode=1&count=15";

    private ProgressDialog pDialog;
    private List<Articles> articlesList = new ArrayList<Articles>();
    private ListView listViewArticles;
    private CustomListAdapter customListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewArticles = (ListView) findViewById(R.id.listViewTitleArticle);
        customListAdapter = new CustomListAdapter(this, articlesList);
        listViewArticles.setAdapter(customListAdapter);

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();


        //TODO create new method and add this in method
        JsonObjectRequest articleReq = new JsonObjectRequest( url,  new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, response.toString());
                hidePDialog();
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

        listViewArticles.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, DetailedArticleActivity.class);
                intent.putExtra("article_title", customListAdapter.getItem(position).getTitle());
                intent.putExtra("article_content", customListAdapter.getItem(position).getContent());
                startActivity(intent);
            }
        });

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        hidePDialog();
    }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }






    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    //tris os for change test


    //try commit

}