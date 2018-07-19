package com.example.android.tourism;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class nightlife extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        final ArrayList<tour> tourApp = new ArrayList<tour>();
        tourApp.add(new tour(getString(R.string.d1),getString(R.string.d6),R.drawable.club_alibii));
        tourApp.add(new tour(getString(R.string.d2),getString(R.string.d7),R.drawable.kitty_su));
        tourApp.add(new tour(getString(R.string.d3),getString(R.string.d8),R.drawable.club_cabana));
        tourApp.add(new tour(getString(R.string.d4),getString(R.string.d9),R.drawable.hard_rock_cafe));
        tourApp.add(new tour(getString(R.string.d5),getString(R.string.d10),R.drawable.xtreme_sports_bar));

        tourAdapter adapter = new tourAdapter(this,tourApp);

        ListView listView = (ListView) findViewById(R.id.list_view);

        listView.setAdapter(adapter);
    }
}
