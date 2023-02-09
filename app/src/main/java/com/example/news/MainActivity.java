package com.example.news;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.news.fragments.MainFragment;
import com.example.news.fragments.TestFragment;
import com.example.news.model.Category;
import com.example.news.model.CountryList;
import com.example.news.model.LanguageList;
import com.example.news.model.QueryType;
import com.example.news.util.Util;

public class MainActivity extends AppCompatActivity {

    Fragment mainFragment;
    Fragment testFragment;
    private QueryType userQuerryType;
    private CountryList userCountry;
    private LanguageList userLanguage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        WindowCompat.setDecorFitsSystemWindows(getWindow(),false);
//        getWindow().setStatusBarColor(Color.BLACK);
//        getWindow().setNavigationBarColor(Color.WHITE);
//        getWindow().getDecorView().setSystemUiVisibility(
//                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
//            Window window = getWindow();
//            window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
//                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
//            window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION,
//                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//
//        }

        loadMainFragment();
    }

    private void loadMainFragment(){
        mainFragment = MainFragment.newInstance();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container_main_activity,mainFragment)
                .commit();
    }

    private void loadTestFragment(){
        testFragment = TestFragment.newInstance();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container_main_activity, testFragment)
                .commit();
    }

    private void setUrlParameteres(QueryType userQuerryType, CountryList userCountry,
                                   Category userCategory, LanguageList userLanguage){

    }

    private void setUrlParameteres(){

    }

    public QueryType getUserQuerryType() {
        return userQuerryType;
    }

    public CountryList getUserCountry() {
        return userCountry;
    }

    public LanguageList getUserLanguage() {
        return userLanguage;
    }
}