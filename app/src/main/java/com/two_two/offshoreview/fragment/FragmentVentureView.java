package com.two_two.offshoreview.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.two_two.offshoreview.R;
import com.two_two.offshoreview.activity.DetailedArticleActivity;
import com.two_two.offshoreview.adapter.CustomRecyclerAdapter;
import com.two_two.offshoreview.adapter.RecyclerItemClickListenerArticle;
import com.two_two.offshoreview.callbacks.ArticleLoadListenerVenture;
import com.two_two.offshoreview.data.Article;
import com.two_two.offshoreview.task.TaskLoadArticlesVenture;
import com.two_two.offshoreview.volley.MyApplication;

import java.util.ArrayList;


public class FragmentVentureView extends Fragment implements ArticleLoadListenerVenture, SwipeRefreshLayout.OnRefreshListener {
    private static final String STATE_ARTICLES = "com.two_two.offshoreview.articles_state_venture";
    private static final String BLOG_NAME = "venture";

    private ArrayList<Article> listArticle = new ArrayList<>();
    private CustomRecyclerAdapter adapter;
    private RecyclerView recyclerViewArticle;
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

        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipeRefresh);
        swipeRefreshLayout.setOnRefreshListener(this);

        recyclerViewArticle = (RecyclerView) rootView.findViewById(R.id.recyclerViewArticle);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerViewArticle.setLayoutManager(layoutManager);
        adapter = new CustomRecyclerAdapter(getActivity(), listArticle);
        recyclerViewArticle.setAdapter(adapter);

        recyclerViewArticle.addOnItemTouchListener(new RecyclerItemClickListenerArticle(getActivity(),
                new RecyclerItemClickListenerArticle.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(getActivity(), DetailedArticleActivity.class);
                        intent.putExtra(getString(R.string.article_title_intent), adapter.getItem(position).getTitle());
                        intent.putExtra(getString(R.string.article_content_intent), adapter.getItem(position).getContent());
                        intent.putExtra(getString(R.string.article_url_intent), adapter.getItem(position).getUrlArticle());
                        intent.putExtra(getString(R.string.article_img_intent), adapter.getItem(position).getThumbnailUrl());
                        startActivity(intent);
                    }
                }));


        if(savedInstanceState!=null){
            listArticle = savedInstanceState.getParcelableArrayList(STATE_ARTICLES);
        } else {
            listArticle = MyApplication.getWritableDatabase().getArticleWithDataBase(BLOG_NAME);
            if(listArticle.isEmpty()){
                new TaskLoadArticlesVenture(this, getActivity()).execute();
            }
        }
        adapter.setArticleList(listArticle);


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
        new TaskLoadArticlesVenture(this, getActivity()).execute();
    }
}