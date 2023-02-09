package com.example.news.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.news.model.NewsArticle;

import java.util.List;

public class DBHandler extends SQLiteOpenHelper {

    private static final  String DB_NAME = "newsArticleDb";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "newsArticles";
    private static final String ID_COL = "id";
    private static final String TITLE_COL = "name";
    private static final String DESCRIPTION_COL = "duration";
    private final String TAG = this.getClass().getSimpleName();

    public DBHandler(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String querry = "create table "+ TABLE_NAME + "("
                + ID_COL + " INTEGER primary key autoincrement, "
                + TITLE_COL + " TEXT, "
                + DESCRIPTION_COL + " TEXT)";

        db.execSQL(querry);
    }

    public void saveNewsArticle(List<NewsArticle> newsArticles){
        SQLiteDatabase db = this.getWritableDatabase();

        for (int i = 0; i < newsArticles.size(); i++) {
            NewsArticle newsArticle = newsArticles.get(i);
            ContentValues values = new ContentValues();
            values.put(TITLE_COL, newsArticle.getTitle());
            values.put(DESCRIPTION_COL, newsArticle.getDescription());

            db.insert(TABLE_NAME, null, values);
        }

        Log.d(TAG, "saveNewsArticle: database updated..!");
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+ TABLE_NAME);
        onCreate(db);
    }
}
