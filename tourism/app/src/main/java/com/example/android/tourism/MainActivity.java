package com.example.android.tourism;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView historical = (TextView) findViewById(R.id.historical);
        historical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent historicalIntent = new Intent(MainActivity.this, historical.class);
                startActivity(historicalIntent);
            }
        });

        TextView food = (TextView) findViewById(R.id.food);
        food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent foodIntent = new Intent(MainActivity.this, food.class);
                startActivity(foodIntent);
            }
        });

        TextView adventure = (TextView) findViewById(R.id.adventure);
        adventure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent adventureIntent = new Intent(MainActivity.this, adventure.class);
                startActivity(adventureIntent);
            }
        });

        TextView nightlife = (TextView) findViewById(R.id.night_life);
        nightlife.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nightlifeIntent = new Intent(MainActivity.this, nightlife.class);
                startActivity(nightlifeIntent);
            }

        });
    }
}
