package com.example.espinajohn.xburger;

import android.util.Log;

import java.util.concurrent.ExecutionException;

import api_communicators.Customer;
import api_communicators.CustomerDetailsController;

/**
 * Created by stlaumade on 3/11/2017.
 */

public class CustomerControls {

    public static Customer createCustomer(String username, String loginMethod){
        CustomerDetailsController customerDetails = new CustomerDetailsController();

        if (username.contains("@")){
            loginMethod = "email/";
        } else {
            loginMethod = "username/";
        }
        customerDetails.execute(loginMethod,username);
        Customer customer = new Customer();
        try {
            customer = customerDetails.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return customer;

    }

    public static boolean validateUsername(String username, String loginMethod){
        //Do something
        if (createCustomer (username,loginMethod) != null){
            return true;
        }
        return false;
    }
}
