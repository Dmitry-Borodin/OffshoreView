package com.two_two.offshoreview.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.two_two.offshoreview.R;
import com.two_two.offshoreview.activity.DetailedArticleActivity;
import com.two_two.offshoreview.adapter.CustomListAdapter;
import com.two_two.offshoreview.callbacks.ArticleLoadListenerEmoney;
import com.two_two.offshoreview.data.Article;
import com.two_two.offshoreview.task.TaskLoadArticlesEmoney;
import com.two_two.offshoreview.volley.MyApplication;

import java.util.ArrayList;


public class FragmentEmoneyView extends Fragment implements ArticleLoadListenerEmoney, SwipeRefreshLayout.OnRefreshListener {

    private static final String STATE_ARTICLES = "com.two_two.offshoreview.articles_state_emoney";
    private static final String BLOG_NAME = "emoney";

    private ArrayList<Article> listArticle = new ArrayList<>();
    private CustomListAdapter adapter;
    private ListView listViewArticleFragment;
    private SwipeRefreshLayout swipeRefreshLayout;

    public FragmentEmoneyView(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tabs, container, false);

        listViewArticleFragment = (ListView) rootView.findViewById(R.id.listArticleFragment);
        adapter = new CustomListAdapter(getActivity(), listArticle);
        listViewArticleFragment.setAdapter(adapter);

        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipeRefresh);
        swipeRefreshLayout.setOnRefreshListener(this);

        if(savedInstanceState!=null){
            listArticle = savedInstanceState.getParcelableArrayList(STATE_ARTICLES);
        } else {
            listArticle = MyApplication.getWritableDatabase().getArticleWithDataBase(BLOG_NAME);
            if(listArticle.isEmpty()){
                new TaskLoadArticlesEmoney(this).execute();
            }
        }
        adapter.setArticleList(listArticle);
        listViewArticleFragment.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), DetailedArticleActivity.class);
                intent.putExtra(getString(R.string.article_title_intent), adapter.getItem(position).getTitle());
                intent.putExtra(getString(R.string.article_content_intent), adapter.getItem(position).getContent());
                intent.putExtra(getString(R.string.article_url_intent), adapter.getItem(position).getUrlArticle());
                intent.putExtra(getString(R.string.article_img_intent), adapter.getItem(position).getThumbnailUrl());
                startActivity(intent);
            }
        });

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.name_fragment_emoney);
    }


    @Override
    public void onArticleLoadListenerEmoney(ArrayList<Article> listArticles) {
        if(swipeRefreshLayout.isRefreshing()){
            swipeRefreshLayout.setRefreshing(false);
        }
        adapter.setArticleList(listArticles);
    }

    @Override
    public void onRefresh() {
        new TaskLoadArticlesEmoney(this).execute();
    }
}
