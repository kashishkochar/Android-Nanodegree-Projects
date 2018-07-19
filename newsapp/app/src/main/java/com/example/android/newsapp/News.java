package com.example.android.newsapp;

public class News {
    private String mHeading;
    private String mDetails;
    private String murl;
    private String mName;
    public News(String heading,String details,String url,String name){
        mHeading =heading;
        mDetails =details;
        murl=url;
        mName= name;
    }
    public String getDetails(){

        return mDetails;
    }
    public String getHeading(){

        return mHeading;
    }
    public String geturl(){
        return murl;
    }
    public String getName(){
        return mName;
    }
}
