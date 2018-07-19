package com.example.android.tourism;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class adventure extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        final ArrayList<tour> tourApp = new ArrayList<tour>();
        tourApp.add(new tour(getString(R.string.c1),getString(R.string.c6),R.drawable.bungee_jumping));
        tourApp.add(new tour(getString(R.string.c2),getString(R.string.c7),R.drawable.trekking));
        tourApp.add(new tour(getString(R.string.c3),getString(R.string.c8),R.drawable.river_rafting));
        tourApp.add(new tour(getString(R.string.c4),getString(R.string.c9),R.drawable.goa));
        tourApp.add(new tour(getString(R.string.c5),getString(R.string.c10),R.drawable.wind_surfing));

        tourAdapter adapter = new tourAdapter(this,tourApp);

        ListView listView = (ListView) findViewById(R.id.list_view);

        listView.setAdapter(adapter);
    }
}
