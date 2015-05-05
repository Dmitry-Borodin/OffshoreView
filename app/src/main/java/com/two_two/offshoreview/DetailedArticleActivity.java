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
        title = intent.getStringExtra("article_title");
        content = intent.getStringExtra("article_content");
        TextView tvTitle, tvContent;
        tvTitle = (TextView) findViewById(R.id.detailedArticleTitle);
        tvContent = (TextView) findViewById(R.id.detailedArticleContent);
        tvTitle.setText(title);
        tvContent.setText(content);

//        list = FillArticle.getArticleList();
//        TextView title, content;
//        title = (TextView) findViewById(R.id.detailedArticleTitle);
//        content = (TextView) findViewById(R.id.detailedArticleContent);
//        for (Article x : list) {
//            if(x.getId() == currentArticleId){
//                currentArticle = x;
//                break;
//            }
//        }
//        title.setText(currentArticle.getTitle());
//        content.setText(currentArticle.getContent());
        currentArticleId = intent.getIntExtra(ID_ARTICLE, 0);
        //list = FillArticle.getArticleList();
        TextView title, content;
        title = (TextView) findViewById(R.id.detailedArticleTitle);
        content = (TextView) findViewById(R.id.detailedArticleContent);

        //открываем БД
        localDataBaseHelper sqlHelper = new localDataBaseHelper(this);

        // получаем базу
        SQLiteDatabase sqldb = sqlHelper.getWritableDatabase();

        Cursor cursor = sqldb.query(localDataBaseHelper.OFFSHOREBLOG_TABLENAME,new String[]{localDataBaseHelper.ID,localDataBaseHelper.ARTICLETITLE,localDataBaseHelper.ARTICLECONTENT,
                localDataBaseHelper.ARTICLEPICTURELINK},"id="+currentArticleId,null,null,null,null);//TODO now always OffshoreView, should be changed

        //this is filling article from list //delete it soon
//        for (Article x : list) {
//            if(x.getId() == currentArticleId){
//                currentArticle = x;
//                break;
//            }
//        }
        if (cursor != null){

            cursor.moveToFirst();
            String titleOfKnownID = cursor.getString(cursor.getColumnIndex(localDataBaseHelper.ARTICLETITLE));
            String contentOfKnownID = cursor.getString(cursor.getColumnIndex(localDataBaseHelper.ARTICLECONTENT));

            title.setText(titleOfKnownID);
            content.setText(contentOfKnownID);
        }
        // закрываем соединения с базой данных
        sqldb.close();
        sqlHelper.close();

        assert cursor != null;
        cursor.close();
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
