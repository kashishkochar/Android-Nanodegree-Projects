package com.example.ayaan.booklisting;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

/**
 * Created by AYAAN on 4/9/2017.
 */

public class BookLoader extends AsyncTaskLoader<List<Book>> {
    private String mUrl;
    public BookLoader(Context context,String url) {
        super(context);
        mUrl = url;
        Log.e(url,"URL passed");
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<Book> loadInBackground() {
        if (mUrl == null) {
            return null;
        }
        Log.e(mUrl,":Url that is passed into fetchbook");
        List<Book> result = BookJson.fetchBookData(mUrl);
        Log.e("Books Loader","----");
        return result;
    }
}
