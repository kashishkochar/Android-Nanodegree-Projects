package com.example.android.musicalstructureapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class songs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_songs);
        Button song = (Button) findViewById(R.id.b6);

        song.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //create a intent
                Intent i = new Intent(songs.this, playlist.class);
                //start new activity
                startActivity(i);
            }
        });
    }
}
