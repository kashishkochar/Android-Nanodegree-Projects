package com.example.ayaan.booklisting;

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

import org.w3c.dom.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static android.R.attr.data;

public class BookActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Book>> {
    //private String enteredtext;
    private EditText enteredtext;
    private Button search;
    private String text;
    public String book_url;
    public BookAdapter booklist;
    private ProgressBar progressBar;
    private ListView listView;
    private TextView empty;
     TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        enteredtext = (EditText)findViewById(R.id.enteredtext);
        search = (Button)findViewById(R.id.search);
        text = enteredtext.getText().toString();
        progressBar = (ProgressBar)findViewById(R.id.progress);
       textView = (TextView)findViewById(R.id.internet);
        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        final LoaderManager loaderManager = getLoaderManager();
        empty = (TextView)findViewById(R.id.empty);
        empty.setVisibility(View.GONE);
        if(networkInfo!= null && networkInfo.isConnected()){
                    //Log.e("")
                    loaderManager.initLoader(1,null,BookActivity.this);
                    //loaderManager.restartLoader(0,null,BookActivity.this);
                    Log.e("Netwok","Connected");
            textView.setVisibility(View.GONE);}

        else {
            textView.setVisibility(View.VISIBLE);
        }
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if(text.isEmpty()){
//                    enteredtext.setError("Empty");
//                }
//                else {

                progressBar.setVisibility(View.VISIBLE);
                String urltosearch =enteredtext.getText().toString().trim();

                    book_url = "https://www.googleapis.com/books/v1/volumes?q="+urltosearch;
                if(networkInfo!=null && networkInfo.isConnected()){textView.setVisibility(View.GONE);}
                Log.v("url:", book_url);
                loaderManager.restartLoader(1,null, BookActivity.this);

            }
        });
        ListView books = (ListView)findViewById(R.id.list);
        booklist = new BookAdapter(this,new ArrayList<Book>());
        books.setAdapter(booklist);

    }



    @Override
    public Loader<List<Book>> onCreateLoader(int id, Bundle args) {
        Log.v("here:", "hi");
//        textView.setVisibility(View.GONE);
        if(booklist!=null)
        {
            booklist.clear();

        }

        return new BookLoader(this,book_url);
    }

    @Override
    public void onLoadFinished(Loader<List<Book>> loader, List<Book> data) {
        progressBar.setVisibility(View.GONE);
        booklist.clear();
        if (booklist!= null && data!= null){
            booklist.addAll(data);
            textView.setVisibility(View.GONE);
            if(booklist.isEmpty()){
                empty.setVisibility(View.VISIBLE);

            }
        }
        if (booklist==null){

            textView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Book>> loader) {
        booklist.clear();
    }
}
