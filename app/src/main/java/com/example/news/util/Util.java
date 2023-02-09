package com.example.news.util;

import android.util.Log;

import com.example.news.model.Category;
import com.example.news.model.CountryList;
import com.example.news.model.LanguageList;
import com.example.news.model.NewsArticle;
import com.example.news.model.QueryType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Util {

    private static final String TAG = "Util";
    private static String customAppTag = "appTag";
    private static Toggle toggle = Toggle.DISABLE;
    private static HashMap<String, Category> categoriesObjects;

    public static CountryList defaultCountry = CountryList.IN;
    public static QueryType defaultQuerryType = QueryType.news;
    public static LanguageList defaultLanguage = LanguageList.en;
    public static CategoryList defaultCategory = CategoryList.business;

    public static List<NewsArticle> newsArticles = new ArrayList<>();

   //newsdata.io
    private static String basicUrl = "https://newsdata.io/api/1/";
    private static String apiKeyAlpine = "pub_150506865eef8b81cd7d00f7507dc814e3844";
    private static String apiKeyTube = "pub_167371c4dc5d393f141b1e1e27e2e3855782b";
    private static String apiKeyKing = "pub_16757d2db4068f8a6eb75a22e83224a27302e";
//    link
    public static String fullLink = "https://newsdata.io/api/1/news?apikey=pub_150506865eef8b81cd7d00f7507dc814e3844&q=sports";

//    dummy api url
    public static String dummyLink = "https://developer.nps.gov/api/v1/parks?parkCode=acad&api_key=sbqXigbqjbO6kcHGdJ8voIDKkeE6KEUUkA82TrAB";

    //create url with request parameters
    public static String getApiUrl(QueryType queryType,
                                   CountryList country,
                                   Category category,
                                   LanguageList language,
                                   String page
                                   ){

        if(page == null){
            String url = basicUrl + queryType + "?apikey=" + apiKeyKing +
                    "&language=" + language +
                    "&country=" + country +
                    "&category=" + category.getName();
            Log.d(TAG, "getApiUrl: "+ url);
            return url;
        }else {
            String url = basicUrl + queryType + "?apikey=" + apiKeyKing +
                    "&language=" + language +
                    "&country=" + country +
                    "&category=" + category.getName() +
                    "&page="+ page;
            Log.d(TAG, "getApiUrl: "+ url);
            return url;
        }

//        String dummyUrl = "";

    }

    public static void enableToggle(){
        toggle = Toggle.ENABLE;
    }

    public static Toggle getToggle(){
        return toggle;
    }

    // to enable global TAG for log-cat
    public static String myCustomTag(String className){
        if(toggle == Toggle.ENABLE){
            return customAppTag;
        }
        return className;
    }

    public static HashMap<String, Category> getCategoryList(){

        if(categoriesObjects == null || categoriesObjects.size() == 0){

            HashMap<String, Category> list = new HashMap<>();
            for (CategoryList c : CategoryList.values()) {
                Category category = new Category();
                category.setName(c.toString());
                list.put(c.toString(), category);
            }
            categoriesObjects = list;
        }
        return categoriesObjects;
    }

    public static String responseCode(int code){
        String responseCode = null;
        switch (code) {
            case 200 : responseCode = "Successful operation";
            break;
            case 400 : responseCode = "Parameter missing";
            break;
            case 401 : responseCode = "Unauthorized";
            break;
            case 403 : responseCode = "CORS policy failed. IP/Domain restricted";
                break;
            case 409 : responseCode = "Parameter duplicate";
                break;
            case 415 : responseCode = "Unsupported type";
                break;
            case 422 : responseCode = "Unprocessable entity";
                break;
            case 429 : responseCode = "Too many requests";
                break;
            case 500 : responseCode = "Internal server error";
                break;
            default: responseCode = null;
        }
        return responseCode;
    }
}


