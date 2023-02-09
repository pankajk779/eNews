package com.example.news.model;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

class Keyword {

    private String keyword;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    static Keyword fill(String string){
        Keyword keyword = new Keyword();
        keyword.setKeyword(string);
        return keyword;
    }

    static List<Keyword> fillList(JSONArray jsonArray) throws JSONException {
        if(jsonArray == null || jsonArray.length() == 0){
            return null;
        }
        List<Keyword> olist = new ArrayList<>();
        for (int i = 0; i <jsonArray.length(); i++) {
            olist.add(fill(jsonArray.getString(i)));
        }
        return olist;
    }
}
