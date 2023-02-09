package com.example.news.adapter;

import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.news.fragments.ViewPagerFragmentBusiness;
import com.example.news.fragments.ViewPagerFragmentEntertainment;
import com.example.news.fragments.ViewPagerFragmentEnvironment;
import com.example.news.fragments.ViewPagerFragmentFood;
import com.example.news.fragments.ViewPagerFragmentHealth;
import com.example.news.fragments.ViewPagerFragmentPolitics;
import com.example.news.fragments.ViewPagerFragmentScience;
import com.example.news.fragments.ViewPagerFragmentSports;
import com.example.news.fragments.ViewPagerFragmentTechnology;
import com.example.news.fragments.ViewPagerFragmentTop;
import com.example.news.fragments.ViewPagerFragmentWorld;
import com.example.news.model.Category;
import com.example.news.model.NewsArticle;
import com.example.news.util.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ViewPagerAdapterMainFragment extends FragmentStateAdapter {


    HashMap<String, Category> categoryList;
    Map<String, Fragment> fragmentList;

    public ViewPagerAdapterMainFragment(@NonNull FragmentManager fragmentManager,
                                        @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
        categoryList = Util.getCategoryList();
        fragmentList = createFragments(categoryList);

    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragmentList.get(categoryList.get(categoryList.keySet().toArray()[position]).getName());
    }



    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    private Map<String, Fragment> createFragments(HashMap<String, Category> categories){
        Map<String, Fragment> fragmentsList = new HashMap<>();

            fragmentsList.put(categories.get(categories.keySet().toArray()[0]).getName(), new ViewPagerFragmentBusiness());
            fragmentsList.put(categories.get(categories.keySet().toArray()[1]).getName(), new ViewPagerFragmentEntertainment());
            fragmentsList.put(categories.get(categories.keySet().toArray()[2]).getName(), new ViewPagerFragmentEnvironment());
            fragmentsList.put(categories.get(categories.keySet().toArray()[3]).getName(), new ViewPagerFragmentFood());
            fragmentsList.put(categories.get(categories.keySet().toArray()[4]).getName(), new ViewPagerFragmentHealth());
            fragmentsList.put(categories.get(categories.keySet().toArray()[5]).getName(), new ViewPagerFragmentPolitics());
            fragmentsList.put(categories.get(categories.keySet().toArray()[6]).getName(), new ViewPagerFragmentScience());
            fragmentsList.put(categories.get(categories.keySet().toArray()[7]).getName(), new ViewPagerFragmentSports());
            fragmentsList.put(categories.get(categories.keySet().toArray()[8]).getName(), new ViewPagerFragmentTechnology());
            fragmentsList.put(categories.get(categories.keySet().toArray()[9]).getName(), new ViewPagerFragmentTop());
            fragmentsList.put(categories.get(categories.keySet().toArray()[10]).getName(), new ViewPagerFragmentWorld());

            return fragmentsList;
    }

    public void updateNewsArticlesList(List<NewsArticle> newsArticles){

    }

}
