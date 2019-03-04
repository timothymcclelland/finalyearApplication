package com.example.uuj.footballquiz;

import android.content.Intent;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class Main2Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        /*Intents used for both home and forward imagebuttons as they are used to move between activities
        This is used for the activities that contain either the home or forward buttons*/
        ImageButton home = (ImageButton) findViewById(R.id.button_home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        ImageButton forward = (ImageButton) findViewById(R.id.forward_button);
        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Main2Activity.this, Main3Activity.class);
                startActivity(intent1);
            }
        });
        /* All following buttons and similar throughout the app are used with onClickListeners to present toasts to the user an appropriate response based on the answer they give*/
        Button fifth = (Button) findViewById(R.id.five);
        fifth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Main2Activity.this, "Wrong Answer", Toast.LENGTH_SHORT).show();
            }
        });
        Button third = (Button) findViewById(R.id.three);
        third.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Main2Activity.this, "Wrong Answer", Toast.LENGTH_SHORT).show();
            }
        });
        Button eighth = (Button) findViewById(R.id.eight);
        eighth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Main2Activity.this, "Wrong Answer", Toast.LENGTH_SHORT).show();
            }
        });
        Button fourth = (Button) findViewById(R.id.four);
        fourth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Main2Activity.this, "Correct", Toast.LENGTH_SHORT).show();
            }
        });
    }};