package com.example.news.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

class NewsSource {

    private String id;
    private String name;
    private String url;
    private List<Category> categories;
    private List<Language> languages;
    private List<Country> countries;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(List<Language> languages) {
        this.languages = languages;
    }

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }

    static NewsSource fill(JSONObject jsonObject) throws JSONException {
        NewsSource entity = new NewsSource();

        if(jsonObject.has("id")){
            entity.setId(jsonObject.getString("id"));
        }
        if(jsonObject.has("name")){
            entity.setName(jsonObject.getString("name"));
        }
        if(jsonObject.has("url")){
            entity.setUrl(jsonObject.getString("url"));
        }
        if(jsonObject.has("category")){
            entity.setCategories(Category.fillList(jsonObject.getJSONArray("category")));
        }
        if(jsonObject.has("language")){
            entity.setLanguages(Language.fillList(jsonObject.getJSONArray("language")));
        }
        if(jsonObject.has("country")){
            entity.setCountries(Country.fillList(jsonObject.getJSONArray("country")));
        }
        return entity;
    }

    static List<NewsSource> fillList(JSONArray jsonArray) throws JSONException{
        if(jsonArray == null || jsonArray.length() == 0){
            return null;
        }
        List<NewsSource> olist = new ArrayList<>();
        for (int i = 0; i <jsonArray.length(); i++) {
            olist.add(fill(jsonArray.getJSONObject(i)));
        }
        return olist;
    }
}
