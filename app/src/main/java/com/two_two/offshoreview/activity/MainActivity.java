package com.two_two.offshoreview.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.two_two.offshoreview.R;
import com.two_two.offshoreview.json.JsonParser;
import com.two_two.offshoreview.json.ProgressDialogForJson;
import com.two_two.offshoreview.data.Article;
import com.two_two.offshoreview.adapter.CustomListAdapter;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String url = "http://offshoreview.eu/api/get_recent_posts/?json=1&json_unescaped_unicode=1&count=15";

    private List<Article> articleList = new ArrayList<Article>();
    private ListView listViewArticles;
    private CustomListAdapter customListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewArticles = (ListView) findViewById(R.id.listViewTitleArticle);

        customListAdapter = new CustomListAdapter(this, articleList);

        listViewArticles.setAdapter(customListAdapter);

        //JsonParser
        JsonParser.jsonParser(MainActivity.this, url, TAG, articleList, customListAdapter);
        //ClickListView
        listViewArticles.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, DetailedArticleActivity.class);
                intent.putExtra("article_id", customListAdapter.getItem(position).getId());
                intent.putExtra("article blogType",customListAdapter.getItem(position).getBlogType());
                startActivity(intent);
            }
        });

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        ProgressDialogForJson.hidePDialog();
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

}
