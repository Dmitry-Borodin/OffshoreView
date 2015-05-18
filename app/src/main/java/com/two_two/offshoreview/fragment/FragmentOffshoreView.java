package com.two_two.offshoreview.fragment;

import android.app.DownloadManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.two_two.offshoreview.R;
import com.two_two.offshoreview.adapter.CustomListAdapter;
import com.two_two.offshoreview.data.Article;
import com.two_two.offshoreview.tabs.SlidingTabLayout;
import com.two_two.offshoreview.volley.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by marazm on 12.05.2015.
 */
public class FragmentOffshoreView extends Fragment {

    private static final String url = "http://offshoreview.eu/api/get_recent_posts/?json=1&json_unescaped_unicode=1&count=15";


    private SlidingTabLayout mSlidingTabLayout;
    private ViewPager mViewPager;

    private VolleySingleton volleySingleton;
    private ImageLoader imageLoader;
    private RequestQueue requestQueue;
    private ArrayList<Article> listArticle = new ArrayList<>();
    private CustomListAdapter adapter;
    private ListView listViewArticleFragment;

    public FragmentOffshoreView(){

    }

    private String[] category = {"Новости", "Статьи", "Юрисдикции", "События в мире",
                                "Аналитика", "Интересное"};


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        volleySingleton = VolleySingleton.getInstance();
        requestQueue = volleySingleton.getRequestQueue();
        sendJsonRequest();
    }

    private void sendJsonRequest() {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                listArticle = parseJSONResponse(response);
                adapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(request);
    }
    //TODO add create list with category
    private ArrayList<Article> parseJSONResponse(JSONObject response) {

        listArticle = new ArrayList<>();

        if(response == null || response.length() > 0){

        }
        try {
            JSONArray arrayArticle = response.getJSONArray("posts");
            for (int i = 0; i < arrayArticle.length(); i++) {
                JSONObject currentArticle = arrayArticle.getJSONObject(i);
                int id = currentArticle.getInt("id");
                String title = currentArticle.getString("title");
                String thumbnail = currentArticle.getString("thumbnail");
                String date = currentArticle.getString("date");
                String content = currentArticle.getString("content");
                JSONArray categoryArray = currentArticle.getJSONArray("categories");
                Article article = new Article();
                for (int j = 0; j < categoryArray.length(); j++) {
                    JSONObject currentCategory = categoryArray.getJSONObject(j);
                    String category = currentCategory.getString("title");
                    article.setCategory(category);
                }




                article.setId(id);
                article.setTitle(title);
                article.setThumbnailUrl(thumbnail);
                article.setDate(date);
                article.setContent(content);

                listArticle.add(article);

            }
        } catch (JSONException e) {

        }
        return listArticle;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_blog_with_tabs, container, false);


        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.name_fragment_offshore);
    }

    @Override
    public void onViewCreated(View view,Bundle savedInstanceState) {

        mViewPager = (ViewPager) view.findViewById(R.id.viewPagerBlogs);
        mViewPager.setAdapter(new MyPagerAdapter());

        mSlidingTabLayout = (SlidingTabLayout) view.findViewById(R.id.slidingTabBlogs);
        mSlidingTabLayout.setViewPager(mViewPager);
    }

    class MyPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return category.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return object == view;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return category[position];
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_tabs, container, false);
            container.addView(view);
            if(category[position] == "Новости" ) {
                listViewArticleFragment = (ListView) view.findViewById(R.id.listArticleFragment);
                adapter = new CustomListAdapter(getActivity(), listArticle);
                listViewArticleFragment.setAdapter(adapter);
            }
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View)object);
        }
    }

}
