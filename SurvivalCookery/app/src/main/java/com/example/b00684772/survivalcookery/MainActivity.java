package com.example.b00684772.survivalcookery;


import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    //Class member variables
    private URL currentDatabaseUrl = null;
    private JSONTask myJSONTask = new JSONTask();
    private Button all, snack, breakfast, lunch, dinner;
    public ListView listView = null;
    List<JSONObject> currentDBModel = new ArrayList<JSONObject>();
    private JSONTask one, two, three, four, five;
    private int filternumber = 1;
    private ProgressBar progressbar;
    private String filtercolourhex = "#8bae00";

    //Filenames for php scripts to run queries on the database for the filters
    private static final String ALLRECIPES = "getallrecipes.php";
    private static final String ALLBREAKFAST = "getallbreakfast.php";
    private static final String ALLLUNCH = "getalllunch.php";
    private static final String ALLDINNER = "getalldinner.php";
    private static final String ALLSNACK = "getallsnack.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list);

        //Linking the JAVA to the XML features
        this.all = (Button) findViewById(R.id.allrecipes);
        this.snack = (Button) findViewById(R.id.allsnack);
        this.breakfast = (Button) findViewById(R.id.allbreakfast);
        this.lunch = (Button) findViewById(R.id.alllunch);
        this.dinner = (Button) findViewById(R.id.alldinner);
        this.progressbar = (ProgressBar) findViewById(R.id.progressBar);

        //Setting the filter button background colours
        all.setBackgroundColor(Color.parseColor("#87b4c7"));
        snack.setBackgroundColor(Color.parseColor("#87b4c7"));
        breakfast.setBackgroundColor(Color.parseColor("#87b4c7"));
        lunch.setBackgroundColor(Color.parseColor("#87b4c7"));
        dinner.setBackgroundColor(Color.parseColor("#87b4c7"));

        //FloatingActionButton implemented which enables user to add a record to the database
        FloatingActionButton add = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Addition.class);
                startActivity(intent);
            }
        });

        //Filter buttons at the top of the main activity which allow the user to refine the listview
        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFilterColour(all, snack, breakfast, lunch, dinner);
                updateList(ALLRECIPES, one);
                filternumber = 1;
            }
        });

        snack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFilterColour(snack, all, breakfast, lunch, dinner);
                updateList(ALLSNACK, two);
                filternumber = 3;
            }
        });

        breakfast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFilterColour(breakfast, snack, all, lunch, dinner);
                updateList(ALLBREAKFAST, three);
                filternumber = 2;
            }
        });

        lunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFilterColour(lunch, snack, breakfast, all, dinner);
                updateList(ALLLUNCH, four);
                filternumber = 4;
            }
        });

        dinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFilterColour(dinner, snack, breakfast, lunch, all);
                updateList(ALLDINNER, five);
                filternumber = 5;
            }
        });

        //SwipeRefreshLayout which was implented from https://developer.android.com/training/swipe/add-swipe-interface.html and was used to
        //refresh the listview if the database was updated from another device. It also takes into account the filter they last chose and runs that query again.
        final SwipeRefreshLayout swipeRefresh = (SwipeRefreshLayout)findViewById(R.id.swiperefresh);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                filterChange(filternumber);

                //Stopping the spinner icon when the refresh is complete
                swipeRefresh.setRefreshing(false);
            }
        });


        //ListView object for displaying the menu results with OnClickListener
        listView = (ListView) findViewById(R.id.lvJsonItem);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //Get the position of the item that was clicked
                int itemPosition = position;

                //Obtain the recipes JSONObject
                try {

                    JSONObject jsonobject = currentDBModel.get(itemPosition);
                    String imageurl = jsonobject.getString("imageurl");
                    String category = jsonobject.getString("category");
                    String name = jsonobject.getString("name");
                    String time = jsonobject.getString("time");
                    String chef = jsonobject.getString("chef");
                    String ingredients = jsonobject.getString("ingredients");
                    String method = jsonobject.getString("method");
                    String description = jsonobject.getString("description");

                    //Create an Intent and store the details in a Bundle
                    Intent detailsIntent = new Intent(getApplicationContext(), ListDetailsActivity.class);
                    Bundle detailsBundle = new Bundle();
                    detailsBundle.putString("name", name);
                    detailsBundle.putString("category", category);
                    detailsBundle.putString("ingredients", ingredients);
                    detailsBundle.putString("method", method);
                    detailsBundle.putString("description", description);
                    detailsBundle.putString("time", time);
                    detailsBundle.putString("chef", chef);
                    detailsBundle.putString("imageurl", imageurl);

                    //Add the bundle to the Intent
                    detailsIntent.putExtras(detailsBundle);

                    //Start the ListDetails Activity
                    startActivity(detailsIntent);

                } catch (Exception ex) {
                    ex.printStackTrace();
                }


            }

        });


        //Attempt to connect to the DB and retrieve the recipes on app startup
        try {
            //Create the URL
            String IpAddress =getApplicationContext().getResources().getString(R.string.ipAddress);
            currentDatabaseUrl = new URL(IpAddress + ALLRECIPES);
            myJSONTask.execute(currentDatabaseUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
    }
}

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("FILTER_KEY", filternumber);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        filternumber = savedInstanceState.getInt("FILTER_KEY");
        filterChange(filternumber);
        switch (filternumber){
            case 1: all.setBackgroundColor(Color.parseColor(filtercolourhex));
                break;
            case 2: breakfast.setBackgroundColor(Color.parseColor(filtercolourhex));
                break;
            case 3: snack.setBackgroundColor(Color.parseColor(filtercolourhex));
                break;
            case 4: lunch.setBackgroundColor(Color.parseColor(filtercolourhex));
                break;
            case 5: dinner.setBackgroundColor(Color.parseColor(filtercolourhex));
                break;
        }

    }

    //The updateList method is used for the filter buttons. It runs a new Asynctask to get the
    //filtered recipes in the background of the UI
    public void updateList(String phpfilename, JSONTask task){
        try {
            String IpAddress =getApplicationContext().getResources().getString(R.string.ipAddress);
            currentDatabaseUrl = new URL(IpAddress + phpfilename);
            task = new JSONTask();
            task.execute(currentDatabaseUrl);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    //Method to let the user know what filter they last pressed
    public void changeFilterColour(Button filterchoice, Button filterother1, Button filterother2, Button filterother3, Button filterother4){
        filterchoice.setBackgroundColor(Color.parseColor(filtercolourhex));
        filterother1.setBackgroundColor(Color.parseColor("#87b4c7"));
        filterother2.setBackgroundColor(Color.parseColor("#87b4c7"));
        filterother3.setBackgroundColor(Color.parseColor("#87b4c7"));
        filterother4.setBackgroundColor(Color.parseColor("#87b4c7"));
    }

    public void filterChange(int filterchoice){
        switch (filterchoice) {

            case 1: updateList(ALLRECIPES, one);
                break;

            case 2: updateList(ALLBREAKFAST, three);
                break;

            case 3: updateList(ALLSNACK, two);
                break;

            case 4: updateList(ALLLUNCH, four);
                break;

            case 5: updateList(ALLDINNER, five);
                break;

            default: updateList(ALLRECIPES, one);

        }
    }

    //Custom class that extends Asynctask called JSONTask, which connects to the database, queries it, returns a JSONOBject
    //and finally populates the listview with recipes from the database.
    public class JSONTask extends AsyncTask<URL, Void, JSONArray> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressbar.setVisibility(View.VISIBLE);
        }

        @Override
        protected JSONArray doInBackground(URL... urls) {
            //Get the params
            final URL url = urls[0];
            JSONArray parentArray = null;

            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {

                //Open the connection
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                //Create an input stream reader from the connection
                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));

                //Create a string buffer to store the String obtained from the input stream
                StringBuffer buffer = new StringBuffer();

                //Store the input stream in the string buffer
                String line = "";
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }

                //Create a string from the string buffer
                String finalJson = buffer.toString();

                try {

                    //Create a JSONArray from the string
                    parentArray = new JSONArray(finalJson);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                //Return the JSONArray
                return parentArray;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }



        @Override
        protected void onPostExecute(JSONArray result) {
            super.onPostExecute(result);
            int numResults = 0;
            //Determine the number of entries returned
            try {
                numResults = result.length();
            } catch (Exception ex){
                Toast.makeText(MainActivity.this, "Please connect to the internet!", Toast.LENGTH_SHORT).show();
                numResults = 0; //Stops app from crashing with no internet access due to result.length() giving a null value.
            }

            //Populate the ListView:
            currentDBModel = new ArrayList<JSONObject>();

            //Create the model from the JSONArray
            try {

                //Obtain details from the JSON array as a JSON Object and store in the List<JSONObject>
                for (int idx = 0; idx < numResults; idx++) {
                    JSONObject jsonObj = result.getJSONObject(idx);
                    currentDBModel.add(jsonObj);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            //Create a ListView object to display the results of the search
            ListView listView = (ListView) findViewById(R.id.lvJsonItem);

            //Create a DriverAdapter object to provide data for the list
            RecipeAdpater listAdapter = new RecipeAdpater(getApplicationContext(),
                    android.R.layout.simple_list_item_1, android.R.id.text1, currentDBModel);

            //Bind the ArrayAdapter to the ListView

            progressbar.setVisibility(View.GONE);
            listView.setAdapter(listAdapter);
            listView.setVisibility(View.VISIBLE);
        }

    }
}







