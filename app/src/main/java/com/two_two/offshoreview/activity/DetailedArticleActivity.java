package com.two_two.offshoreview.activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.two_two.offshoreview.R;
import com.two_two.offshoreview.data.Article;
import com.two_two.offshoreview.data.LocalDataBaseHelper;

import java.util.List;


public class DetailedArticleActivity extends AppCompatActivity {
    private int currentArticleId;
    private static final String ID_ARTICLE = "article_id";
    private List<Article> list;
    private Article currentArticle;
    private String title, content;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailed_article);

        Intent intent = getIntent();
        /*
//        title = intent.getStringExtra("article_title");   It deleted from MainActivity. Dmitry.
//        content = intent.getStringExtra("article_content");
        TextView tvTitle, tvContent;
        tvTitle = (TextView) findViewById(R.id.detailedArticleTitle);
        tvContent = (TextView) findViewById(R.id.detailedArticleContent);
        tvTitle.setText(title);
        tvContent.setText(content);
*/

  //TODO implement save articles in the database. Need create new Class with Method.
        currentArticleId = intent.getIntExtra(ID_ARTICLE, 0);
        TextView title, content;
        title = (TextView) findViewById(R.id.detailedArticleTitle);
        content = (TextView) findViewById(R.id.detailedArticleContent);

        //открываем БД
        LocalDataBaseHelper sqlHelper = new LocalDataBaseHelper(this);

        // получаем базу
        SQLiteDatabase sqldb = sqlHelper.getWritableDatabase();
        //BEWARE!!!!! i don't look for BlogType!!!
        Cursor cursor = sqldb.query(LocalDataBaseHelper.OFFSHOREBLOG_TABLENAME,new String[]{LocalDataBaseHelper.ID,LocalDataBaseHelper.ARTICLETITLE,LocalDataBaseHelper.ARTICLECONTENT,
                LocalDataBaseHelper.ARTICLEPICTURELINK},"id="+currentArticleId,null,null,null,null);//TODO now always OffshoreView, should be changed


        if (cursor != null){

            cursor.moveToFirst();
            String titleOfKnownID = cursor.getString(cursor.getColumnIndex(LocalDataBaseHelper.ARTICLETITLE));
            String contentOfKnownID = cursor.getString(cursor.getColumnIndex(LocalDataBaseHelper.ARTICLECONTENT));

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
