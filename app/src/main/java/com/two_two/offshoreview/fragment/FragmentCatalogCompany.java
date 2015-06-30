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

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.two_two.offshoreview.R;
import com.two_two.offshoreview.activity.DetailedArticleActivity;
import com.two_two.offshoreview.adapter.CustomRecyclerAdapter;
import com.two_two.offshoreview.adapter.RecyclerItemClickListenerArticle;
import com.two_two.offshoreview.callbacks.ArticleLoadListenerCatalog;
import com.two_two.offshoreview.callbacks.ArticleLoadListenerOffshore;
import com.two_two.offshoreview.data.Article;
import com.two_two.offshoreview.task.TaskLoadArticlesCatalog;
import com.two_two.offshoreview.task.TaskLoadArticlesOffshore;
import com.two_two.offshoreview.volley.MyApplication;

import java.util.ArrayList;

public class FragmentCatalogCompany extends Fragment implements ArticleLoadListenerCatalog, SwipeRefreshLayout.OnRefreshListener {

    private static final String STATE_ARTICLES = "com.two_two.offshoreview.articles_state_catalog";
    private static final String BLOG_NAME = "catalog";

    private ArrayList<Article> listArticle = new ArrayList<>();
    private CustomRecyclerAdapter adapter;
    private RecyclerView recyclerViewArticle;
    private SwipeRefreshLayout swipeRefreshLayout;

    public FragmentCatalogCompany(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new TaskLoadArticlesCatalog(this, getActivity()).execute();
    }
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tabs, container, false);

        AdView adView = (AdView) rootView.findViewById(R.id.adView);
        // Поиск AdView как ресурса и отправка запроса.
//        AdRequest adRequest =new AdRequest.Builder()
//                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
//                .addTestDevice("312D074FC3BA625F8AAF2277E76888D0").build();

        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);



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
                new TaskLoadArticlesCatalog(this, getActivity()).execute();
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
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.name_fragment_catalog);
    }

    @Override
    public void onArticleLoadListenerCatalog(ArrayList<Article> listArticles) {
        if(swipeRefreshLayout.isRefreshing()){
            swipeRefreshLayout.setRefreshing(false);
        }
        adapter.setArticleList(listArticles);
    }

    @Override
    public void onRefresh() {
        new TaskLoadArticlesCatalog(this, getActivity()).execute();
    }
}
