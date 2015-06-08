package com.two_two.offshoreview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.two_two.offshoreview.R;
import com.two_two.offshoreview.data.Article;
import com.two_two.offshoreview.volley.VolleySingleton;

import java.util.ArrayList;

public class CustomRecyclerAdapter extends RecyclerView.Adapter<CustomRecyclerAdapter.ArticleViewHolder>{

    private ArrayList<Article> listArticles = new ArrayList<>();
    private Context context;
    private LayoutInflater inflater;

    private VolleySingleton volleySingleton;
    private ImageLoader imageLoader;

    public void setArticleList(ArrayList<Article> listArticles){
        this.listArticles=listArticles;
        notifyDataSetChanged();
    }

    public CustomRecyclerAdapter(Context context, ArrayList<Article> listArticles) {
        inflater = LayoutInflater.from(context);
        this.listArticles = listArticles;
        this.context = context;
        volleySingleton = VolleySingleton.getInstance();
        imageLoader = volleySingleton.getImageLoader();
    }

    @Override
    public ArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = inflater.inflate(R.layout.item_menu, parent, false);
        ArticleViewHolder holder = new ArticleViewHolder(rootView);
        return holder;
    }

    @Override
    public void onBindViewHolder(ArticleViewHolder holder, int position) {
        Article article = listArticles.get(position);
        holder.thumbNail.setImageUrl(article.getThumbnailUrl(), imageLoader);
        holder.category.setText(article.getCategory());
        holder.title.setText(article.getTitle());
        holder.date.setText(article.getDate());
    }

    @Override
    public int getItemCount() {
        return listArticles.size();
    }

    public Article getItem(int position){
        return listArticles.get(position);
    }


    public static class ArticleViewHolder extends RecyclerView.ViewHolder{

        NetworkImageView thumbNail;
        TextView title;
        TextView date;
        TextView category;

        public ArticleViewHolder(View itemView) {
            super(itemView);
            thumbNail = (NetworkImageView) itemView.findViewById(R.id.articleImg);
            title = (TextView) itemView.findViewById(R.id.articleTitle);
            date = (TextView) itemView.findViewById(R.id.articleDate);
            category = (TextView) itemView.findViewById(R.id.tvTestCategory);
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


    //click on itemRecyclerView
    public class RecyclerItemClickListenerArticle implements RecyclerView.OnItemTouchListener {

        private OnItemClickListener mListener;
        private GestureDetector mGestureDetector;



        public RecyclerItemClickListenerArticle(Context context, OnItemClickListener listener) {
            mListener = listener;
            mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            View childView = rv.findChildViewUnder(e.getX(), e.getY());
            if (childView != null && mListener != null && mGestureDetector.onTouchEvent(e)) {
                mListener.onItemClick(childView, rv.getChildPosition(childView));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
}
