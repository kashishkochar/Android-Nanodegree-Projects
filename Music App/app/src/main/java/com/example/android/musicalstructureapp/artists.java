package com.example.android.musicalstructureapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class artists extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artists);

        Button artist = (Button) findViewById(R.id.b1);

        artist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //create a intent
                Intent i = new Intent(artists.this, now_Playing.class);
                //start new activity
                startActivity(i);
            }
        });
    }
}
