package com.two_two.offshoreview.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.two_two.offshoreview.R;
import com.two_two.offshoreview.data.Article;
import com.two_two.offshoreview.volley.VolleySingleton;

import java.util.ArrayList;


public class CustomListAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private ArrayList<Article> articleList = new ArrayList<>();

    private VolleySingleton volleySingleton;
    private ImageLoader imageLoader;

    public void setArticleList(ArrayList<Article> articleList){
                this.articleList=articleList;
                notifyDataSetChanged();
        }

    public CustomListAdapter(Activity activity, ArrayList<Article> articleList){
        this.activity = activity;
        this.articleList = articleList;
        volleySingleton = VolleySingleton.getInstance();
        imageLoader = volleySingleton.getImageLoader();
    }
    @Override
    public int getCount() {
        return articleList.size();
    }

    @Override
    public Article getItem(int position) {
        return articleList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null) {
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_menu, null);
        }
        if (imageLoader == null) {
            imageLoader = volleySingleton.getImageLoader();
        }
        ViewHolder holder = new ViewHolder();
        holder.thumbNail = (NetworkImageView) convertView.findViewById(R.id.articleImg);
        holder.title = (TextView) convertView.findViewById(R.id.articleTitle);
        holder.date = (TextView) convertView.findViewById(R.id.articleDate);
        holder.category = (TextView) convertView.findViewById(R.id.tvTestCategory);
        Article article = articleList.get(position);
        holder.thumbNail.setImageUrl(article.getThumbnailUrl(), imageLoader);
        holder.category.setText(article.getCategory());
        holder.title.setText(article.getTitle());
        holder.date.setText(article.getDate());
        convertView.setTag(holder);
        return convertView;
       }

    static class ViewHolder {
        NetworkImageView thumbNail;
        TextView title;
        TextView date;
        TextView category;
    }


}
