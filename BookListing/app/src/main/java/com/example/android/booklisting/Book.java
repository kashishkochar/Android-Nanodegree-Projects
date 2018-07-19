package com.example.android.booklisting;

public class Book {
    private String mtitle;
    private String mauthor;
    private String murl;

    public Book(String title,String author,String url){
        mauthor=author;
        mtitle=title;
        murl=url;
    }

    public String getMauthor() {
        return mauthor;
    }

    public String getMtitle() {
        return mtitle;
    }

}
