package api_communicators;

import android.os.AsyncTask;

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
 * Created by espinajohn on 8/11/2017.
 */

public class AvailableStocksListRetriever extends AsyncTask<String, Integer, ArrayList<Integer>> {

    int ingredient_id;
    String ingredient_name;
    String category;
    int categoryID;
    int stock_level;
    double price;
    String img_file_name;
    ArrayList<Integer> allStocksList = new ArrayList<>();
    HashMap<String, ArrayList> categoryHash = new HashMap<>();
    String ingredientURL= "http://project2-burgerx-database-api.herokuapp.com/ingredients/available";


    @Override
    protected ArrayList<Integer> doInBackground(String...args) {


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
                category = StockControls.getItemCategory(ingredient_id);
                categoryID = ingredientObject.get("Category_ID").getAsInt();
                stock_level = ingredientObject.get("Stock_Level").getAsInt();
                price = ingredientObject.get("Price").getAsDouble();
                img_file_name = "";

                // Create a new Stock object and add it to the list of stocks.

              //  Stock stockItem = new Stock(ingredient_id,ingredient_name, category, stock_level,price,img_file_name);


               if (category.matches("(?i)Bread|Salad|Meat|Cheese|Sause")){
                   allStocksList.add(ingredient_id);
               }

            }



        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return allStocksList;
    }

    protected void onProgressUpdate (Integer...progress){
    }

    protected  void onPostExecute (Stock s){
    }
}
