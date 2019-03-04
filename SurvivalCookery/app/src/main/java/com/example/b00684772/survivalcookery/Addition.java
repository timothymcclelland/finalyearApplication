package com.example.b00684772.survivalcookery;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;


public class Addition extends AppCompatActivity{

    //Instantiating class variables
    Spinner spinner;
    private EditText name, method, ingredients, description, chef;
    private ImageButton back;
    private Button submit;
    private String uriString;
    private static final String SQL_INSERT = "sqlInsert.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addition);

        //Linking the JAVA to the XML features
        this.name = (EditText)this.findViewById(R.id.name);
        this.method = (EditText)this.findViewById(R.id.method);
        this.description = (EditText)this.findViewById(R.id.description);
        this.ingredients = (EditText)this.findViewById(R.id.ingredients);
        this.chef = (EditText)this.findViewById(R.id.chef);
        this.spinner = (Spinner)this.findViewById(R.id.spinner);
        this.submit = (Button) this.findViewById(R.id.submitbutton);
        this.back = (ImageButton) this.findViewById(R.id.backbutton);

        //Declaring the URL for the insert query
        final String strUrl = getApplicationContext().getResources().getString(R.string.ipAddress)+SQL_INSERT;

        //Spinner getting linked to an ArrayAdapter
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.category_array, R.layout.spinner_layout);
        adapter.setDropDownViewResource(R.layout.spinner_layout);
        spinner.setAdapter(adapter);

        //Button which submits the form details from the user to the database using the sqlInsert class
        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Context context;
                sqlInsert a = new sqlInsert();
                final String category = spinner.getSelectedItem().toString();

                //To check the internet connection we implemented code from https://dzone.com/articles/android-snippet-check-if
                ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo ni = cm.getActiveNetworkInfo();
                if(ni != null && ni.isConnected()) {
                    if(name.getText().toString().trim().length() == 0|| chef.getText().toString().trim().length() == 0|| ingredients.getText().toString().trim().length() == 0|| method.getText().toString().trim().length() == 0||description.getText().toString().trim().length() == 0){
                        Toast.makeText(Addition.this, getApplicationContext().getResources().getString(R.string.errorMessage), Toast.LENGTH_LONG).show();
                    }else{
                        a.execute(strUrl, name.getText().toString(), category, chef.getText().toString(),
                                ingredients.getText().toString(), method.getText().toString(), description.getText().toString());
                        //Bringing the user back to the main activity after submission is done
                        Toast.makeText(Addition.this, "Your recipe was submitted", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                } else {
                    Toast.makeText(Addition.this, "Your recipe failed to submit, please check your network connection and try again", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Back button to cancel the user's entry to the database
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    //The following overrides ensure that user data is prederved in the EditTexts, should a screen rotation occur.
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("NAME_KEY", name.getText().toString());
        outState.putString("METHOD_KEY", method.getText().toString());
        outState.putString("INGREGIENTS_KEY", ingredients.getText().toString());
        outState.putString("DESCRIPTION_KEY", description.getText().toString());
        outState.putString("CHEF_KEY", chef.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        name.setText(savedInstanceState.getString("NAME_KEY"));
        method.setText(savedInstanceState.getString("METHOD_KEY"));
        ingredients.setText(savedInstanceState.getString("INGREDIENTS_KEY"));
        description.setText(savedInstanceState.getString("DESCRIPTIONS_KEY"));
        chef.setText(savedInstanceState.getString("CHEF_KEY"));
        super.onRestoreInstanceState(savedInstanceState);
    }

    }