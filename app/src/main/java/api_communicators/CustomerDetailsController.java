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

public class CustomerDetailsController extends AsyncTask<String, Integer, Customer> {

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
    protected Customer doInBackground(String...args) {


        try {

            URL url = new URL (api_base_url + args[0] + args[1]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection ();
            connection.connect ();



            JsonParser jp = new JsonParser ();
            JsonElement root = jp.parse (new InputStreamReader ((InputStream) connection.getContent ()));
            JsonObject customer_object = root.getAsJsonObject ();
            Gson gson = new GsonBuilder ().serializeNulls ().create ();

            String usernaname = customer_object.get("Username").getAsString();

            if (!usernaname.equalsIgnoreCase("void")) {


                setCustomer_id(customer_object.get("Customer_ID").getAsInt());
                setUsername(customer_object.get("Username").getAsString());
                setEmail(customer_object.get("Email").getAsString());
                setPhone_number(customer_object.get("Phone_Number").getAsString());
                setIterations(customer_object.get("Iterations").getAsInt());
                setSalt(customer_object.get("Salt").getAsString());
                setPassHash(customer_object.get("PassHash").getAsString());
                setPassPin(customer_object.get("PassPin").getAsString());
                setCardToken(customer_object.get("Card_Token").getAsString());
                setCustomer(new Customer(getCustomer_id(), getUsername(), getEmail(), getPhone_number(), getIterations(), getSalt(), getPassHash(), getPassPin(), getCardToken()));

                return getCustomer();
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


    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public void setIterations(int iterations) {
        this.iterations = iterations;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public void setPassHash(String passHash) {
        this.passHash = passHash;
    }

    public void setPassPin(String passPin) {
        this.passPin = passPin;
    }

    public void setCardToken(String cardToken) {
        this.cardToken = cardToken;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
