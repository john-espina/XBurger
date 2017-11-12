package api_communicators;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import entity.Customer;

/**
 * This class talks to the API to retrive customer details when a customer logs in to the app
 * Created by stlaumade on 11/13/2017.
 */

public class CustomerAddToDBAPI extends AsyncTask<Customer, Integer, Customer> {

    private int customer_id;
    private String username;
    private String email;
    private String phone_number;
    private int iterations;
    private String salt;
    private String passHash;
    private String passPin;
    private String cardToken;
    private Customer customer = this.getCustomer ();
    static final String api_base_url = "http://project2-burgerx-database-api.herokuapp.com/customer/";

    @Override
    protected Customer doInBackground(Customer...args) {



        //Create a Json object
        JsonObject customer_json_object = args[0].createCustomerJson();

        String url_string = api_base_url + "add";
        try {

            URL url = new URL(url_string);
            HttpURLConnection request = (HttpURLConnection) url.openConnection();

            request.setDoOutput(true);
            request.setDoInput(true);
            request.setRequestProperty("Content-Type", "application/json");
            request.setRequestProperty("Accept", "application/json");
            request.setRequestMethod("POST");

            OutputStreamWriter out = new OutputStreamWriter(request.getOutputStream());
            out.write(customer_json_object.toString());
            out.flush();

            StringBuilder sb = new StringBuilder();
            int HttpResult = request.getResponseCode();
            if (HttpResult == HttpURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(request.getInputStream(), "utf-8"));
                String line = null;
                while ((line = br.readLine()) != null) {
                    sb.append(line + "\n");
                }
                br.close();
                System.out.println("" + sb.toString());
            } else {
                System.out.println(request.getResponseMessage());
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
        //Do something in here
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
