package com.example.news.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.news.R;
import com.example.news.model.NewsArticle;
import com.example.news.model.viewModel.MainFragmentViewModel;
import com.squareup.picasso.Picasso;

public class FragmentNewsDetails extends Fragment {

    private final String TAG = getClass().getSimpleName();
    private MainFragmentViewModel mainFragmentViewModel;
    private TextView tvHeading, body, time, source, noImage;
    private NewsArticle newsArticle;
    private ImageView imageView;

    public FragmentNewsDetails() {
        // Required empty public constructor
    }

    public static FragmentNewsDetails newInstance(NewsArticle newsArticle) {
        Bundle args = new Bundle();
        args.putParcelable("key", newsArticle);
        FragmentNewsDetails fragment = new FragmentNewsDetails();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        newsArticle = getArguments().getParcelable("key");
        return inflater.inflate(R.layout.fragment_news_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews(view);

        if(newsArticle.getTitle() != null){
            tvHeading.setText(newsArticle.getTitle());
        }
        if(newsArticle.getDescription() != null){
            body.setText(newsArticle.getDescription());
        }
        if(newsArticle.getSource_id() != null){
            source.setText("Source : " +newsArticle.getSource_id());
        }

        if(newsArticle.getPubDate() != null){
            time.setText(" Published on : "+ newsArticle.getPubDate());
        }
        if(newsArticle.getImage_url() == null){
            noImage.setVisibility(View.VISIBLE);
        }else{
            Picasso.get().load(newsArticle.getImage_url()).into(imageView);
        }

    }

    private void initViews(View view){
        tvHeading = view.findViewById(R.id.tv_newsDetails_headline);
        imageView = view.findViewById(R.id.imageView_topStory);
        body = view.findViewById(R.id.tv_details_body);
        time = view.findViewById(R.id.tv_newsDetails_time);
        source = view.findViewById(R.id.tv_details_source);
        noImage = view.findViewById(R.id.tv_details_noImage);
        noImage.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}