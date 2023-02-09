package com.example.news.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.news.R;
import com.example.news.fragments.ViewPagerFragmentBusiness;
import com.example.news.model.NewsArticle;
import com.example.news.model.OnArticleClickListener;
import com.example.news.model.data.OnButtonClicked;

import java.util.List;
import java.util.zip.Inflater;

public class RecyclerViewAdapterNewsHeadlines extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

   private int dummycount;
   private String dummySource, dummyTime, dummyHeadline;
   private List<NewsArticle> newsArticles;
   private OnArticleClickListener onArticleClickListener;
   private OnButtonClicked onButtonClicked;

   public RecyclerViewAdapterNewsHeadlines(OnArticleClickListener onArticleClickListener, OnButtonClicked onButtonClicked) {
      this.onArticleClickListener = onArticleClickListener;
      this.onButtonClicked = onButtonClicked;
      dummycount = 5;
      dummySource = "NDTV";
      dummyHeadline = "Sensex ends 304 pts lower at 60,353, Nifty below 18K; Bajaj Finance down 7%";
      dummyTime = "2 hours ago";
   }

   @Override
   public int getItemViewType(int position) {
      if(newsArticles == null || newsArticles.size() == 0){
         if(position == dummycount) return 1;
         else return 0;
      }else if(position == newsArticles.size()){
         return 1;
      }else{
         return 0;
      }
   }

   @NonNull
   @Override
   public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

      switch (viewType){
         case 0 : View view = LayoutInflater.from(parent.getContext()).inflate(
                 R.layout.row_recycler_view_news_headlines,parent,false);
            return new ViewHolder(view);

         case 1 : View view1 = LayoutInflater.from(parent.getContext()).inflate(
                 R.layout.row_recycler_view_next_button,parent,false);
         return new ViewHolderButton(view1);

         default: return null;
      }

   }

   @Override
   public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

      switch (holder.getItemViewType()){
         case 0 : ViewHolder viewHolder = (ViewHolder) holder;
            if(newsArticles == null || newsArticles.size() == 0){
               viewHolder.source1.setText(dummySource);
               viewHolder.time1.setText(dummyTime);
               viewHolder.headline1.setText(dummyHeadline);

            }else{
               viewHolder.source1.setText(newsArticles.get(position).getSource_id());
               viewHolder.time1.setText(newsArticles.get(position).getPubDate());
               viewHolder.headline1.setText(newsArticles.get(position).getTitle());
            }
            break;
         case 1 : ViewHolderButton viewHolderButton = (ViewHolderButton) holder;
      }




   }

   @Override
   public int getItemCount() {
      if(newsArticles == null || newsArticles.size() == 0){
         return dummycount+1;
      }
      int i = newsArticles.size()+1;
      return i;
   }

   public void updateData(List<NewsArticle> newsArticles){
      this.newsArticles = newsArticles;
      notifyDataSetChanged();
   }

   protected class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

      private TextView source1, headline1, time1;
      private CardView card1;

      public ViewHolder(@NonNull View itemView) {
         super(itemView);

         source1 = itemView.findViewById(R.id.textview_row_recyclerview_headlines_source);
         headline1 = itemView.findViewById(R.id.textview_row_recyclerview_headlines_headline);
         time1 = itemView.findViewById(R.id.textview_row_recyclerview_headlines_time);
         card1 = itemView.findViewById(R.id.main_card_1);


         card1.setOnClickListener(this);
      }

      @Override
      public void onClick(View v) {
         if(v.equals(card1)){
            if(newsArticles == null || newsArticles.size() == 0){

            }else{
               NewsArticle newsArticle = newsArticles.get(getAdapterPosition());
               onArticleClickListener.onArticleClicked(newsArticle);
            }

         }
      }
   }

   protected class ViewHolderButton extends RecyclerView.ViewHolder implements View.OnClickListener{

      private TextView statusTv;
      private Button button;

      public ViewHolderButton(@NonNull View itemView) {
         super(itemView);
         statusTv = itemView.findViewById(R.id.tv_row_recyclerview_row_tv);
         button = itemView.findViewById(R.id.button_row_recyclerview_button);

         button.setOnClickListener(this);
      }

      @Override
      public void onClick(View v) {
         if(v.equals(button)){
            onButtonClicked.onClicked(true);
         }
      }
   }
}
