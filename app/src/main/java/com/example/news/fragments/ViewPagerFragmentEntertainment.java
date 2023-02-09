package com.example.news.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.news.R;
import com.example.news.adapter.RecyclerViewAdapterNewsArticleSavedNews;
import com.example.news.adapter.RecyclerViewAdapterNewsHeadlines;
import com.example.news.model.NewsArticle;
import com.example.news.model.OnArticleClickListener;
import com.example.news.model.StatusList;
import com.example.news.model.data.ApiNextPageString;
import com.example.news.model.data.ErrorCallback;
import com.example.news.model.data.OnButtonClicked;
import com.example.news.model.data.StatusCallback;
import com.example.news.model.viewModel.MainFragmentViewModel;
import com.example.news.util.Util;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerFragmentEntertainment extends Fragment implements OnArticleClickListener, StatusCallback, View.OnClickListener, OnButtonClicked, ApiNextPageString, ErrorCallback {

    private ImageView bannerImageView, imageViewTopStory;
    private final String KEY = "entertainment";
    private String name;
    private String page;
    private TextView pageTitle, news, sourceTopStory, tvLayoutHeading, statusTextView;
    private CardView statusCard;
    private CardView cardView;
    private ProgressBar progressBar;
    private RecyclerView recyclerViewSavedNews, recyclerViewHeadlines;
    private RecyclerViewAdapterNewsArticleSavedNews recyclerViewAdapterNewsArticleSavedNews;
    private RecyclerViewAdapterNewsHeadlines recyclerViewAdapterNewsHeadlines;
    private LinearLayout linearLayout;
    private MainFragmentViewModel mainFragmentViewModel;
    private NewsArticle selectedNews = new NewsArticle();
    private TextView error;

    private final String TAG = this.getClass().getSimpleName();
    private TextView textViewHeadlineTopStory, textViewTimeTopNews;
    private int updateToken = 0;
    private MutableLiveData<List<NewsArticle>> _newsArticles = new MutableLiveData<>(new ArrayList<>());
    public LiveData<List<NewsArticle>> newsArticles = _newsArticles;
    private MutableLiveData<Boolean> _layoutDrawn = new MutableLiveData<>(false);
    private MutableLiveData<Boolean> dataAvailable = new MutableLiveData<>(false);
    private MutableLiveData<Boolean> dataAndLayoutAvailable = new MutableLiveData<>(false);

    public ViewPagerFragmentEntertainment() {
        name = "Entertainment";
    }

    public static ViewPagerFragmentEntertainment newInstance() {
        ViewPagerFragmentEntertainment fragment = new ViewPagerFragmentEntertainment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainFragmentViewModel = new ViewModelProvider(getParentFragment())
                .get(MainFragmentViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_latest_news, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews(view);
        tvLayoutHeading.setText("Entertainment");
        updateToken = 2;
        initSavedRecyclerView();
        setObservers(view);

        cardView.setOnClickListener(this);

        mainFragmentViewModel.entertainmentNewsArticles.observe(
                getViewLifecycleOwner(),
                new Observer<List<NewsArticle>>() {
                    @Override
                    public void onChanged(List<NewsArticle> newsArticles) {
                        if(mainFragmentViewModel.entertainmentNewsArticles.getValue() == null ||
                                mainFragmentViewModel.entertainmentNewsArticles.getValue().size() == 0){

                        }else{
                            Log.d(TAG, "onChanged: got data "+ newsArticles.size());
                            for (int i = 0; i < newsArticles.size(); i++) {
                                if (newsArticles.get(i).getImage_url() != null) {
                                    updateTopStory(newsArticles.get(i));
                                    selectedNews = newsArticles.get(i);
                                    break;
                                }
                                if(i == newsArticles.size()-1){
                                    updateTopStory(newsArticles.get(0));
                                    selectedNews = newsArticles.get(0);
                                }
                            }
                            recyclerViewAdapterNewsHeadlines.updateData(newsArticles);
                        }
                    }
                }
        );
        requestData(null);
    }

    private void requestData(String page){
        statusTextView.setText(StatusList.Sending_Request.toString());
        statusCard.setVisibility(View.VISIBLE);
        mainFragmentViewModel.requestNewsList(Util.getCategoryList().get(KEY), this, this, page, this);

    }

    private void initViews(View view){

        tvLayoutHeading = view.findViewById(R.id.tv_layout_main_heading);
        bannerImageView = view.findViewById(R.id.imageview_layoutlatestnews_bannerimage);
        bannerImageView.setImageResource(R.drawable.entertainment_banner);
        bannerImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        sourceTopStory = view.findViewById(R.id.tv_source_card);
        pageTitle = view.findViewById(R.id.tv_title);
        news = view.findViewById(R.id.tv_news);
        recyclerViewSavedNews = view.findViewById(R.id.recyclerview_row_recyclerview_newsarticle_savednews);
        recyclerViewHeadlines = view.findViewById(R.id.recyclerview_row_recyclerview_newsarticle_newsheadlines);

        imageViewTopStory = view.findViewById(R.id.imageView_topStory);
        textViewHeadlineTopStory = view.findViewById(R.id.tv_headline_card);
        textViewTimeTopNews = view.findViewById(R.id.tv_time_card);
        statusCard = view.findViewById(R.id.cv_latestNews_statusCard);
        progressBar = view.findViewById(R.id.status_progressBar);
        statusTextView = view.findViewById(R.id.status_tv);
        statusCard.setVisibility(View.INVISIBLE);
        cardView = view.findViewById(R.id.firstcard_latestnews);
        error = view.findViewById(R.id.tv_laterstNews_error);
        error.setVisibility(View.INVISIBLE);
    }

    private void initSavedRecyclerView(){
        recyclerViewAdapterNewsArticleSavedNews = new RecyclerViewAdapterNewsArticleSavedNews();
        recyclerViewSavedNews.setLayoutManager(new LinearLayoutManager(this.getContext(),
                RecyclerView.HORIZONTAL, false));
        recyclerViewSavedNews.setAdapter(recyclerViewAdapterNewsArticleSavedNews);

        recyclerViewAdapterNewsHeadlines = new RecyclerViewAdapterNewsHeadlines(this, this);
        recyclerViewHeadlines.setLayoutManager(new LinearLayoutManager(this.getContext(),
                RecyclerView.VERTICAL, false));
        recyclerViewHeadlines.setAdapter(recyclerViewAdapterNewsHeadlines);
    }

    private void setObservers(View view){
        _layoutDrawn.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                checkValues();
            }
        });

        dataAvailable.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                checkValues();
            }
        });

        dataAndLayoutAvailable.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                updateViews(newsArticles.getValue());
            }
        });

        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                _layoutDrawn.postValue(true);
            }
        });
    }

    private void checkValues(){
        if(_layoutDrawn.getValue() == true && dataAvailable.getValue() == true)
            dataAndLayoutAvailable.postValue(true);
    }

    public void updateNewsArticles(List<NewsArticle> newsArticles){
        _newsArticles.postValue(newsArticles);
    }

    private void updateViews(List<NewsArticle> newsArticles){

        if(newsArticles.size() !=0){
            recyclerViewAdapterNewsHeadlines.updateData(newsArticles);
            updateTopStory(newsArticles.get(0));
        }else{

        }

    }

    private void updateTopStory(NewsArticle newsArticle){
        statusCard.setVisibility(View.INVISIBLE);
        textViewHeadlineTopStory.setText(newsArticle.getTitle());
        textViewTimeTopNews.setText(newsArticle.getPubDate());
        sourceTopStory.setText(newsArticle.getSource_id());
        if(newsArticle.getImage_url() == null){

        }else{
            Picasso.get().load(newsArticle.getImage_url()).into(imageViewTopStory);
        }
    }

    private void openFragment(NewsArticle newsArticle){
        Fragment fragment = FragmentNewsDetails.newInstance(newsArticle);
        FragmentTransaction fragmentTransaction = getParentFragment().getParentFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fragment_container_main_activity,fragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onArticleClicked(NewsArticle newsArticle) {
        openFragment(newsArticle);
    }

    @Override
    public void onStatus(StatusList statusList) {
        statusTextView.setText(statusList.toString());
    }

    @Override
    public void onClick(View v) {
        if(v.equals(cardView)){
            openFragment(selectedNews);
        }
    }

    @Override
    public void onClicked(Boolean b) {
        if(page == null){
            Toast.makeText(getContext(), "please wait for data", Toast.LENGTH_LONG).show();
        }else{
            requestData(page);
        }
    }

    @Override
    public void onString(String s) {
        page = s;
        Log.d(TAG, "onString: "+ s);
    }

    @Override
    public void onError(String error) {
        this.error.setText(error);
        this.error.setVisibility(View.VISIBLE);
    }
}
