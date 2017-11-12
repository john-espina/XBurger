package helpers;

import java.util.concurrent.ExecutionException;

import api_communicators.CustomerAddToDBAPI;
import entity.Customer;
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

    public static boolean addCustomerToDB(Customer customer){
        CustomerAddToDBAPI addToDBAPI = new CustomerAddToDBAPI ();

        //Need to get the customer and do something with it

        addToDBAPI.execute(customer);

        return false;
    }
}
