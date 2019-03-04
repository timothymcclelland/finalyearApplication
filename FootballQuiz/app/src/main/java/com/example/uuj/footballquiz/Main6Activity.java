package com.example.uuj.footballquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class Main6Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        /* Only the home image button is used for the final activity and is positioned centrally to signify the end of quiz*/
        ImageButton home = (ImageButton) findViewById(R.id.button_home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main6Activity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        Button hull_city = (Button) findViewById(R.id.hull);
        hull_city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Main6Activity.this, "Wrong Answer", Toast.LENGTH_SHORT).show();
            }
        });
        Button newcastle_united = (Button) findViewById(R.id.newcastle);
        newcastle_united.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Main6Activity.this, "Wrong Answer", Toast.LENGTH_SHORT).show();
            }
        });
        Button west_brom = (Button) findViewById(R.id.westbrom);
        west_brom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Main6Activity.this, "Correct", Toast.LENGTH_SHORT).show();
            }
        });
        Button portsmouth_fc = (Button) findViewById(R.id.portsmouth);
        portsmouth_fc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Main6Activity.this, "Wrong Answer", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
