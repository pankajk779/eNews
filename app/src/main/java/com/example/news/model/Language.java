package com.example.news.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

class Language {

   private String name;

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   static Language fill(String name){
      Language entity = new Language();
      entity.setName(name);
      return entity;
   }

   static List<Language> fillList(JSONArray jsonArray) throws JSONException{
      if(jsonArray == null || jsonArray.length() == 0){
         return null;
      }
      List<Language> olist = new ArrayList<>();
      for (int i = 0; i < jsonArray.length(); i++) {
         olist.add(fill(jsonArray.getString(i)));
      }
      return olist;
   }
}
