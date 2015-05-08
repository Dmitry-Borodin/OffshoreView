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


        //work for Toolbar
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        //work for navigation drawer
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        NavigationDrawerFragment drawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), toolbar);

        //Tabs
        mPager = (ViewPager) findViewById(R.id.pagerView);
        mPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        mTabs = (SlidingTabLayout) findViewById(R.id.slidingTabs);
        mTabs.setViewPager(mPager);
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
    //PagerAdapter
    class MyPagerAdapter extends FragmentPagerAdapter{
        String [] testTabs;
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
            testTabs = new String[]{"Новости", "Статьи", "Юрисдикции",
                    "События в мире", "Аналитика", "Интересное"};
        }

        @Override
        public Fragment getItem(int position) {
            FragmentTabs fragmentTabs = FragmentTabs.getInstance(position);

            return fragmentTabs;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return testTabs[position];
        }

        @Override
        public int getCount() {
            return testTabs.length;
        }
    }
    //Fragment for slidingTabs
    public static class FragmentTabs extends Fragment{
        private TextView textView;
        public static FragmentTabs getInstance(int position){
            FragmentTabs fragmentTabs = new FragmentTabs();
            Bundle bundle = new Bundle();
            bundle.putInt("position", position);
            fragmentTabs.setArguments(bundle);
            return fragmentTabs;
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_tabs, container, false);

            textView = (TextView) rootView.findViewById(R.id.position);
            Bundle args = getArguments();
            if(args != null) {
                textView.setText("Page number " + args.getInt("position"));
            }

            return rootView;
        }
    }

}
