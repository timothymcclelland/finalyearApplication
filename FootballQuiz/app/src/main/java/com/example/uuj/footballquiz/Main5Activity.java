package com.example.uuj.footballquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class Main5Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        ImageButton home = (ImageButton) findViewById(R.id.button_home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main5Activity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        ImageButton forward = (ImageButton) findViewById(R.id.forward_button);
        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Main5Activity.this, Main6Activity.class);
                startActivity(intent1);
            }
        });
        Button wayne_rooney = (Button) findViewById(R.id.rooney);
        wayne_rooney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Main5Activity.this, "Wrong Answer", Toast.LENGTH_SHORT).show();
            }
        });
        Button van_persie = (Button) findViewById(R.id.rvp);
        van_persie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Main5Activity.this, "Correct", Toast.LENGTH_SHORT).show();
            }
        });
        Button sergio_aguero = (Button) findViewById(R.id.aguero);
        sergio_aguero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Main5Activity.this, "Wrong Answer", Toast.LENGTH_SHORT).show();
            }
        });
        Button torres_fernando = (Button) findViewById(R.id.fernando_torres);
        torres_fernando.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Main5Activity.this, "Wrong Answer", Toast.LENGTH_SHORT).show();
            }
        });
    }
}