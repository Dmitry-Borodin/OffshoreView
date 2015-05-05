package com.two_two.offshoreview;

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

import com.android.volley.toolbox.JsonObjectRequest;
import com.two_two.offshoreview.volley.AppController;
import com.two_two.offshoreview.volley.Articles;
import com.two_two.offshoreview.volley.CustomListAdapter;

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
    private ListView listView;
    private CustomListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listViewTitleArticle);
        adapter = new CustomListAdapter(this, articlesList);
        listView.setAdapter(adapter);

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();



        JsonObjectRequest articleReq = new JsonObjectRequest( url,  new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, response.toString());
                hidePDialog();

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
            adapter.notifyDataSetChanged();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        AppController.getInstance().addToRequestQueue(articleReq);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, DetailedArticleActivity.class);
                intent.putExtra("article_title", adapter.getItem(position).getTitle());
                intent.putExtra("article_content", adapter.getItem(position).getContent());
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
