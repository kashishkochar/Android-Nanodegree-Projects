package com.example.android.newsapp;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

public class NewsLoader extends AsyncTaskLoader<List<News>> {
    String murl;
    public NewsLoader(Context context,String url){
        super(context);
        murl=url;
    }
    @Override
    protected void onStartLoading(){

        forceLoad();
    }
    @Override
    public List<News> loadInBackground() {
        if(murl == null){
            return null;
        }
        List<News> news=NewsJson.fetchNewsData(murl);
        return news;
    }
}
