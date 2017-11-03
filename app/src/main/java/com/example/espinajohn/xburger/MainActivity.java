package com.example.espinajohn.xburger;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    BurgerAppLayout control;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        control = new BurgerAppLayout(this);

        SharedPreferences settings = getSharedPreferences("BurgerPreferences", Context.MODE_PRIVATE);

        //Get the preferences here

        //Call the Burger Controller to set up the main screen
    }

    protected void onStop(){
        SharedPreferences settings = getSharedPreferences("BurgerPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();

        //Save the preferences in here

        editor.commit();
        super.onStop();
    }
}
