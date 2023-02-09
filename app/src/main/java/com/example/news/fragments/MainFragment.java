package com.example.news.fragments;

import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.news.DBTransfer;
import com.example.news.R;
import com.example.news.adapter.RecyclerViewAdapterMainFragmentToolbar;
import com.example.news.adapter.ViewPagerAdapterMainFragment;
import com.example.news.data.DBHandler;
import com.example.news.model.Category;
import com.example.news.model.NewsArticle;
import com.example.news.model.viewModel.MainFragmentViewModel;
import com.example.news.util.Util;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class MainFragment extends Fragment implements MainFragmentCallback, View.OnClickListener{

    private final String TAG = this.getClass().getSimpleName();
    private MainFragmentViewModel mainFragmentViewModel;
    private RecyclerView recyclerViewToolbar;
    private HorizontalScrollView scrollViewToolbar;
    private ViewPager2 viewPager2MainFragment;
    private RecyclerViewAdapterMainFragmentToolbar recyclerViewAdapterMainFragmentToolbar;
    private DBHandler dbHandler;
    private DBTransfer dbTransfer;
    private TabLayout tabLayout;
    private ImageButton settings;

    private AppBarLayout appBarLayout;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private ConstraintLayout cLAppBarLayout;
    private ImageView frameBackgroundImage, frameCollapsableBackgroundImage;
    private Button button0, button1, button2, button3, button4, button5, button6, button7, button8, button9, button10;
    private List<Button> buttonViews;
    private ViewPagerAdapterMainFragment viewPagerAdapterMainFragment;
    private static Category requestedCategory;

    public MainFragment() {
        // Required empty public constructor
    }

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dbHandler = new DBHandler(this.getContext());
            dbTransfer = DBTransfer.getInstance();
            dbTransfer.setDbHandler(dbHandler);

            buttonViews = new ArrayList<>();

        mainFragmentViewModel = new ViewModelProvider(this).
                get(MainFragmentViewModel.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews(view);
        addTabs();
        setAdapters();
        setObservers();

//        setButtons(Util.getCategoryList());
//        setButtonClickListeners();

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = SettingsFragment.newInstance();
                FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
                fragmentTransaction.add(R.id.fragment_container_main_activity,fragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.d(TAG, "onTabSelected: "+ tab.getPosition());
                viewPager2MainFragment.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.BaseOnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                int i = Math.abs(verticalOffset)-appBarLayout.getTotalScrollRange();
                Log.d(TAG, "onOffsetChanged: "+ i);
                if(i >= -40){
                    frameBackgroundImage.setImageAlpha(255);
//                    frameCollapsableBackgroundImage.setImageAlpha(0);
                }else if(-50 <= i && i < -40){
                    frameBackgroundImage.setImageAlpha(90);
                }else{
                    frameBackgroundImage.setImageAlpha(0);
                    frameCollapsableBackgroundImage.setImageAlpha(255);
                    tabLayout.setBackgroundColor(Color.WHITE);
                }

            }
        });

        viewPager2MainFragment.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                Log.d(TAG, "onPageSelected: "+ position);
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });
    }

    private void initViews(View view){
//        recyclerViewToolbar = view.findViewById(R.id.recyclerview_mainfragment_toolbar);
        tabLayout = view.findViewById(R.id.tabLayout_mainFragment);
        viewPager2MainFragment = view.findViewById(R.id.viewpager_mainfragment);
        appBarLayout = view.findViewById(R.id.app_bar_layout_main_fragment);
        appBarLayout.bringToFront();
        collapsingToolbarLayout = view.findViewById(R.id.collapsingtoolbarlayout_main_fragment);
        cLAppBarLayout = view.findViewById(R.id.constraintlayout_appbarlayout_mainfragment);
        frameBackgroundImage = view.findViewById(R.id.imageview_frame_appbarlayout_mainfragment);
        scrollViewToolbar = view.findViewById(R.id.button_scrollview);
        frameCollapsableBackgroundImage = view.findViewById(R.id.imageview_frame_collapsable_mainfragment);
        settings = view.findViewById(R.id.imageButton_mainFragment_settings);
        button0 = view.findViewById(R.id.button1); buttonViews.add(button0);
        button1 = view.findViewById(R.id.button2); buttonViews.add(button1);
        button2 = view.findViewById(R.id.button3); buttonViews.add(button2);
        button3 = view.findViewById(R.id.button4); buttonViews.add(button3);
        button4 = view.findViewById(R.id.button5); buttonViews.add(button4);
        button5 = view.findViewById(R.id.button6); buttonViews.add(button5);
        button6 = view.findViewById(R.id.button7); buttonViews.add(button6);
        button7 = view.findViewById(R.id.button8); buttonViews.add(button7);
        button8 = view.findViewById(R.id.button9); buttonViews.add(button8);
        button9 = view.findViewById(R.id.button10); buttonViews.add(button9);
        button10 = view.findViewById(R.id.button11); buttonViews.add(button10);
    }

    private void addTabs(){

        HashMap<String, Category> cList = Util.getCategoryList();
        Collection<Category> list = Util.getCategoryList().values();
        ArrayList<Category> aList = new ArrayList<>(list);
        for (int i = 0; i < Util.getCategoryList().size(); i++) {
            switch(i){
                case 0 : tabLayout.addTab(tabLayout.newTab().setText(cList.get("business").getName()));
                break;
                case 1 : tabLayout.addTab(tabLayout.newTab().setText(cList.get("entertainment").getName()));break;
                case 2 : tabLayout.addTab(tabLayout.newTab().setText(cList.get("environment").getName()));break;
                case 3 : tabLayout.addTab(tabLayout.newTab().setText(cList.get("food").getName()));break;
                case 4 : tabLayout.addTab(tabLayout.newTab().setText(cList.get("health").getName()));break;
                case 5 : tabLayout.addTab(tabLayout.newTab().setText(cList.get("politics").getName()));break;
                case 6 : tabLayout.addTab(tabLayout.newTab().setText(cList.get("science").getName()));break;
                case 7 : tabLayout.addTab(tabLayout.newTab().setText(cList.get("sports").getName()));break;
                case 8 : tabLayout.addTab(tabLayout.newTab().setText(cList.get("technology").getName()));break;
                case 9 : tabLayout.addTab(tabLayout.newTab().setText(cList.get("top").getName()));break;
                case 10 : tabLayout.addTab(tabLayout.newTab().setText(cList.get("world").getName()));break;

            }

//            tabLayout.addTab(tabLayout.newTab().setText(cList.get(cList.keySet().toArray()[i]).getName()));
        }
    }

    private void setAdapters(){

        viewPagerAdapterMainFragment = new ViewPagerAdapterMainFragment(
                getChildFragmentManager(),getLifecycle()
        );
        viewPager2MainFragment.setAdapter(viewPagerAdapterMainFragment);
    }

    private void setObservers(){

    }

    @Override
    public void recyclerViewClickPosition(int position) {
        viewPager2MainFragment.setCurrentItem(position);
    }

    private void setButtons(HashMap<String, Category> categoryList){
        button0.setText(categoryList.get(categoryList.keySet().toArray()[0]).getName());
        button1.setText(categoryList.get(categoryList.keySet().toArray()[1]).getName());
        button2.setText(categoryList.get(categoryList.keySet().toArray()[2]).getName());
        button3.setText(categoryList.get(categoryList.keySet().toArray()[3]).getName());
        button4.setText(categoryList.get(categoryList.keySet().toArray()[4]).getName());
        button5.setText(categoryList.get(categoryList.keySet().toArray()[5]).getName());
        button6.setText(categoryList.get(categoryList.keySet().toArray()[6]).getName());
        button7.setText(categoryList.get(categoryList.keySet().toArray()[7]).getName());
        button8.setText(categoryList.get(categoryList.keySet().toArray()[8]).getName());
        button9.setText(categoryList.get(categoryList.keySet().toArray()[9]).getName());
        button10.setText(categoryList.get(categoryList.keySet().toArray()[10]).getName());
    }
    
    private void setButtonClickListeners(){
        button0.setOnClickListener(this);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);
        button10.setOnClickListener(this);

    }

    private void changeButtonColor(Button button){
        for(Button view : buttonViews){
            if(view.equals(button)){
                button.setBackgroundColor(Color.WHITE);
                button.setTextColor(Color.BLACK);
            }else{
                view.setBackgroundColor(Color.parseColor("#87CEEB"));
                view.setTextColor(Color.BLACK);
            }
        }

    }

    @Override
    public void onClick(View v) {
        Log.d(TAG, "onClick: view is clicked");

        for (int i = 0; i <buttonViews.size(); i++) {
            if(v.equals(buttonViews.get(i))){
                viewPager2MainFragment.setCurrentItem(i);
                changeButtonColor(buttonViews.get(i));
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}