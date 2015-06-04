package com.two_two.offshoreview.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.ShareActionProvider;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.two_two.offshoreview.R;
import com.two_two.offshoreview.volley.VolleySingleton;


public class DetailedArticleActivity extends AppCompatActivity {

    private static final String TAG_LOG = DetailedArticleActivity.class.getSimpleName();
    private String title, content, img, urlArticle;
    private NetworkImageView thumbNail;
    private ImageLoader imageLoader;
    private VolleySingleton volleySingleton;
    private TextView tvTitle, tvContent;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailed_article);
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Статья");


        volleySingleton = VolleySingleton.getInstance();
        imageLoader = volleySingleton.getImageLoader();

        Intent intent = getIntent();
        urlArticle = intent.getStringExtra("article_url");
        title = intent.getStringExtra("article_title");
        content = intent.getStringExtra("article_content");
        img = intent.getStringExtra("article_img");
        img = img.replace("-150x150", "");
        thumbNail = (NetworkImageView) findViewById(R.id.detailedArticleImg);
        tvTitle = (TextView) findViewById(R.id.detailedArticleTitle);
        tvContent = (TextView) findViewById(R.id.detailedArticleContent);
        tvTitle.setText(title);
        thumbNail.setImageUrl(img, imageLoader);
        tvContent.setText(Html.fromHtml(content));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detailed_article, menu);
        MenuItem menuItem = menu.findItem(R.id.action_share);
        ShareActionProvider mShareActionProvider =
                (ShareActionProvider) MenuItemCompat.getActionProvider(menuItem);
        if(mShareActionProvider != null){
            mShareActionProvider.setShareIntent(createShareForecastIntent());
        } else {
            Log.d(TAG_LOG, "Share ACTION is null");
        }
        return true;
    }

    private Intent createShareForecastIntent(){
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, urlArticle);
        return shareIntent;
    }
}
