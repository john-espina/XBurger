package entity;
import com.google.gson.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import passwords.Passwords;

/**
 * Created by Julian Lees on 5/11/2017.
 */
public class Customer implements Serializable {

    int customer_id;
    String username;
    String email;
    String phone_number;
    int iterations;
    String salt;
    String passHash;
    String passPin;
    String cardToken;
    static final String api_base_url = "http://project2-burgerx-database-api.herokuapp.com/customer/";

    public Customer(){

    }

    // Constructor for Customer object with full suite of details as pulled from the database
    public Customer(int customer_id, String username, String email, String phone_number, int iterations, String salt, String passHash, String passPin, String cardToken) {
        this.customer_id = customer_id;
        this.username = username;
        this.email = email;
        this.phone_number = phone_number;
        this.iterations = iterations;
        this.salt = salt;
        this.passHash = passHash;
        this.passPin = passPin;
        this.cardToken = cardToken;
    }

    // Constructor for "prospective" customer with full suite of details (minus customer_id which will be assigned on registration
    public Customer(String username, String email, String phone_number, int iterations, String salt, String passHash, String passPin, String cardToken) {
        this.username = username;
        this.email = email;
        this.phone_number = phone_number;
        this.iterations = iterations;
        this.salt = salt;
        this.passHash = passHash;
        this.passPin = passPin;
        this.cardToken = cardToken;
    }

    public boolean validateCustomerPassword(String password_entry, String hashDB, String saltDB, int iterationsDB) {

        //Use Passwords class to compare password_entry with hash from the database
        byte[] salt_bytes = Passwords.base64Decode(saltDB);
        byte[] hash_bytes = Passwords.base64Decode(hashDB);

        boolean password_validation = Passwords.isExpectedPassword(password_entry.toCharArray(), salt_bytes, iterationsDB, hash_bytes);

        return password_validation;
    }

    public JsonObject createCustomerJson() {
        JsonObject customer_json_object = new JsonObject();
        customer_json_object.addProperty("Username", this.username);
        customer_json_object.addProperty("Email", this.email);
        customer_json_object.addProperty("Phone_Number", this.phone_number);
        customer_json_object.addProperty("Iterations", this.iterations);
        customer_json_object.addProperty("Salt", this.salt);
        customer_json_object.addProperty("PassHash", this.passHash);
        customer_json_object.addProperty("PassPin", this.passPin);
        customer_json_object.addProperty("Card_Token", this.cardToken);

        return customer_json_object;

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
