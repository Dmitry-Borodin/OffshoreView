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
import com.two_two.offshoreview.callbacks.ArticleLoadListenerVenture;
import com.two_two.offshoreview.data.Article;
import com.two_two.offshoreview.task.TaskLoadArticlesVenture;
import com.two_two.offshoreview.volley.MyApplication;

import java.util.ArrayList;


public class FragmentVentureView extends Fragment implements ArticleLoadListenerVenture, SwipeRefreshLayout.OnRefreshListener {
    private static final String STATE_ARTICLES = "articles_state";
    private static final String BLOG_NAME = "venture";

    private ArrayList<Article> listArticle = new ArrayList<>();
    private CustomListAdapter adapter;
    private ListView listViewArticleFragment;
    private SwipeRefreshLayout swipeRefreshLayout;

    public FragmentVentureView(){

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
                new TaskLoadArticlesVenture(this).execute();
            }
        }
        adapter.setArticleList(listArticle);

        listViewArticleFragment.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), DetailedArticleActivity.class);
                intent.putExtra("article_title", adapter.getItem(position).getTitle());
                intent.putExtra("article_content", adapter.getItem(position).getContent());
                intent.putExtra("article_url", adapter.getItem(position).getUrlArticle());
                intent.putExtra("article_img", adapter.getItem(position).getThumbnailUrl());
                startActivity(intent);
            }
        });

        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(STATE_ARTICLES, listArticle);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.name_fragment_venture);
    }

    @Override
    public void onArticleLoadListenerVenture(ArrayList<Article> listArticles) {
        if(swipeRefreshLayout.isRefreshing()){
            swipeRefreshLayout.setRefreshing(false);
        }
        adapter.setArticleList(listArticles);
    }

    @Override
    public void onRefresh() {
        new TaskLoadArticlesVenture(this).execute();
    }
}