package com.example.android.tourism;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class historical extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        final ArrayList<tour> tourApp = new ArrayList<tour>();
        tourApp.add(new tour(getString(R.string.a1),getString(R.string.a6),R.drawable.red_fort));
        tourApp.add(new tour(getString(R.string.a2),getString(R.string.a7),R.drawable.bhangarh_fort));
        tourApp.add(new tour(getString(R.string.a3),getString(R.string.a8),R.drawable.jallianwala));
        tourApp.add(new tour(getString(R.string.a4),getString(R.string.a9),R.drawable.taj_mahal));
        tourApp.add(new tour(getString(R.string.a5),getString(R.string.a10),R.drawable.qutab_minar));

        tourAdapter adapter = new tourAdapter(this,tourApp);

        ListView listView = (ListView) findViewById(R.id.list_view);

        listView.setAdapter(adapter);
    }
}
