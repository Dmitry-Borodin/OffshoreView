package com.two_two.offshoreview.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.two_two.offshoreview.R;
import com.two_two.offshoreview.activity.MainActivity;
import com.two_two.offshoreview.data.BlogNames;

import java.util.Collections;
import java.util.List;

/**
 * Created by marazm on 07.05.15.
 */
public class BlogNamesRecyclerAdapter extends
        RecyclerView.Adapter<BlogNamesRecyclerAdapter.MyViewHolder> {


    Context context;
    private final LayoutInflater inflater;
    List<BlogNames> blogNames = Collections.emptyList();

    public BlogNamesRecyclerAdapter(Context context, List<BlogNames> blogNames) {
        inflater = LayoutInflater.from(context);
        this.blogNames = blogNames;
        this.context = context;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = inflater.inflate(R.layout.blog_name_item, parent, false);
        MyViewHolder holder = new MyViewHolder(rootView);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        BlogNames current = blogNames.get(position);
        holder.title.setText(current.getTitle());
        holder.icon.setImageResource(current.getIconId());
    }



    @Override
    public int getItemCount() {
        return blogNames.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView icon;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.listBlogName);
            icon = (ImageView) itemView.findViewById(R.id.iconBlogName);

        }

    }




}
