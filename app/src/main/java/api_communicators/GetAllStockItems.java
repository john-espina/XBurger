package api_communicators;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import entity.Stock;
import helpers.StockControls;

/**
 * Created by stlaumade on 13/11/2017.
 */

public class GetAllStockItems extends AsyncTask<String, Integer, HashMap > {

        int ingredient_id;
        HashMap<Integer, Boolean> selectedStock = new HashMap<>();
        String ingredientURL= "http://project2-burgerx-database-api.herokuapp.com/ingredients/all";

        @Override
        protected HashMap<Integer, Boolean> doInBackground(String...args) {

            try{

                URL url = new URL (ingredientURL);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                JsonParser jp = new JsonParser();
                JsonElement root = jp.parse(new InputStreamReader ((InputStream)connection.getContent()));
                JsonArray ingredientsArray = root.getAsJsonArray(); //get the array of stock
                Gson gson = new GsonBuilder ().serializeNulls().create();

                // Extract each item from the array and convert them to JsonObject
                for (int i=0; i<ingredientsArray.size();i++) {
                    JsonObject ingredientObject = ingredientsArray.get (i).getAsJsonObject ();
                    ingredient_id = ingredientObject.get ("Stock_ID").getAsInt ();
                    selectedStock.put(ingredient_id, false);
                }



            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return selectedStock;
        }

        protected void onProgressUpdate (Integer...progress){
        }

        protected  void onPostExecute (Stock s){
        }
}

