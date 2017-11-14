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
import java.util.Map;

import entity.Customer;
import entity.Item;
import entity.Order;
import entity.Staff;
import entity.Stock;
import helpers.OrderControls;
import helpers.StockControls;

/**
 * Created by stlaumade on 14/11/2017.
 */

public class GetOrderListFromDB extends AsyncTask <Integer, Integer, ArrayList<Order>> {

    static final String api_base_url = "http://project2-burgerx-database-api.herokuapp.com/order/";
    ArrayList<Order> order_list = new ArrayList<Order> ();

    @Override
    protected ArrayList<Order> doInBackground(Integer... args) {

        Log.d ("Get Orders", "Inside the Do In Background");

        // Create the order object to pass in
        int customer_id = args[0];
        Log.d ("Get Orders", "" + customer_id);


        //Method for getting an orderlist by customer id

        String api_url = api_base_url + "list/customer/" + customer_id;


        try {
            //Request the json resource at the specified url
            URL url = new URL (api_url);
            HttpURLConnection request = (HttpURLConnection) url.openConnection ();
            request.connect();

            Log.d ("GetOrderListFromDB", "CONNECTION SUCCESSFUL");

            //Convert the JSON object to access data
            JsonParser jp = new JsonParser (); //json parser from gson library
            Log.d ("GetOrderListFromDB", "Json Parser Passed");

            JsonElement root = jp.parse (new InputStreamReader((InputStream)request.getContent()));
            Log.d ("GetOrderListFromDB", "Json Element Passed");

            JsonArray order_json_list = root.getAsJsonArray ();
            Log.d ("GetOrderListFromDB", order_json_list.toString () + "");

            Log.d ("GetOrderListFromDB 2 ", order_json_list.size () + "");

            for (int n = 0; n < order_json_list.size (); n++) {
                JsonObject order_json = order_json_list.get (n).getAsJsonObject ();

                //Get item details and pass into ingredient objects
                JsonObject item_details = null;
                try {
                    item_details = order_json.get ("item_details_list").getAsJsonObject ();
                } catch (IllegalStateException e) {
                    //Catch case where order does not exist
                    System.out.println ("Order does not exist");
                    return null;
                }

                //Get order metadata and parse elements into an order object
                JsonObject order_meta = order_json.get ("order_details_list").getAsJsonObject ();
                //Process order_meta
                int order_id_received = order_meta.get ("Order_ID").getAsInt ();
                Staff staff = null;
                int customer_id_output = order_meta.get ("Customer_ID").getAsInt ();
                Customer customer = new Customer (customer_id_output, null, null, null, -1, null, null, null, null);
                String datetimeString = order_meta.get ("DateTime").getAsString ();
                int order_status_num = order_meta.get ("Status").getAsInt ();
                String order_status = "" + order_status_num;

                //Get items and ingredients
                ArrayList<Item> items = new ArrayList<> ();
                //For each item, process the ingredients
                for (Map.Entry<String, JsonElement> entry : item_details.entrySet ()) {

                    int item_id = Integer.parseInt (entry.getKey ());
                    //Initialise item ingredients array as a new array
                    ArrayList<Stock> ingredients = new ArrayList<> ();

                    String item_type = "";

                    JsonArray ingredient_array = entry.getValue ().getAsJsonArray ();
                    Log.d ("JSON ARRAY", ingredient_array.toString ());

                    for (int i = 0; i < ingredient_array.size (); i++) {

                        int stock_id = Integer.parseInt (ingredient_array.get (i).toString ());

                        //Check the first ingredients to confirm the item type
                        if (i == 0) {
                            item_type = StockControls.getItemType (stock_id);
                        }

                        String stock_name = StockControls.getIngredientName (stock_id);
                        String category = StockControls.getItemCategory (stock_id);
                        double price = StockControls.getIngredientPrice (stock_id);
                        Stock ingredient = new Stock (stock_id, stock_name, category, -1, price, "");

                        ingredients.add (ingredient);
                    }

                    Item new_item = new Item (item_id, order_id_received, ingredients, item_type);
                    items.add (new_item);
                }

                Order order = new Order (order_id_received, staff, customer, datetimeString, order_status, items);
                order_list.add (order);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace ();
        } catch (IOException e) {
            e.printStackTrace ();
        } catch (IllegalStateException e) {
            return null;
        }

        Log.d("Retrieving orders", "" + order_list.size());
        return order_list;
    }

    public static void main(String[] args) {

    }

}

