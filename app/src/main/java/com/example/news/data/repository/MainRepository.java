package com.example.news.data.repository;

import android.os.AsyncTask;
import android.util.Log;

import com.example.news.model.Category;
import com.example.news.model.LanguageList;
import com.example.news.model.NewsArticle;
import com.example.news.model.StatusList;
import com.example.news.model.data.ApiNextPageString;
import com.example.news.model.data.DataCallback;
import com.example.news.model.data.StatusCallback;
import com.example.news.util.Util;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainRepository {

    private String TAG = this.getClass().getSimpleName();

    private static MainRepository instance;
    NetworkDataSource networkDataSource;
    LocalDataSource localDataSource;
    private DataCallback<List<NewsArticle>> callback;

    private MainRepository(){
        networkDataSource = new NetworkDataSource();
        localDataSource = new LocalDataSource();
    }

    public static MainRepository getInstance(){
        if(instance == null)
            return instance = new MainRepository();

        return instance;
    }

    public void getData(Category category, DataCallback<List<NewsArticle>> dataCallback,
                        StatusCallback statusCallback, ApiNextPageString apiNextPageString, String page){
        checkDataAvailability(category, dataCallback, statusCallback, apiNextPageString, page);
    }

    private void checkDataAvailability(Category category, DataCallback<List<NewsArticle>> dataCallback,
                                       StatusCallback statusCallback, ApiNextPageString apiNextPageString, String page){
        callback = dataCallback;
        if(isAvailableOffline()){

        }else{
            getDataFromApi(category, new DataCallback<JSONObject>() {
                @Override
                public void onSuccess(JSONObject jsonObject) {
                    Log.d(TAG, "onSuccess: "+jsonObject.toString());
                    try {
                        String nextPage = jsonObject.getString("nextPage");
                        apiNextPageString.onString(nextPage);
                        Log.d(TAG, "onSuccess: "+ nextPage);
                        statusCallback.onStatus(StatusList.Sorting_Data);
                        mapJsonData(jsonObject);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        onFailure(e.toString());
                    }
                }

                @Override
                public void onFailure(String error) {
                    dataCallback.onFailure(error);
                }

                @Override
                public void responseCode(int code) {
                    Log.d(TAG, "responseCode: is "+ code + Util.responseCode(code));
                }
            }, statusCallback, page);
        }
    }

    private boolean isAvailableOffline(){
        return false;
    }

    private void getDataFromApi(Category category, DataCallback<JSONObject> dataCallback,
                                StatusCallback statusCallback, String page){
        networkDataSource.getNewsArticleFromApi(
                Util.getApiUrl(
                        Util.defaultQuerryType,
                        Util.defaultCountry,
                        category,
                        LanguageList.en,
                        page
                ),
                dataCallback,
                statusCallback
        );
    }
    private void mapJsonData(JSONArray jsonArray){
        Log.d(TAG, "mapJsonData: jsonArray "+ jsonArray.length());
    }

    private void mapJsonData(JSONObject jsonObject) throws JSONException {
        RepositoryAsyncTask task = new RepositoryAsyncTask();
        task.execute(jsonObject);
    }

    private class RepositoryAsyncTask extends AsyncTask<JSONObject, String , List<NewsArticle>>{

        @Override
        protected List<NewsArticle> doInBackground(JSONObject... jsonObjects) {
            Log.d(TAG, "doInBackground: started");
            List<NewsArticle> newsArticles = new ArrayList<>();
            try {
                newsArticles =  NewsArticle.fillList(jsonObjects[0].getJSONArray("results"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return newsArticles;
        }

        @Override
        protected void onPostExecute(List<NewsArticle> newsArticles) {
            callback.onSuccess(newsArticles);
        }
    }
}


