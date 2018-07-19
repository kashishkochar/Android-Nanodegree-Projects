package com.example.android.newsapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class NewsAdapter extends ArrayAdapter<News> {
    public NewsAdapter(Activity context, ArrayList<News>news){

        super(context,0,news);
    }
    public View getView(int pos, View currentView, ViewGroup parent){
        if(currentView==null){
            currentView=LayoutInflater.from(getContext()).inflate(R.layout.list,parent,false);
        }
         final News news=getItem(pos);

         final TextView heading = (TextView)currentView.findViewById(R.id.heading);
        heading.setText(news.getHeading());
        final TextView details =(TextView)currentView.findViewById(R.id.details);
        details.setText(news.getDetails());
        final TextView name=(TextView)currentView.findViewById(R.id.name);
        name.setText(news.getName());

        try{
            URL url =new URL(news.geturl());
        }
        catch (MalformedURLException e){

        }
        currentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(String.valueOf(heading),"url clicked");
                Intent intent=new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(news.geturl()));
                v.getContext().startActivity(intent);
            }
        });
        return currentView;
    }
}
