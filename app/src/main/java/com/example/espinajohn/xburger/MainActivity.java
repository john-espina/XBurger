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
import api_communicators.AvailableStocksListRetriever;
import api_communicators.GetAllStockItems;
import api_communicators.StockDetailsController;


public class MainActivity extends AppCompatActivity {

    BurgerAppLayout control;
    int currentLayout;
    Boolean loggedin;
    int customerid;
    private static HashMap<String, ArrayList> allStockHashMap = new HashMap<>();
    private static  HashMap<String, ArrayList> availableStocksHashMap = new HashMap<>();
    public static HashMap<Integer, Boolean> selectedStock;
    private static ArrayList<Integer> availableStocksList = new ArrayList<>();




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
            setAllStockHashMap(new AllStockRetriever().execute().get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        try {
            setAvailableStocksHashMap(new StockDetailsController().execute().get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        try {
            setAvailableStocksList(new AvailableStocksListRetriever().execute().get());
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
                control.customer_id = customerid;
                control.selectedStock = (HashMap<Integer,Boolean>) savedInstanceState.getSerializable("FoodMap");

                for (int key: control.selectedStock.keySet()){
                    Log.d("SavedInstance NotNull", ""+ key + " " + control.selectedStock.get(key));
                }
            }

        } else {
            try {
                selectedStock = new GetAllStockItems().execute().get();
            } catch (InterruptedException e) {
                e.printStackTrace ();
            } catch (ExecutionException e) {
                e.printStackTrace ();
            }

            for (Integer key : selectedStock.keySet()) {
                Log.d("AM SavedInsSt Null", "" + key + " " + selectedStock.get (key));
            }

            control = new BurgerAppLayout(this, R.layout.activity_main);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //Put the saved instance state preferences here
        outState.putInt("current_layout", control.currentLayout);
        outState.putBoolean ("logged_in", control.app_logged_in);
        outState.putSerializable("FoodMap", control.selectedStock);
        outState.putInt ("customer_id", control.customer_id);
        super.onSaveInstanceState(outState);
    }

    @Override
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

    @Override
    protected void onDestroy(){
        Log.d ("CHECK", "on destroy");
        super.onDestroy ();
    }

    @Override
    public void onStart(){
        Log.d("CHECK", "On Start");

        for (int key: control.selectedStock.keySet()){
            Log.d("SavedInstance NotNull", ""+ key + " " + control.selectedStock.get(key));
        }
        super.onStart();
    }


    public static HashMap<String, ArrayList> getAllStockHashMap() {
        return allStockHashMap;
    }

    public static void setAllStockHashMap(HashMap<String, ArrayList> allStockHashMap) {
        MainActivity.allStockHashMap = allStockHashMap;
    }

    public static HashMap<String, ArrayList> getAvailableStocksHashMap() {
        return availableStocksHashMap;
    }

    public static void setAvailableStocksHashMap(HashMap<String, ArrayList> availableStocksHashMap) {
        MainActivity.availableStocksHashMap = availableStocksHashMap;
    }

    public static ArrayList<Integer> getAvailableStocksList() {
        return availableStocksList;
    }

    public static void setAvailableStocksList(ArrayList<Integer> availableStocksList) {
        MainActivity.availableStocksList = availableStocksList;
    }
}





