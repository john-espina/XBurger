package com.example.espinajohn.xburger;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import api_communicators.AllStockRetriever;
import api_communicators.Stock;


public class MainActivity extends AppCompatActivity {

    BurgerAppLayout control;
    int currentLayout;
    ArrayList<Integer> burger_order;
    Boolean loggedin;
    private static HashMap<String, ArrayList> stockHashMap = new HashMap<>();

    public static HashMap<String, ArrayList> getStockHashMap() {
        return stockHashMap;
    }

    public static void setStockHashMap(HashMap<String, ArrayList> stockHashMap) {
        MainActivity.stockHashMap = stockHashMap;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.d("CHECK", "on create");
        super.onCreate(savedInstanceState);

        SharedPreferences settings = getSharedPreferences("BurgerPreferences", Context.MODE_PRIVATE);

        //Get the preferences here
        loggedin = settings.getBoolean("logged_in", false);

       //retrieve available ingredients from database
        //this will return a hashmap of category with corresponding arraylist of Stock item as value
        try {
            setStockHashMap(new AllStockRetriever().execute().get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        //Call the Burger Controller to set up the main screen
        if (savedInstanceState != null) {
            // Remember the layout
            currentLayout = savedInstanceState.getInt("current_layout");
            control = new BurgerAppLayout(this, currentLayout);

            if (loggedin !=null) {
                control.app_logged_in = loggedin;
            }
            //Do something with the burger ingredient list


        } else {
            control = new BurgerAppLayout(this, R.layout.activity_main);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //Put the saved instance state preferences here
        outState.putInt("current_layout", control.currentLayout);
        //outState.putStringArrayList("burger_order", burger_order_ingredients);
        super.onSaveInstanceState(outState);
    }

    protected void onStop(){
        Log.d("CHECK", "on stop");
        SharedPreferences settings = getSharedPreferences("BurgerPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean("logged_in", control.app_logged_in);

        editor.commit();
        super.onStop();
    }

}
