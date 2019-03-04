package com.example.uuj.footballquiz;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         /*Intent used within start button to start quiz*/
        Button start = (Button) findViewById(R.id.start_button);
        start.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View view){
        Intent intent = new Intent(MainActivity.this, Main2Activity.class);
        startActivity(intent);
        }
        });
    }
}
