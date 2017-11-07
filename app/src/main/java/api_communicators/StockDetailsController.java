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

/**
 * This Class connects to the API to retrieve available ingredients.
 * Each Ingredient retrieved will be instantiated as a new Stock object
 * Created by espinajohn on 8/11/2017.
 */

public class StockDetailsController extends AsyncTask<String, Integer, Stock>{


    int ingredient_id;
    String ingredient_name;
    String category;
    int stock_level;
    double price;
    String img_file_name;
    ArrayList<Stock> stocks;
    String ingredientURL= "http://project2-burgerx-database-api.herokuapp.com/ingredients/available";


    @Override
    protected Stock doInBackground(String...args) {

        try{

            URL url = new URL (ingredientURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.connect();

            JsonParser jp = new JsonParser();
            JsonElement root = jp.parse(new InputStreamReader((InputStream)connection.getContent()));
            JsonArray ingredientsArray = root.getAsJsonArray(); //get the array of stock
            Gson gson = new GsonBuilder().serializeNulls().create();

            // Extract each item from the array and convert them to JsonObject
            for (int i=0; i<ingredientsArray.size();i++){
                JsonObject ingredientObject = ingredientsArray.get(i).getAsJsonObject();

                ingredient_id = ingredientObject.get("Stock_ID").getAsInt();
                ingredient_name = ingredientObject.get("Ingredient_Name").getAsString();
                //get categeory here;
                stock_level = ingredientObject.get("Stock_Level").getAsInt();
                price = ingredientObject.get("Price").getAsDouble();
                img_file_name = ingredientObject.get("Img_File_Name").getAsString();

                // Create a new Stock object and add it to the list of stocks.
                Stock stockItem = new Stock(ingredient_id,ingredient_name, category,stock_level,price,img_file_name);
                stocks.add(stockItem);
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }

    protected void onProgressUpdate (Integer...progress){
    }

    protected  void onPostExecute (Stock s){
    }
}
