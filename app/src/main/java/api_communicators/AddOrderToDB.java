package api_communicators;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import entity.Order;

import static api_communicators.CustomerAddToDBAPI.api_base_url;

/**
 * Created by stlaumade on 14/11/2017.
 */

public class AddOrderToDB extends AsyncTask <Order, Integer, Order> {

    static final String api_base_url = "http://project2-burgerx-database-api.herokuapp.com/order/";

    @Override
    protected Order doInBackground(Order... args) {

        //Need to pass the order through the arguments and then use that to call this method and find its customer id
        Order order = args[0];
        JsonObject order_json_object = order.createOrderJson ();

        String url_string = api_base_url + "add/" + order.getCustomer ().getCustomer_id ();

        try {

            URL url = new URL (url_string);
            HttpURLConnection request = (HttpURLConnection) url.openConnection ();

            request.setDoOutput (true);
            request.setDoInput (true);

            request.setRequestProperty ("Content-Type", "application/json");
            request.setRequestProperty ("Accept", "application/json");
            request.setRequestMethod ("POST");

            OutputStreamWriter out = new OutputStreamWriter (request.getOutputStream ());
            out.write (order_json_object.toString ());
            out.flush ();

            StringBuilder sb = new StringBuilder ();
            int HttpResult = request.getResponseCode ();

            if (HttpResult == HttpURLConnection.HTTP_OK) {

                BufferedReader br = new BufferedReader (new InputStreamReader (request.getInputStream (), "utf-8"));
                String line = null;
                while ((line = br.readLine ()) != null) {
                    sb.append (line + "\n");
                }
                br.close ();
                Log.d ("Check", "" + sb.toString ());

            } else {
                Log.d ("Check", "" + request.getResponseMessage ());

            }

        } catch (MalformedURLException e) {
            e.printStackTrace ();
        } catch (IOException e) {
            e.printStackTrace ();
        }
        return order;
    }
}
