package com.two_two.offshoreview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.two_two.offshoreview.example.Article;
import com.two_two.offshoreview.example.FillArticle;
import com.two_two.offshoreview.example.TestAdapter;
import com.two_two.offshoreview.fillingClasses.localDataBaseHelper;

import java.util.List;


public class MainActivity extends AppCompatActivity {
    private List<Article> list;
    private ListView titleArticlesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = FillArticle.getArticleList(this);
        titleArticlesList= (ListView) findViewById(R.id.listViewTitleArticle);
        final TestAdapter adapter = new TestAdapter(this, list);
        titleArticlesList.setAdapter(adapter);

        titleArticlesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, DetailedArticleActivity.class);
                intent.putExtra("article_id", adapter.getItem(position).getId());
                startActivity(intent);
            }
        });

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
