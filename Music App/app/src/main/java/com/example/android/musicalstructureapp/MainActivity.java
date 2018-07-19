package com.example.android.musicalstructureapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView playlist = (TextView) findViewById(R.id.playlist);
        TextView songs = (TextView) findViewById(R.id.song);
        TextView artist = (TextView) findViewById(R.id.artist);
        TextView now_Playing = (TextView) findViewById(R.id.playing);
        TextView payment = (TextView) findViewById(R.id.payment);

        playlist.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //create a intent
                Intent i = new Intent (MainActivity.this,playlist.class);
                //start new activity
                startActivity(i);
            }
        });
        songs.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //create a intent
                Intent i = new Intent (MainActivity.this,songs.class);
                //start new activity
                startActivity(i);
            }
        });
        artist.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //create a intent
                Intent i = new Intent (MainActivity.this,artists.class);
                //start new activity
                startActivity(i);
            }
        });
        now_Playing.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //create a intent
                Intent i = new Intent (MainActivity.this,now_Playing.class);
                //start new activity
                startActivity(i);
            }
        });
        payment.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //create a intent
                Intent i = new Intent (MainActivity.this,payment.class);
                //start new activity
                startActivity(i);
            }
        });

    }
}