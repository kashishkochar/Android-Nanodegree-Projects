package com.example.android.booklisting;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.List;

public class BookLoader extends AsyncTaskLoader<List<Book>> {
    private static String Log_tag=BookLoader.class.getSimpleName();
    private String mUrl;
    public BookLoader(Context context,String url){
        super(context);
        mUrl=url;
    }
    @Override
    protected void onStartLoading(){forceLoad();}

    @Override
    public List<Book> loadInBackground() {
        if (mUrl == null) {
            return null;
        }
        //Log.e(mUrl,"hi");
        List<Book> fbook = BookJSON.fetchBookData(mUrl);
        return fbook;
    }
}
