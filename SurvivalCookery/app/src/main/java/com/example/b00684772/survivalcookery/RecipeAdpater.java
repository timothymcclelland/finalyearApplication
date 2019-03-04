package com.example.b00684772.survivalcookery;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class RecipeAdpater extends ArrayAdapter<JSONObject> {

    private Context context;

    public RecipeAdpater(Context context, int resource, int textViewResourceId, List<JSONObject> objects) {
        super(context, resource, textViewResourceId, objects);

        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        String strName = "Recipe Name";
        String strURL = "URL";
        String strDesc = "Recipe Description";
        String strChef = "Recipe Chef";

        //Obtain a handle to the view
        View v = convertView;

        //Check the handle exists and inflate the view components for a single row
        if (v == null) {
            LayoutInflater viewInflator = LayoutInflater.from(getContext());
            v = viewInflator.inflate(R.layout.listview_row, null);
        }

        //Obtain the JSONObject for the current position in the list
        JSONObject item = getItem(position);

        //Check the JSON object exists and assign the associated data to the view components
        if (item != null) {

            ImageView recipeIcon = (ImageView) v.findViewById(R.id.ivRecipeImage);
            TextView recipeName = (TextView) v.findViewById(R.id.tvRecipeName);
            TextView recipeDescption = (TextView) v.findViewById(R.id.tvRecipeDesciption);
            TextView recipeChief = (TextView) v.findViewById(R.id.tvRecipeChef);
            //Obtain the name and location from the JSON object
            try {

                strName = item.getString("name");
                strDesc = item.getString("description");
                strURL = item.getString("imageurl");
                strChef = item.getString("chef");

            } catch (JSONException e) {
                e.printStackTrace();
            }

            //Place the content obtained in the view components
            recipeName.setText(strName);
            recipeDescption.setText(strDesc);
            recipeChief.setText(strChef);

            //Use glide to load and cache the images for the resulting driver
            Glide.with(getContext())
                    .load(strURL)
                    .override(375, 250)
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(recipeIcon);

        }

        //Return the view
        return v;

    }
}