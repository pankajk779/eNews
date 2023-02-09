package com.example.news.model.data;

public interface DataCallback<D> {

    void onSuccess(D d);
    void onFailure(String error);
    void responseCode(int code);
}
