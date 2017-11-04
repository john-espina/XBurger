package api_communicators;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * This class talks to the API to retrive customer details when a customer logs in to the app
 * Created by johnny on 11/4/2017.
 */

public class CustomerVerifier extends AsyncTask <String, Integer, String> {

    private String customerEmail = null;
    private String customerPassHash = null;

    String apiURL = "http://project2-burgerx-database-api.herokuapp.com/customer/";

    @Override
    protected String doInBackground(String...args) {
        String finalJson ="";
        String output;
        try {
            URL url = new URL (apiURL + args[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            StringBuffer json = new StringBuffer();

            while ((output=reader.readLine())!=null)
                json.append(output);

            connection.disconnect();

            finalJson = new String(json.toString());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("url", apiURL + args[0]);
        return finalJson;
    }

    protected void onProgressUpdate (Integer...progress){

    }

    protected  void onPostExecute (String result){
        processCustomerData(result);
    }

    private void processCustomerData (String result){
        JSONObject customerJSONObject = null;

        try {
            customerJSONObject = new JSONObject(result);
        } catch (JSONException e){
            e.printStackTrace();
        }


        try{
            setCustomerEmail(customerJSONObject.getString("Email"));
            setCustomerPassHash(customerJSONObject.getString("PassHash"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

            Log.d("Email", getCustomerEmail());
            Log.d("PassHash", getCustomerPassHash());

        }




    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerPassHash() {
        return customerPassHash;
    }

    public void setCustomerPassHash(String customerPassHash) {
        this.customerPassHash = customerPassHash;
    }
}
