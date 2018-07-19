package com.example.android.booklisting;

import android.content.Context;
import android.text.NoCopySpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class BookAdapter extends ArrayAdapter<Book> {
    public BookAdapter(Context context, ArrayList<Book> books){
        super(context,0,books);
    }
    public View getView(int position, View convertview, ViewGroup parent){

        if(convertview==null){
            convertview = LayoutInflater.from(getContext()).inflate(R.layout.item_view,parent,false);
        }
        Book book = getItem(position);

        TextView bookName = (TextView) convertview.findViewById(R.id.name);
        bookName.setText(book.getMtitle());
        TextView authorName = (TextView) convertview.findViewById(R.id.author);
        authorName.setText(book.getMauthor());
        return convertview;
    }
}
