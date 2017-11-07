package com.example.espinajohn.xburger;

import android.util.Log;

import java.util.concurrent.ExecutionException;

import api_communicators.Customer;
import api_communicators.CustomerDetailsController;

/**
 * Created by stlaumade on 3/11/2017.
 */

public class CustomerControls {

    public static Customer createCustomer(String username){
        Customer customer = null;
        String loginMethod;
        CustomerDetailsController customerDetails = new CustomerDetailsController();

        if (username.contains("@")){
            loginMethod = "email/";
        } else {
            loginMethod = "username/";
        }

        customerDetails.execute(loginMethod,username);

        try {
            if (customerDetails.get() !=null){
                try {
                    return customer = customerDetails.get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        return null;

    }

    public boolean validateCredentials(String name, String password){
        //
        if (validateUsername (name) && validatePassword(password)){
            return true;
        }
        return false;
    }

    public static boolean validatePassword(String password){
        //Get the customer
        //Get the hash
        //Get the salt
        //Get the iterations
        return false;
    }

    public static boolean validateUsername(String username){
        //Do something
        if (createCustomer (username) != null){
            return true;
        }
        return false;
    }
}
