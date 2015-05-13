package com.two_two.offshoreview.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.two_two.offshoreview.R;
import com.two_two.offshoreview.fragment.FragmentEmoneyView;
import com.two_two.offshoreview.fragment.FragmentOffshoreView;
import com.two_two.offshoreview.fragment.FragmentVentureView;
import com.two_two.offshoreview.fragment.NavigationDrawerFragment;
import com.two_two.offshoreview.json.JsonParser;
import com.two_two.offshoreview.json.ProgressDialogForJson;
import com.two_two.offshoreview.data.Article;
import com.two_two.offshoreview.adapter.CustomListAdapter;
import com.two_two.offshoreview.tabs.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String url = "http://offshoreview.eu/api/get_recent_posts/?json=1&json_unescaped_unicode=1&count=15";

    private List<Article> articleList = new ArrayList<Article>();
    private ListView listViewArticles;
    private CustomListAdapter customListAdapter;
    private Toolbar toolbar;
    //Tabs
    private ViewPager mPager;
    private SlidingTabLayout mTabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Fragment fragment = new FragmentOffshoreView();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame, fragment).commit();


        //work for Toolbar
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        //work for navigation drawer
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        NavigationDrawerFragment drawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), toolbar);

/* TODO add after test Tabs
        //work for article list
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
                intent.putExtra("article blogType", customListAdapter.getItem(position).getBlogType());
                startActivity(intent);
            }
        });

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ProgressDialogForJson.hidePDialog();
    }*/
} //delete after unComment
    // show FragmentBlog
    public void onSelectDrawerItem(int position){
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new FragmentOffshoreView();
                break;
            case 1:
                fragment = new FragmentEmoneyView();
                break;
            case 2:
                fragment = new FragmentVentureView();
            default:
                break;
        }
        if(fragment != null){
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame, fragment).commit();

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
}
