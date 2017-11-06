package api_communicators;

import android.os.AsyncTask;
import android.util.Log;

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

/**
 * This class talks to the API to retrive customer details when a customer logs in to the app
 * Created by johnny on 11/4/2017.
 */

public class CustomerVerifier extends AsyncTask<String, Integer, Customer> {

    int customer_id;
    String username;
    String email;
    String phone_number;
    int iterations;
    String salt;
    String passHash;
    String passPin;
    String cardToken;
    Customer customer;
    static final String api_base_url = "http://project2-burgerx-database-api.herokuapp.com/customer/username/";


    @Override
    protected Customer doInBackground(String...args) {

        JsonObject customer_object = null;
        try {
            URL url = new URL(api_base_url + args[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.connect();

            JsonParser jp = new JsonParser();
            JsonElement root = jp.parse(new InputStreamReader((InputStream) connection.getContent()));
            customer_object = root.getAsJsonObject();
            Gson gson = new GsonBuilder().serializeNulls().create();

            customer_id = customer_object.get("Customer_ID").getAsInt();
            username = customer_object.get("Username").getAsString();
            email = customer_object.get("Email").getAsString();
            phone_number =  customer_object.get("Phone_Number").getAsString();
            iterations = customer_object.get("Iterations").getAsInt();
            salt = customer_object.get("Salt").getAsString();
            passHash = customer_object.get("PassHash").getAsString();
            passPin = customer_object.get("PassPin").getAsString();
            cardToken = customer_object.get("Card_Token").getAsString();
            customer =  new Customer(customer_id, username, email, phone_number, iterations, salt, passHash, passPin, cardToken);

            Log.d("Customer Object", customer_object.toString());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return customer;


    }



    protected void onProgressUpdate (Integer...progress){

    }

    protected  void onPostExecute (Customer c){

    }






    public Customer getCustomer(){
        return customer;
    }
    public int getCustomer_id() {
        return customer_id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public int getIterations() {
        return iterations;
    }

    public String getSalt() {
        return salt;
    }

    public String getPassHash() {
        return passHash;
    }

    public String getPassPin() {
        return passPin;
    }

    public String getCardToken() {
        return cardToken;
    }



}
