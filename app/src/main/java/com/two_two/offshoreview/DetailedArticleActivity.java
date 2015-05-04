package com.two_two.offshoreview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.two_two.offshoreview.example.Article;
import com.two_two.offshoreview.example.FillArticle;

import java.util.List;


public class DetailedArticleActivity extends AppCompatActivity {
    private int currentArticleId;
    private static final String ID_ARTICLE = "article_id";
    private List<Article> list;
    private Article currentArticle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailed_article);
        Intent intent = getIntent();
        currentArticleId = intent.getIntExtra(ID_ARTICLE, 0);
        list = FillArticle.getArticleList();
        TextView title, content;
        title = (TextView) findViewById(R.id.detailedArticleTitle);
        content = (TextView) findViewById(R.id.detailedArticleContent);
        for (Article x : list) {
            if(x.getId() == currentArticleId){
                currentArticle = x;
                break;
            }
        }
        title.setText(currentArticle.getTitle());
        content.setText(currentArticle.getContent());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detailed_article, menu);
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
