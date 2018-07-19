package com.example.android.musicalstructureapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class payment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        Button payment = (Button) findViewById(R.id.b4);

        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //create a intent
                Intent i = new Intent(payment.this, MainActivity.class);
                //start new activity
                startActivity(i);
            }
        });
    }
}
