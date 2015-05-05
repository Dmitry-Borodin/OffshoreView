package com.two_two.offshoreview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by drew on 04.05.15.
 */
abstract public class MainListAdapter extends RecyclerView.Adapter<MainListAdapter.MainListViewHolder>{

    public interface IOnListItemClick {
        void onClick(int pos, int pid);
    }

    public interface IOnViewHolderClick {
        void onListItemClick(View v, int position, int pid);
    }

    private IOnListItemClick adapterListener;

    public IOnViewHolderClick viewHolderClickCallback = new IOnViewHolderClick() {
        @Override
        public void onListItemClick(View v, int position, int command) {
            adapterListener.onClick(position, command);
        }
    };

    public MainListAdapter(IOnListItemClick callback) {
        adapterListener = callback;
    }

    abstract public static class MainListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public int position;
        public int pid;
        private IOnViewHolderClick clickListener;

        public MainListViewHolder(View v, IOnViewHolderClick listener) {
            super(v);
            this.clickListener = listener;
            findControls(v);
            v.setOnClickListener(this);
        }

        abstract void findControls (View v);

        @Override
        public void onClick(View v) {
            clickListener.onListItemClick(v, position, pid);
        }
    }
}
