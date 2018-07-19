package com.example.ayaan.booklisting;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import static android.R.attr.resource;
import static android.R.id.list;

/**
 * Created by AYAAN on 4/9/2017.
 */

public class BookAdapter extends ArrayAdapter<Book> {

    public BookAdapter(Activity context, ArrayList<Book> book) {
        super(context, 0,book);
    }
    public View getView(int position, View currentview, ViewGroup parent){
        Book current = getItem(position);
        View currentlist = currentview;
        if (currentlist == null){
            currentlist = LayoutInflater.from(getContext()).inflate(R.layout.places,parent,false);
        }
        TextView name = (TextView)currentlist.findViewById(R.id.name);
        name.setText(current.getBookname());
        TextView author = (TextView)currentlist.findViewById(R.id.author);
        author.setText(current.getAuthor());
        return currentlist;
    }
}
