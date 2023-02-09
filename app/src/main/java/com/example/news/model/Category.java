package com.example.news.model;

import org.json.JSONArray;
import org.json.JSONException;
import java.util.ArrayList;
import java.util.List;

public class Category {

   private String name;

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   static Category fill(String string){
      Category entity = new Category();
      entity.setName(string);
      return entity;
   }

   static List<Category> fillList(JSONArray jsonArray) throws JSONException {
      if(jsonArray == null || jsonArray.length() == 0){
         return null;
      }
      List<Category> olist = new ArrayList<>();
      for (int i = 0; i < jsonArray.length(); i++) {
         olist.add(fill(jsonArray.getString(i)));
      }
      return olist;
   }
}
