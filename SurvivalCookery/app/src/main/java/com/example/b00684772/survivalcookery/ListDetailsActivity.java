package com.example.b00684772.survivalcookery;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class ListDetailsActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_details);

        //Linking the JAVA to the XML features
        ImageView ivRecipeImage = (ImageView) findViewById(R.id.ivRecipeImage);
        TextView tvRecipeName = (TextView) findViewById(R.id.tvRecipeName);
        TextView tvRecipeDesciption = (TextView) findViewById(R.id.tvRecipeDesciption);
        TextView tvRecipeCategory = (TextView) findViewById(R.id.tvRecipeCategory);
        TextView tvRecipeIngredients = (TextView) findViewById(R.id.tvRecipeIngredients);
        TextView tvRecipeMethod = (TextView) findViewById(R.id.tvRecipeMethod);
        TextView tvRecipeChef = (TextView) findViewById(R.id.tvRecipeChef);

        //Obtain the Strings passed in using the Intent
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String recipe_name = bundle.getString("name");
        String recipe_category = bundle.getString("category");
        String recipe_description = bundle.getString("description");
        String recipe_ingredients = bundle.getString("ingredients");
        String recipe_method = bundle.getString("method");
        String recipe_imageurl = bundle.getString("imageurl");
        String recipe_chef = bundle.getString("chef");

        //Display the relevant information in the TextViews
        tvRecipeName.setText(recipe_name);
        tvRecipeCategory.setText(recipe_category);
        tvRecipeDesciption.setText(recipe_description);
        tvRecipeIngredients.setText(recipe_ingredients);
        tvRecipeMethod.setText(recipe_method);
        tvRecipeChef.setText(recipe_chef);

        //Display the image - should already be cached
        Glide.with(getApplicationContext())
                .load(recipe_imageurl)
                .override(375, 250)
                .into(ivRecipeImage);

        final ImageButton backbutton = (ImageButton) findViewById(R.id.backbutton);
        backbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

    }
}

