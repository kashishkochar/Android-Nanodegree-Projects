package com.example.android.musicalstructureapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class now_Playing extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_now__playing);

        Button play = (Button) findViewById(R.id.b3);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //create a intent
                Intent i = new Intent(now_Playing.this, songs.class);
                //start new activity
                startActivity(i);
            }
        });
    }
}
