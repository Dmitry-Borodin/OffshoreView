package com.two_two.offshoreview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.two_two.offshoreview.example.Article;
import java.util.ArrayList;

/**
 * Created by drew on 04.05.15.
 */
public class CardsAdapter extends MainListAdapter {

    private ArrayList<Article> mainListArray;

    public CardsAdapter(ArrayList<Article> array, IOnListItemClick callback) {
        super(callback);
        mainListArray = array;
    }

    @Override
    public MainListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.mainlistitem, parent, false);
        return new CardsViewHolder(v, viewHolderClickCallback);
    }

    @Override
    public void onBindViewHolder(MainListViewHolder holder, int position) {
        CardsViewHolder cardsViewHolder = (CardsViewHolder) holder;
        cardsViewHolder.position = position;
        cardsViewHolder.tvDate.setText(mainListArray.get(position).getDateTime());
        cardsViewHolder.tvTitle.setText(mainListArray.get(position).getTitle());
//        cardsViewHolder.tvDescription.setText(mainListArray.get(position).getContent());
        cardsViewHolder.pid = mainListArray.get(position).getPid();
//       cardsViewHolder.cardImg.setImageResource(mainListArray.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return mainListArray.size();
    }

    public static class CardsViewHolder extends MainListAdapter.MainListViewHolder {

        public TextView tvDate;
        public TextView tvTitle;
        public TextView tvDescription;
//        public ImageView cardImg;

        public CardsViewHolder(View v, IOnViewHolderClick listener) {
            super(v, listener);
        }

        @Override
        void findControls(View v) {
            tvDate = (TextView) v.findViewById(R.id.card_date);
            tvTitle = (TextView) v.findViewById(R.id.card_title);
            tvDescription = (TextView) v.findViewById(R.id.card_content);
//            cardImg = (ImageView) v.findViewById(R.id.card_image);
        }
    }
}
