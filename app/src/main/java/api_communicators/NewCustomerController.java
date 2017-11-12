package api_communicators;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import entity.Customer;

/**
 * Post a customer to the database to get added
 * Created by espinajohn on 6/11/2017
 */

public class NewCustomerController extends AsyncTask<String, Integer, Customer> {

    //Need to make an async task to connect to database to post the customer
    private int customer_id;
    private String username;
    private String email;
    private String phone_number;
    private int iterations;
    private String salt;
    private String passHash;
    private String passPin;
    private String cardToken;
    private Customer customer;
    static final String api_base_url = "http://project2-burgerx-database-api.herokuapp.com/customer/";

    @Override
    protected Customer doInBackground(String... args) {

        try {

            URL url = new URL (api_base_url + args[0] + args[1]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection ();
            connection.setRequestMethod ("POST");
            connection.connect ();


            JsonParser jp = new JsonParser ();
            JsonElement root = jp.parse (new InputStreamReader ((InputStream) connection.getContent ()));
            JsonObject customer_object = root.getAsJsonObject ();
            Gson gson = new GsonBuilder ().serializeNulls ().create ();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
