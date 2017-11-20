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
 * This Class connects to the API to retrieve available ingredients.
 * Each Ingredient retrieved will be instantiated as a new Stock object
 * Created by espinajohn on 8/11/2017.
 */

public class AvailableStocksRetriever extends AsyncTask<String, Integer, HashMap >{


    int ingredient_id;
    String ingredient_name;
    String category;
    int categoryID;
    int stock_level;
    double price;
    String img_file_name;
    ArrayList<Stock> buns= new ArrayList<>();
    ArrayList<Stock> salads = new ArrayList<>();
    ArrayList<Stock> patties = new ArrayList<>();
    ArrayList<Stock> cheeses = new ArrayList<>();
    ArrayList<Stock> sauces = new ArrayList<>();
    ArrayList<Stock> sides = new ArrayList<>();
    ArrayList<Stock> drinks = new ArrayList<>();
    HashMap<String, ArrayList> categoryHash = new HashMap<>();
    String ingredientURL= "http://project2-burgerx-database-api.herokuapp.com/ingredients/available";

    @Override
    protected HashMap<String, ArrayList> doInBackground(String...args) {

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
                Stock stockItem = new Stock(ingredient_id,ingredient_name, category,stock_level,price,img_file_name);
                if (category.equalsIgnoreCase("Bread")){
                    buns.add(stockItem);
                }
                if (category.equalsIgnoreCase("Salad")){
                    salads.add(stockItem);
                }
                if (category.equalsIgnoreCase("Meat")){
                    patties.add(stockItem);
                }
                if (category.equalsIgnoreCase("Cheese")){
                    cheeses.add(stockItem);
                }
                if (category.equalsIgnoreCase("Sauce")){
                    sauces.add(stockItem);
                }
                if (category.equalsIgnoreCase("Side")){
                    sides.add(stockItem);
                }
                if (category.equalsIgnoreCase("Drink")){
                    drinks.add(stockItem);
                }


            }

            categoryHash.put("bunCategory", buns);
            categoryHash.put("saladCategory", salads);
            categoryHash.put("pattieCategory", patties);
            categoryHash.put("cheeseCategory", cheeses);
            categoryHash.put("sauceCategory", sauces);
            categoryHash.put("sideCategory", sides);
            categoryHash.put("drinkCategory", drinks);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return categoryHash;
    }

    protected void onProgressUpdate (Integer...progress){
    }

    protected  void onPostExecute (Stock s){
    }
}
