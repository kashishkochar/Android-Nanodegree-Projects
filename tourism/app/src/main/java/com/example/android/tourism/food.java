package com.example.android.tourism;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ListView;

import java.util.ArrayList;

public class food extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        final ArrayList<tour> tourApp = new ArrayList<tour>();
        tourApp.add(new tour(getString(R.string.b1),getString(R.string.b6),R.drawable.peshawri));
        tourApp.add(new tour(getString(R.string.b2),getString(R.string.b7),R.drawable.bukhara));
        tourApp.add(new tour(getString(R.string.b3),getString(R.string.b8),R.drawable.indian_accent));
        tourApp.add(new tour(getString(R.string.b4),getString(R.string.b9),R.drawable.gulati));
        tourApp.add(new tour(getString(R.string.b5),getString(R.string.b10),R.drawable.tuscany_garden));

        tourAdapter adapter = new tourAdapter(this,tourApp);

        ListView listView = (ListView) findViewById(R.id.list_view);

        listView.setAdapter(adapter);
    }
}
