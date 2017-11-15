package com.example.espinajohn.xburger;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import api_communicators.AllStockRetriever;
import api_communicators.GetAllStockItems;
import api_communicators.StockDetailsController;
import entity.Item;


public class MainActivity extends AppCompatActivity {

    BurgerAppLayout control;

    int currentLayout;
    ArrayList<Item> burger_order;
    Boolean loggedin;
    int customerid;

    private static HashMap<String, ArrayList> stockHashMap = new HashMap<>();
    public static HashMap<Integer, Boolean> selectedStock;

    public static HashMap<String, ArrayList> getStockHashMap() {
        return stockHashMap;
    }

    public static void setStockHashMap(HashMap<String, ArrayList> stockHashMap) {
        MainActivity.stockHashMap = stockHashMap;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.d("CHECK", "on create");
        super.onCreate(savedInstanceState);

        SharedPreferences settings = getSharedPreferences("BurgerPreferences", Context.MODE_PRIVATE);

        //Get the preferences here
        loggedin = settings.getBoolean("logged_in", false);
        customerid = settings.getInt ("customer_id", 0);
        currentLayout = settings.getInt ("current_layout", R.layout.activity_main);

       //retrieve available ingredients from database
        //this will return a hashmap of category with corresponding arraylist of Stock item as value
        try {
            setStockHashMap(new AllStockRetriever().execute().get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        //Retrieves the list of all ingredients by ingredient_id
        //Will be used to send orders to the database and for persistence
        try {
            selectedStock = new GetAllStockItems().execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace ();
        } catch (ExecutionException e) {
            e.printStackTrace ();
        }

        for (Integer key : selectedStock.keySet()) {
            Log.d("ActivityMain", "" + key + " " + selectedStock.get (key));
        }


        //Call the Burger Controller to set up the main screen
        if (savedInstanceState != null) {
            // Remember the layout
            currentLayout = savedInstanceState.getInt("current_layout");
            control = new BurgerAppLayout(this, currentLayout);


            if (loggedin !=null) {
                control.app_logged_in = loggedin;
                control.customer_id = customerid;
            }

        } else {
            control = new BurgerAppLayout(this, R.layout.activity_main);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //Put the saved instance state preferences here
        outState.putInt("current_layout", control.currentLayout);
        outState.putBoolean ("logged_in", control.app_logged_in);
        outState.putInt ("customer_id", control.customer_id);
        super.onSaveInstanceState(outState);
    }

    protected void onStop(){
        Log.d("CHECK", "on stop");
        SharedPreferences settings = getSharedPreferences("BurgerPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean("logged_in", control.app_logged_in);
        editor.putInt ("customer_id", control.customer_id);
        editor.putInt ("current_layout", control.currentLayout);

        editor.commit();
        super.onStop();
    }

    protected void onDestroy(){
        Log.d ("CHECK", "on destroy");
        super.onDestroy ();
    }
}
