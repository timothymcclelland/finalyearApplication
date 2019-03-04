package com.example.uuj.footballquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        ImageButton home = (ImageButton) findViewById(R.id.button_home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main3Activity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        ImageButton forward = (ImageButton) findViewById(R.id.forward_button);
        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Main3Activity.this, Main4Activity.class);
                startActivity(intent1);
            }
        });
        Button ninetyy = (Button) findViewById(R.id.ninety);
        ninetyy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Main3Activity.this, "Wrong Answer", Toast.LENGTH_SHORT).show();
            }
        });
        Button eightyfive = (Button) findViewById(R.id.eighty_five);
        eightyfive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Main3Activity.this, "Wrong Answer", Toast.LENGTH_SHORT).show();
            }
        });
        Button tenth = (Button) findViewById(R.id.ten);
        tenth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Main3Activity.this, "Wrong Answer", Toast.LENGTH_SHORT).show();
            }
        });
        Button ninetytwo = (Button) findViewById(R.id.ninety_two);
        ninetytwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Main3Activity.this, "Correct", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
