package com.two_two.offshoreview.example;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.two_two.offshoreview.R;

import java.util.List;

/**
 * Created by Stealps on 01.05.2015.

public class TestAdapter extends BaseAdapter {

    List<Article> articles;
    private LayoutInflater inflater;
    private Context context;

    public TestAdapter(Context context, List<Article> articles) {
        this.articles = articles;
        this.context = context;
        inflater=(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return articles.size();
    }

    @Override
    public Article getItem(int position) {
        return articles.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.item_menu,parent,false);
        TextView title = (TextView) convertView.findViewById(R.id.templateTitleTextView);
        title.setText(getItem(position).getTitle());
        return convertView;
    }



}*/
