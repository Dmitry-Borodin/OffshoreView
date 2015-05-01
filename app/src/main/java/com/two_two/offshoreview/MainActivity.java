package com.two_two.offshoreview;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.two_two.offshoreview.example.Article;
import com.two_two.offshoreview.example.FillArticle;
import com.two_two.offshoreview.example.TestAdapter;

import java.util.List;


public class MainActivity extends ActionBarActivity {
    private List<Article> list;
    private ListView titleArticlesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = FillArticle.getArticleList();
        titleArticlesList= (ListView) findViewById(R.id.listViewTitleArticle);
        titleArticlesList.setAdapter(new TestAdapter(this,list));
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
