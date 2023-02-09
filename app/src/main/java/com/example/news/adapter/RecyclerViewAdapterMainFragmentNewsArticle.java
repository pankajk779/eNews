package com.example.news.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.news.R;
import com.example.news.model.NewsArticle;
import com.squareup.picasso.Picasso;
import java.util.List;

public class RecyclerViewAdapterMainFragmentNewsArticle extends RecyclerView.Adapter<RecyclerViewAdapterMainFragmentNewsArticle.ViewHolder> {

    private final String TAG = this.getClass().getSimpleName();
    private List<NewsArticle> newsArticles;

    public RecyclerViewAdapterMainFragmentNewsArticle() {
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_recycler_view_news_article_singlerow,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.newsTitle.setText(newsArticles.get(position).getTitle());

        if(newsArticles.get(position).getImage_url() == null){

        }else{
            Picasso.get()
                    .load(newsArticles.get(position).getImage_url())
                    .resize(200,200)
                    .centerCrop()
                    .into(holder.newsImage);
        }

        Log.d(TAG, "onBindViewHolder: image url = "+ newsArticles.get(position).getImage_url());
    }

    @Override
    public int getItemCount() {
        if(newsArticles == null)
            return 0;
        else return newsArticles.size();
    }

    public void updateLatestNewsData(List<NewsArticle> newsArticles){
        this.newsArticles = newsArticles;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView newsTitle;
        private ImageView newsImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            newsTitle = itemView.findViewById(R.id.textview_row_recyclerview_row_newsarticle_maincard_headline_card);
            newsImage = itemView.findViewById(R.id.imageview_row_recyclerview_row_newsarticle_articleimage);
        }
    }
}
