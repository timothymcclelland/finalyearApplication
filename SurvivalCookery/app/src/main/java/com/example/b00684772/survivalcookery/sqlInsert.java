package com.example.b00684772.survivalcookery;

import android.os.AsyncTask;
import org.json.JSONArray;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class sqlInsert extends AsyncTask<String, Void, JSONArray> {
    //runs the script file sqlinsert.php and gets the values that are contained within the database.
    @Override
    protected JSONArray doInBackground(String... arg0) {

        String strurl = arg0[0];
        String name = arg0[1];
        String category = arg0[2];
        String chef = arg0[3];
        String ingredients = arg0[4];
        String method = arg0[5];
        String description = arg0[6];
     //this executes the url and puts the passed through array items into the url and executes the php on the server to insert the data into the database
        try {
            String test = strurl + "?category=" + URLEncoder.encode(category, "UTF-8") + "&name=" + URLEncoder.encode(name, "UTF-8") + "&chef=" + URLEncoder.encode(chef, "UTF-8") + "&ingredients=" + URLEncoder.encode(ingredients, "UTF-8") + "&method=" + URLEncoder.encode(method, "UTF-8") + "&description=" + URLEncoder.encode(description, "UTF-8");
            URL myFileURL = null;
            InputStream is = null;
            myFileURL = new URL(test);
            is = myFileURL.openStream();

            return null;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

        @Override
    protected void onPostExecute(JSONArray result) {
        super.onPostExecute(result);

    }

}