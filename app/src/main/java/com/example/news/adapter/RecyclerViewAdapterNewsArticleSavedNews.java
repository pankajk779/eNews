package com.example.news.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.news.R;
import com.example.news.util.Util;
import com.squareup.picasso.Picasso;
import java.util.List;

public class RecyclerViewAdapterNewsArticleSavedNews extends RecyclerView.Adapter<RecyclerViewAdapterNewsArticleSavedNews.ViewHolder> {

    private int dummyCount = 10;

    public RecyclerViewAdapterNewsArticleSavedNews() {

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_recycler_view_news_article_singlerow, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return dummyCount;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView newsImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            newsImage = itemView.findViewById(R.id.imageview_row_recyclerview_row_newsarticle_articleimage);
        }
    }
}
