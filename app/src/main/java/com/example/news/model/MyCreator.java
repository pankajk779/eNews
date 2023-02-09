package com.example.news.model;

import org.json.JSONArray;
import org.json.JSONException;
import java.util.ArrayList;
import java.util.List;

class MyCreator {

   private String creator;

   public String getCreator() {
      return creator;
   }

   public void setCreator(String creator) {
      this.creator = creator;
   }

   static MyCreator fill(String creator) throws JSONException {
      MyCreator entity = new MyCreator();
         entity.setCreator(creator);
         return entity;
      }

   static List<MyCreator> fillList(JSONArray jsonArray) throws JSONException{
      if(jsonArray == null || jsonArray.length() == 0){
         return null;
      }
      List<MyCreator> olist = new ArrayList<>();
      for (int i = 0; i < jsonArray.length(); i++) {
         olist.add(fill(jsonArray.getString(i)));
      }
      return olist;
   }

}
