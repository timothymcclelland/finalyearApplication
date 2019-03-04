package com.example.uuj.footballquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class Main4Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        ImageButton home = (ImageButton) findViewById(R.id.button_home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main4Activity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        ImageButton forward = (ImageButton) findViewById(R.id.forward_button);
        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Main4Activity.this, Main5Activity.class);
                startActivity(intent1);
            }
        });
        Button ppogba = (Button) findViewById(R.id.pogba);
        ppogba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Main4Activity.this, "Correct", Toast.LENGTH_SHORT).show();
            }
        });
        Button torres_nine = (Button) findViewById(R.id.torres);
        torres_nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Main4Activity.this, "Wrong Answer", Toast.LENGTH_SHORT).show();
            }
        });
        Button sterling_raheem = (Button) findViewById(R.id.sterling);
        sterling_raheem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Main4Activity.this, "Wrong Answer", Toast.LENGTH_SHORT).show();
            }
        });
        Button mezut_ozil = (Button) findViewById(R.id.ozil);
        mezut_ozil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Main4Activity.this, "Wrong Answer", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
