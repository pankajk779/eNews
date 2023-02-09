package com.example.news.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class NewsArticle implements Parcelable {

    private static final String TAG = "NewsArticle";
    private String title;
    private String link;
    private List<Keyword> keywords;
    private List<MyCreator> creators;
    private String video_url;
    private String description;
    private String content;
    private String pubDate;
    private String image_url;
    private String source_id;
    private List<Country> countries;
    private List<Category> categories;
    private String language;

    protected NewsArticle(Parcel in) {
        title = in.readString();
        link = in.readString();
        video_url = in.readString();
        description = in.readString();
        content = in.readString();
        pubDate = in.readString();
        image_url = in.readString();
        source_id = in.readString();
        language = in.readString();
    }

    public NewsArticle(){

    }

    public static final Creator<NewsArticle> CREATOR = new Creator<NewsArticle>() {
        @Override
        public NewsArticle createFromParcel(Parcel in) {
            return new NewsArticle(in);
        }

        @Override
        public NewsArticle[] newArray(int size) {
            return new NewsArticle[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public List<Keyword> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<Keyword> keywords) {
        this.keywords = keywords;
    }

    public List<MyCreator> getCreator() {
        return creators;
    }

    public void setCreator(List<MyCreator> myCreators) {
        this.creators = myCreators;
    }

    public String getVideo_url() {
        return video_url;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getSource_id() {
        return source_id;
    }

    public void setSource_id(String source_id) {
        this.source_id = source_id;
    }

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public static NewsArticle fill(JSONObject jsonObject) throws JSONException {
        NewsArticle entity = new NewsArticle();

        if(jsonObject.has("title")){
            if(jsonObject.isNull("title")){
                entity.setTitle(null);
            }else{
                entity.setTitle(jsonObject.getString("title"));
            }
        }
        if(jsonObject.has("link")){
            if(jsonObject.isNull("link")){
                entity.setLink(null);
            }else{
                entity.setLink(jsonObject.getString("link"));
            }
        }
        if(jsonObject.has("keywords")){
                if(jsonObject.isNull("keywords")){
                    entity.setKeywords(null);
                }else{
                    entity.setKeywords(Keyword.fillList(jsonObject.getJSONArray("keywords")));
                }
        }
        if(jsonObject.has("creator")){
            if(jsonObject.isNull("creator")){
                entity.setCreator(null);
            }else{
                entity.setCreator(MyCreator.fillList(jsonObject.getJSONArray("creator")));
            }
        }
        if(jsonObject.has("video_url")){
            if(jsonObject.isNull("video_url")){
                entity.setVideo_url(null);
            }else{
                entity.setVideo_url(jsonObject.getString("video_url"));
            }
        }
        if(jsonObject.has("description")){
            if(jsonObject.isNull("description")){
                entity.setDescription(null);
            }else{
                entity.setDescription(jsonObject.getString("description"));
            }
        }
        if(jsonObject.has("content")){
            if(jsonObject.isNull("content")){
                entity.setContent(null);
            }else{
                entity.setContent(jsonObject.getString("content"));
            }
        }
        if(jsonObject.has("pubDate")){
            if(jsonObject.isNull("pubDate")){
                entity.setPubDate(null);
            }else{
                entity.setPubDate(jsonObject.getString("pubDate"));
            }
        }
        if(jsonObject.has("image_url")){
            if(jsonObject.isNull("image_url")){
                entity.setImage_url(null);
            }else{
                entity.setImage_url(jsonObject.getString("image_url"));
            }
        }
        if(jsonObject.has("source_id")){
            if(jsonObject.isNull("source_id")){
                entity.setSource_id(null);
            }else{
                entity.setSource_id(jsonObject.getString("source_id"));
            }
        }
        if(jsonObject.has("country")){
            if(jsonObject.isNull("country")){
                entity.setCountries(null);
            }else{
                entity.setCountries(Country.fillList(jsonObject.getJSONArray("country")));
            }
        }
        if(jsonObject.has("category")){
            if(jsonObject.isNull("category")){
                entity.setCategories(null);
            }else{
                entity.setCategories(Category.fillList(jsonObject.getJSONArray("category")));
            }
        }
        if(jsonObject.has("language")){
            if(jsonObject.isNull("language")){
                entity.setLanguage(null);
            }else{
                entity.setLanguage(jsonObject.getString("language"));
            }
        }
        return entity;
    }

    public static List<NewsArticle> fillList(JSONArray jsonArray) throws JSONException{
        if(jsonArray == null || jsonArray.length() == 0){
            return null;
        }
        List<NewsArticle> olist = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            NewsArticle newsArticle = fill(jsonArray.getJSONObject(i));
            olist.add(newsArticle);
        }
        return olist;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(link);
        dest.writeString(video_url);
        dest.writeString(description);
        dest.writeString(content);
        dest.writeString(pubDate);
        dest.writeString(image_url);
        dest.writeString(source_id);
        dest.writeString(language);
    }
}
