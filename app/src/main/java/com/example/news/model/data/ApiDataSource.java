package com.example.news.model.data;

import org.json.JSONObject;

public interface ApiDataSource extends DataSource {

    void getNewsArticleFromApi(String url, DataCallback<JSONObject> dataCallback, StatusCallback statusCallback);

}
