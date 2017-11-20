package com.example.espinajohn.xburger;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import api_communicators.AllStockRetriever;
import api_communicators.AvailableStocksListRetriever;
import api_communicators.GetAllStockItems;
import api_communicators.AvailableStocksRetriever;
import entity.Item;
import entity.Order;


public class MainActivity extends AppCompatActivity {

    BurgerAppLayout control;
    private static HashMap<String, ArrayList> allStockHashMap = new HashMap<>();
    private static  HashMap<String, ArrayList> availableStocksHashMap = new HashMap<>();
    private static ArrayList<Integer> availableStocksList = new ArrayList<>();

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.d ("CHECK", "on create");
        super.onCreate (savedInstanceState);

        if (readFromFile("selectedstock") == null){
            try {
                control.selectedStock = new GetAllStockItems ().execute ().get ();
            } catch (InterruptedException e) {
                e.printStackTrace ();
            } catch (ExecutionException e) {
                e.printStackTrace ();
            }
        } else {
            control.selectedStock = (HashMap<Integer,Boolean>) readFromFile ("selectedstock");
            Log.d ("On Create Remembers", control.selectedStock + "");
        }

        //retrieve available ingredients from database
        //this will return a hashmap of category with corresponding arraylist of Stock item as value
        try {
            setAllStockHashMap (new AllStockRetriever ().execute ().get ());
        } catch (InterruptedException e) {
            e.printStackTrace ();
        } catch (ExecutionException e) {
            e.printStackTrace ();
        }

        try {
            setAvailableStocksHashMap (new AvailableStocksRetriever().execute ().get ());
        } catch (InterruptedException e) {
            e.printStackTrace ();
        } catch (ExecutionException e) {
            e.printStackTrace ();
        }

        try {
            setAvailableStocksList (new AvailableStocksListRetriever ().execute ().get ());
        } catch (InterruptedException e) {
            e.printStackTrace ();
        } catch (ExecutionException e) {
            e.printStackTrace ();
        }

        if (savedInstanceState != null) {
            control = new BurgerAppLayout (this, control.currentLayout);
        } else {
            control = new BurgerAppLayout (this, R.layout.activity_main);
        }
    }

    @Override
    protected void onStop(){
        Log.d("CHECK", "on stop");

        if (control.master_order != null){Log.d ("On Stop To Remember", control.master_order + "");}
        if (control.app_logged_in != null){Log.d ("On Stop To Remember", control.app_logged_in + "");}
        if (control.customer_id < 1){Log.d ("On Stop To Remember", control.customer_id + "");}
        if (control.currentLayout < 1){Log.d ("On Stop To Remember", control.currentLayout + "");}
        if (control.selectedStock != null){Log.d ("On Stop To Remember", control.selectedStock + "");}
        if (control.listofitems != null){Log.d ("On Stop To Remember", control.listofitems + "");}

        writeToFile ("masterorder", control.master_order);
        writeToFile ("loggedin", control.app_logged_in);
        writeToFile ("customerid", control.customer_id);
        writeToFile ("currentlayout", control.currentLayout);
        writeToFile ("selectedstock", control.selectedStock);
        writeToFile ("listofitems", control.listofitems);

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

        if (readFromFile ("masterorder") != null){
            control.master_order = (Order) readFromFile ("masterorder");
            Log.d ("On Create Remembers", control.master_order + "");
        }

        if (readFromFile("loggedin") != null) {
            control.app_logged_in = (Boolean) readFromFile ("loggedin");
            Log.d ("On Create Remembers", control.app_logged_in + "");

        }

        if (readFromFile("customerid") != null) {
            control.customer_id = (int) readFromFile ("customerid");
            Log.d ("On Create Remembers", control.customer_id + "");

        }

        if (readFromFile ("currentlayout") != null) {
            control.currentLayout = (int) readFromFile ("currentlayout");
            Log.d ("On Create Remembers", control.currentLayout + "");
        }

        if (readFromFile("listofitems") != null) {
            control.listofitems = (ArrayList<Item>) readFromFile ("listofitems");
            Log.d ("On Create Remembers", control.listofitems + "");
        }

        if (readFromFile("selectedstock") != null){
            control.selectedStock = (HashMap<Integer,Boolean>) readFromFile ("selectedstock");
            Log.d ("On Create Remembers", control.selectedStock + "");
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

    public void writeToFile(String filename, Object o){
        try
        {
            FileOutputStream fos = openFileOutput(filename, MODE_PRIVATE);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(o); //Put your custom object in here
            os.close();
            fos.close();
            Log.d("write", "Wrote Fine");
        }
        catch(Exception e)
        {
            Log.d("write", "Didn't write???");
        }
    }

    public Object readFromFile(String filename){
        try {
            FileInputStream fis = openFileInput (filename);
            ObjectInputStream is = new ObjectInputStream (fis);
            Object o = is.readObject ();
            is.close ();
            fis.close ();
            Log.d ("write", "Read Okay");
            return o;
        } catch (Exception e) {
            Log.d ("write", "Didnt Read???");
            Log.d ("write", e.toString ());
        }
        return null;
    }
}






