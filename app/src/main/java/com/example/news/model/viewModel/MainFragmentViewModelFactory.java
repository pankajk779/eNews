package com.example.news.model.viewModel;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.viewmodel.CreationExtras;

import com.example.news.data.DBHandler;

public class MainFragmentViewModelFactory implements ViewModelProvider.Factory {
    private DBHandler dbHandler;
    private String test;

    public MainFragmentViewModelFactory(String test){
//        this.dbHandler = dbHandler;
        this.test = test;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass, @NonNull CreationExtras extras) {
        return ViewModelProvider.Factory.super.create(modelClass, extras);
    }
}
