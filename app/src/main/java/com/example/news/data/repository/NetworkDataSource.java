package com.example.news.data.repository;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.news.controller.VolleyClient;
import com.example.news.model.StatusList;
import com.example.news.model.data.ApiDataSource;
import com.example.news.model.data.DataCallback;
import com.example.news.model.data.StatusCallback;

import org.json.JSONObject;

public class NetworkDataSource implements ApiDataSource {

    private String TAG = this.getClass().getSimpleName();

    protected NetworkDataSource() {
    }

    @Override
    public void getNewsArticleFromApi(String url, DataCallback<JSONObject> dataCallback,
                                      StatusCallback statusCallback) {

        Response.Listener<JSONObject> successListener = (JSONObject response) ->{
            statusCallback.onStatus(StatusList.Got_Data);
            dataCallback.onSuccess(response);
        };

        Response.ErrorListener errorListener = (VolleyError error)->{
            statusCallback.onStatus(StatusList.Error);
                dataCallback.onFailure(error.toString());
            };


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                url,
                null,
                successListener,
                errorListener
        ){
            @Override
            protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
                dataCallback.responseCode(response.statusCode);
                return super.parseNetworkResponse(response);
            }
        };

        VolleyClient.getInstance().addToRequestQueue(jsonObjectRequest);

    }
}
