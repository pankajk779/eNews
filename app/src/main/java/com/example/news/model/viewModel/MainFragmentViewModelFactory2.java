package com.example.news.model.viewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.viewmodel.CreationExtras;

public class MainFragmentViewModelFactory2 implements ViewModelProvider.Factory {
    private String test;

    public MainFragmentViewModelFactory2(String test) {
        this.test = test;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass, @NonNull CreationExtras extras) {
        return ViewModelProvider.Factory.super.create(modelClass, extras);
    }
}
