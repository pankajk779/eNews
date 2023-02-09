package com.example.news;

import com.example.news.data.DBHandler;

public class DBTransfer {
   private DBHandler dbHandler;
   private static DBTransfer instance;

   private DBTransfer(){
   }

   public static DBTransfer getInstance(){
      if(instance == null){
         instance = new DBTransfer();
         return instance;
      }
      return instance;
   }
   public void setDbHandler(DBHandler dbHandler){
      this.dbHandler = dbHandler;
   }

   public DBHandler getDbHandler(){
      return dbHandler;
   }
}
