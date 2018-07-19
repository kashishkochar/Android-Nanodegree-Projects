package com.example.ayaan.booklisting;

/**
 * Created by AYAAN on 4/9/2017.
 */

public class Book {
    private String mBookname;
    private String mAuthor;
    private String mUrl;
    public Book(String bookname,String author,String url){
        mAuthor = author;
        mBookname = bookname;
        mUrl = url;
    }
    public String getBookname(){
        return mBookname;
    }
    public String getAuthor(){
        return mAuthor;
    }
    public String getUrl(){
        return mUrl;
    }
}
