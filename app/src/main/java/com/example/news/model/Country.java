package com.example.news.model;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

class Country {

   private String name;

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   static Country fill(String string){
      Country entity = new Country();
      entity.setName(string);
      return entity;
   }

   static List<Country> fillList(JSONArray jsonArray) throws JSONException {
      if(jsonArray == null || jsonArray.length() == 0){
         return null;
      }
      List<Country> olist = new ArrayList<>();
      for (int i = 0; i < jsonArray.length(); i++) {
         olist.add(fill(jsonArray.getString(i)));
      }
      return olist;
   }
}
