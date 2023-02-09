package com.example.news.adapter;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.news.R;
import com.example.news.fragments.MainFragmentCallback;
import com.example.news.fragments.RecyclerViewAdapterCallback;
import com.example.news.model.Category;
import com.example.news.util.Util;

import java.util.HashMap;
import java.util.List;

public class RecyclerViewAdapterMainFragmentToolbar extends RecyclerView.Adapter<RecyclerViewAdapterMainFragmentToolbar.ViewHolder>
implements RecyclerViewAdapterCallback{

    private HashMap<String, Category> newsCategories;
    private int selectedView = -1;
    private MainFragmentCallback mainFragmentCallback;

    public RecyclerViewAdapterMainFragmentToolbar(MainFragmentCallback mainFragmentCallback) {
        newsCategories = Util.getCategoryList();
        this.mainFragmentCallback = mainFragmentCallback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_recyclerview_mainfragment_toolbar,parent,false);
        return new ViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.button.setText(newsCategories.get(newsCategories.keySet().toArray()[position]).getName());

        if(selectedView == position){
            holder.button.setBackgroundColor(Color.WHITE);
            holder.button.setTextColor(Color.BLACK);
            holder.button.setAlpha(1);
        }else{
            holder.button.setBackgroundColor(Color.parseColor("#FF1976D2"));
            holder.button.setTextColor(Color.WHITE);
            holder.button.setAlpha(1);
        }
    }

    @Override
    public int getItemCount() {
        if(newsCategories == null){
            return 0;
        }else{
            return newsCategories.size();
        }
    }

    @Override
    public void viewPagerPosition(int position) {
        selectedView = position;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private Button button;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            button = itemView.findViewById(R.id.row_button_recyclerview_mainfragment_toolbar);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectedView = getAdapterPosition();
                    mainFragmentCallback.recyclerViewClickPosition(getAdapterPosition());
                    notifyDataSetChanged();
                }
            });
        }
    }
}
