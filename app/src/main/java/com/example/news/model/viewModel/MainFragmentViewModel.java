package com.example.news.model.viewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.news.DBTransfer;
import com.example.news.data.DBHandler;
import com.example.news.data.repository.MainRepository;
import com.example.news.model.Category;
import com.example.news.model.NewsArticle;
import com.example.news.model.StatusList;
import com.example.news.model.data.ApiNextPageString;
import com.example.news.model.data.DataCallback;
import com.example.news.model.data.ErrorCallback;
import com.example.news.model.data.StatusCallback;
import com.example.news.util.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainFragmentViewModel extends ViewModel {

    private final String TAG = this.getClass().getSimpleName();
    private MainRepository mainRepository;
    private DBHandler dbHandler;

    private MutableLiveData<NewsArticle> _selectedItem = new MutableLiveData<>();
    public LiveData<NewsArticle> selectedItem = _selectedItem;

    private MutableLiveData<List<NewsArticle>> _businessNewsArticles = new MutableLiveData<>(null);
    public LiveData<List<NewsArticle>> businessNewsArticles = _businessNewsArticles;

    private MutableLiveData<List<NewsArticle>> _entertainmentNewsArticles = new MutableLiveData<>();
    public LiveData<List<NewsArticle>> entertainmentNewsArticles = _entertainmentNewsArticles;

    private MutableLiveData<List<NewsArticle>> _environmentNewsArticles = new MutableLiveData<>();
    public LiveData<List<NewsArticle>> environmentNewsArticles = _environmentNewsArticles;

    private MutableLiveData<List<NewsArticle>> _foodNewsArticles = new MutableLiveData<>();
    public LiveData<List<NewsArticle>> foodNewsArticles = _foodNewsArticles;

    private MutableLiveData<List<NewsArticle>> _healthNewsArticles = new MutableLiveData<>();
    public LiveData<List<NewsArticle>> healthNewsArticles = _healthNewsArticles;

    private MutableLiveData<List<NewsArticle>> _politicsNewsArticles = new MutableLiveData<>();
    public LiveData<List<NewsArticle>> politicsNewsArticles = _politicsNewsArticles;

    private MutableLiveData<List<NewsArticle>> _scienceNewsArticles = new MutableLiveData<>();
    public LiveData<List<NewsArticle>> scienceNewsArticles = _scienceNewsArticles;

    private MutableLiveData<List<NewsArticle>> _sportsNewsArticles = new MutableLiveData<>();
    public LiveData<List<NewsArticle>> sportsNewsArticles = _sportsNewsArticles;

    private MutableLiveData<List<NewsArticle>> _technologyNewsArticles = new MutableLiveData<>();
    public LiveData<List<NewsArticle>> technologyNewsArticles = _technologyNewsArticles;

    private MutableLiveData<List<NewsArticle>> _topNewsArticles = new MutableLiveData<>();
    public LiveData<List<NewsArticle>> topNewsArticles = _topNewsArticles;

    private MutableLiveData<List<NewsArticle>> _worldNewsArticles = new MutableLiveData<>();
    public LiveData<List<NewsArticle>> worldNewsArticles = _worldNewsArticles;

    public static MutableLiveData<List<NewsArticle>> _newsArticles = new MutableLiveData<>(new ArrayList<>(0));
    public LiveData<List<NewsArticle>> newsArticles = _newsArticles;


    public MainFragmentViewModel() {
        mainRepository = MainRepository.getInstance();

        dbHandler = DBTransfer.getInstance().getDbHandler();
        if(dbHandler == null)
            Log.d(TAG, "MainFragmentViewModel: null");
        else Log.d(TAG, "MainFragmentViewModel: not null");
//        dbHandler = dbHandler;

    }

    public void requestNewsList(Category category, StatusCallback statusCallback, ApiNextPageString apiNextPageString, String page, ErrorCallback errorCallback){
        Log.d(TAG, "requestNewsList: making dataRequest from api with category "+ category.getName());
        mainRepository.getData(category,
                new DataCallback<List<NewsArticle>>() {
                    @Override
                    public void onSuccess(List<NewsArticle> list) {
                        if (list != null) {
                            Log.d(TAG, "onSuccess: got data with size " + list.size());
                            switch (category.getName()) {
                                case "business":
                                    if(_businessNewsArticles.getValue() == null){
                                        _businessNewsArticles.postValue(list);
                                    }else{
                                        List<NewsArticle> l = _businessNewsArticles.getValue();
                                        for (int i = 0; i < list.size(); i++) {
                                            l.add(i, list.get(i));
                                        }
                                        _businessNewsArticles.postValue(l);
                                    }
                                    break;

                                case "entertainment":
                                    if(_entertainmentNewsArticles.getValue() == null){
                                        _entertainmentNewsArticles.postValue(list);
                                    }else{
                                        List<NewsArticle> l1 = _entertainmentNewsArticles.getValue();
                                        for (int i = 0; i < list.size(); i++) {
                                            l1.add(i, list.get(i));
                                        }
                                        _entertainmentNewsArticles.postValue(l1);
                                    }

                                    break;
                                case "environment":
                                    if(_environmentNewsArticles.getValue() == null){
                                        _environmentNewsArticles.postValue(list);
                                    }else{
                                        List<NewsArticle> l2 = _environmentNewsArticles.getValue();
                                        for (int i = 0; i < list.size(); i++) {
                                            l2.add(i, list.get(i));
                                        }
                                        _environmentNewsArticles.postValue(l2);
                                    }

                                    break;
                                case "food":

                                    if(_foodNewsArticles.getValue() == null){
                                        _foodNewsArticles.postValue(list);
                                    }else{
                                        List<NewsArticle> l3 = _foodNewsArticles.getValue();
                                        for (int i = 0; i < list.size(); i++) {
                                            l3.add(i, list.get(i));
                                        }
                                        _foodNewsArticles.postValue(list);
                                    }

                                    break;
                                case "health":
                                    if(_healthNewsArticles.getValue() == null){
                                        _healthNewsArticles.postValue(list);
                                    }else{
                                        List<NewsArticle> l4 = _healthNewsArticles.getValue();
                                        for (int i = 0; i < list.size(); i++) {
                                            l4.add(i, list.get(i));
                                        }
                                        _healthNewsArticles.postValue(l4);
                                    }

                                    break;
                                case "politics":
                                    if(_politicsNewsArticles.getValue() == null){
                                        _politicsNewsArticles.postValue(list);
                                    }else{
                                        List<NewsArticle> l5 = _politicsNewsArticles.getValue();
                                        for (int i = 0; i < list.size(); i++) {
                                            l5.add(i, list.get(i));
                                        }
                                        _politicsNewsArticles.postValue(l5);
                                    }

                                    break;
                                case "science":
                                    if(_scienceNewsArticles.getValue() == null){
                                        _scienceNewsArticles.postValue(list);
                                    }else{
                                        List<NewsArticle> l6 = _scienceNewsArticles.getValue();
                                        for (int i = 0; i < list.size(); i++) {
                                            l6.add(i, list.get(i));
                                        }
                                        _scienceNewsArticles.postValue(l6);
                                    }

                                    break;
                                case "sports":
                                    if(_sportsNewsArticles.getValue() == null){
                                        _sportsNewsArticles.postValue(list);
                                    }else{
                                        List<NewsArticle> l7 = _sportsNewsArticles.getValue();
                                        for (int i = 0; i < list.size(); i++) {
                                            l7.add(i, list.get(i));
                                        }
                                        _sportsNewsArticles.postValue(l7);
                                    }

                                    break;
                                case "technology":
                                    if(_technologyNewsArticles.getValue() == null){
                                        _technologyNewsArticles.postValue(list);
                                    }else{
                                        List<NewsArticle> l8 = _technologyNewsArticles.getValue();
                                        for (int i = 0; i < list.size(); i++) {
                                            l8.add(i, list.get(i));
                                        }
                                        _technologyNewsArticles.postValue(l8);
                                    }

                                    break;
                                case "top":
                                    if(_topNewsArticles.getValue() == null){
                                        _topNewsArticles.postValue(list);
                                    }else{
                                        List<NewsArticle> l9 = _topNewsArticles.getValue();
                                        for (int i = 0; i < list.size(); i++) {
                                            l9.add(i, list.get(i));
                                        }
                                        _topNewsArticles.postValue(l9);
                                    }

                                    break;
                                case "world":
                                    if(_worldNewsArticles.getValue() == null){
                                        _worldNewsArticles.postValue(list);
                                    }else{
                                        List<NewsArticle> l10 = _worldNewsArticles.getValue();
                                        for (int i = 0; i < list.size(); i++) {
                                            l10.add(i, list.get(i));
                                        }
                                        _worldNewsArticles.postValue(l10);
                                    }

                                    break;
                            }
                        }
                    }

                    @Override
                    public void onFailure(String error) {
                        errorCallback.onError(error);
                        Log.d(TAG, "onFailure: no data received " + error);
                    }

                    @Override
                    public void responseCode(int code) {
                        Log.d(TAG, "responseCode: " + Util.responseCode(code));
                    }
                }
                , new StatusCallback() {
                    @Override
                    public void onStatus(StatusList statusList) {
                        statusCallback.onStatus(statusList);
                    }
                }, new ApiNextPageString() {
                    @Override
                    public void onString(String s) {
                        apiNextPageString.onString(s);
                    }
                }, page);
    }

    public List<NewsArticle> getNewsList(Category category){
        switch (category.getName()){
            case "business" :   return businessNewsArticles.getValue();

            case "entertainment":   return entertainmentNewsArticles.getValue();

            case "environment": return environmentNewsArticles.getValue();

            case "food":    return foodNewsArticles.getValue();

            case "health":  return healthNewsArticles.getValue();

            case "politics":    return politicsNewsArticles.getValue();

            case "science": return scienceNewsArticles.getValue();

            case "sports":  return sportsNewsArticles.getValue();

            case "technology":  return technologyNewsArticles.getValue();

            case "top": return topNewsArticles.getValue();

            case "world":   return worldNewsArticles.getValue();

            default: return null;
        }
    }
}


