package com.example.android.booklisting;

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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class BookActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Book>> {
    public String text;
    public String USGS_STRING_URL;
    public static int LOADER_ID = 1;
    private BookAdapter mAdapter;
    TextView textView;
    TextView emptyView;
    EditText enteredText;
    ProgressBar progressBar;
    ConnectivityManager connMgr;
    NetworkInfo networkInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        enteredText=(EditText)findViewById(R.id.editText);
        Button search = (Button) findViewById(R.id.searchButton);
        text = enteredText.getText().toString();
        progressBar = (ProgressBar) findViewById(R.id.loading_indicator);
        textView = (TextView) findViewById(R.id.internet);
        emptyView = (TextView) findViewById(R.id.empty_view);
        emptyView.setVisibility(View.GONE);
        mAdapter = new BookAdapter(this,new ArrayList<Book>());


    connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
    networkInfo = connMgr.getActiveNetworkInfo();
    emptyView.setVisibility(View.GONE);
        final LoaderManager loaderManager = getLoaderManager();
    if(networkInfo !=null && networkInfo.isConnected()) {
        loaderManager.initLoader(LOADER_ID, null, this);
        textView.setVisibility(View.GONE);
    }
    else {
        //Log.e(String.valueOf(networkInfo),"no internet");
        textView.setVisibility(View.VISIBLE);
    }
    search.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            progressBar.setVisibility(View.VISIBLE);
            emptyView.setVisibility(View.GONE);
            mAdapter.clear();
           String url_to_search=enteredText.getText().toString().trim();
           USGS_STRING_URL = "https://www.googleapis.com/books/v1/volumes?q="+url_to_search;
            connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            networkInfo = connMgr.getActiveNetworkInfo();
            if(networkInfo!=null && networkInfo.isConnected()) {
                //Toast.makeText(BookActivity.this, "Hi", Toast.LENGTH_SHORT).show();
                textView.setVisibility(View.GONE);
                emptyView.setVisibility(View.GONE);
                //Log.e(String.valueOf(networkInfo),"no connection break");
                loaderManager.restartLoader(LOADER_ID,null,BookActivity.this);
            }
            else{
                //Toast.makeText(BookActivity.this, "No internet", Toast.LENGTH_SHORT).show();
                textView.setVisibility(View.VISIBLE);
                emptyView.setVisibility(View.GONE);
                //Log.e(String.valueOf(networkInfo),"on search connection breaks");
            }

            }

    });
    ListView bookk = (ListView)findViewById(R.id.text);
    bookk.setAdapter(mAdapter);
}
    @Override
    public Loader<List<Book>> onCreateLoader(int i,Bundle bundle){
        if(mAdapter!=null){
            mAdapter.clear();
        }
        return new BookLoader(this,USGS_STRING_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<Book>> loader, List<Book> book1) {
        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);
        mAdapter.clear();
        if (mAdapter!= null && book1!=null) {
            mAdapter.addAll(book1);
            textView.setVisibility(View.GONE);
            if(mAdapter.isEmpty()){
                emptyView.setVisibility(View.VISIBLE);
            }
        }

       /* if(book1==null){
            //Toast.makeText(this, "Empty", Toast.LENGTH_SHORT).show();
            emptyView.setVisibility(View.VISIBLE);
        }*/
        if(mAdapter==null)
        {
            emptyView.setVisibility(View.VISIBLE);
        }
    }
    @Override
    public void onLoaderReset(Loader<List<Book>> loader){
        mAdapter.clear();
        }
}
