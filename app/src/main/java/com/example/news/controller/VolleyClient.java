package com.example.news.controller;

import android.content.Context;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public final class VolleyClient {

    private static VolleyClient instance;
    private RequestQueue requestQueue;

   private VolleyClient(){

   }

   public static VolleyClient getInstance(){
       if(instance == null)
           return instance = new VolleyClient();

       return instance;
   }

//   to be called from application class
   public void createRequestQueue(Context context){
       if(requestQueue == null)
       requestQueue = Volley.newRequestQueue(context.getApplicationContext());
   }

   public RequestQueue getRequestQueue(){
       return requestQueue;
   }

   public <T> void addToRequestQueue(Request<T> req){
       getRequestQueue().add(req);
   }

}
