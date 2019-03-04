package com.example.uuj.timmy2project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button timmysbutton = (Button)findViewById(R.id.timmysbutton);

        timmysbutton.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        TextView timmytext = (TextView)findViewById(R.id.timmytext);
                        timmytext.setText("Good job");
                    }
                }
        );

        timmysbutton.setOnLongClickListener(
                new Button.OnLongClickListener(){
                    public boolean onLongClick(View v){
                        TextView timmytext = (TextView)findViewById(R.id.timmytext);
                        timmytext.setText("This is a long click button");
                        return false;
                    }
                }
        );
    }
}
