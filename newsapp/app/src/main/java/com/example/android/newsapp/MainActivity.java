package com.example.android.newsapp;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<News>> {
    private static int LOADER_ID =1;
    EditText enteredtext;
    String text;
    Button search;
    NewsAdapter mAdapter = null;
    ProgressBar progressBar;
    NetworkInfo networkInfo;
    ConnectivityManager connectivityManager;
    TextView emptyView;
    TextView noInternet;
    private String urlToSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        enteredtext = (EditText)findViewById(R.id.edit_text);
        search= (Button)findViewById(R.id.button);
        text =enteredtext.getText().toString();
        progressBar=(ProgressBar)findViewById(R.id.loading_Indicator);
        noInternet=(TextView)findViewById(R.id.no_Internet_view);
        emptyView=(TextView)findViewById(R.id.emptyView);
        emptyView.setVisibility(View.GONE);
        //progressBar.setVisibility(View.VISIBLE);
        mAdapter= new NewsAdapter(this,new ArrayList<News>());

        connectivityManager =(ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        //if(connectivityManager!=null)
        networkInfo=connectivityManager.getActiveNetworkInfo();
        emptyView.setVisibility(View.GONE);
        final LoaderManager loaderManager=getLoaderManager();
        if(networkInfo!=null && networkInfo.isConnected()) {
            loaderManager.initLoader(LOADER_ID, null, this);
            noInternet.setVisibility(View.GONE);
        }
        else{
            emptyView.setVisibility(View.GONE);
            noInternet.setVisibility(View.VISIBLE);
        }
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(String.valueOf(loaderManager),"inside on click");
                progressBar.setVisibility(View.VISIBLE);
                emptyView.setVisibility(View.GONE);
                mAdapter.clear();
                String str = enteredtext.getText().toString().trim();
                urlToSearch = "https://content.guardianapis.com/search?"+str+"&show-tags=contributor&api-key=7ab0fc3e-ae04-4710-8c0b-7c8ebb871588";
                connectivityManager=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
                networkInfo=connectivityManager.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.isConnected()) {
                    noInternet.setVisibility(View.GONE);
                    loaderManager.restartLoader(LOADER_ID, null, MainActivity.this);
                }
                else {
                    progressBar.setVisibility(View.VISIBLE);
                    noInternet.setVisibility(View.VISIBLE);
                }
            }
        });
        //listview
        ListView newsss =(ListView)findViewById(R.id.list_main);

        newsss.setAdapter(mAdapter);
}
    public Loader<List<News>> onCreateLoader(int id, Bundle bundle){
        if(mAdapter!=null){
            mAdapter.clear();
        }
        return new NewsLoader(this,urlToSearch);
    }

    @Override
    public void onLoadFinished(Loader<List<News>>loader,List<News>news){
        progressBar.setVisibility(View.GONE);
        mAdapter.clear();
        if(mAdapter !=null && news!=null){
            mAdapter.addAll(news);
            if(mAdapter.isEmpty()){
                emptyView.setVisibility(View.VISIBLE);
            }
            else {
                emptyView.setVisibility(View.GONE);
            }
        }
    }
    @Override
    public void onLoaderReset(Loader<List<News>>loader){
        mAdapter.clear();
    }
}
