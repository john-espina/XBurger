package helpers;

import android.util.Log;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import api_communicators.AddOrderToDB;
import api_communicators.GetOrderListFromDB;
import entity.Order;

/**
 * Created by stlaumade on 14/11/2017.
 */

public class OrderControls {

    public static void addOrderToDB(Order order) {
        AddOrderToDB addOrder = new AddOrderToDB ();
        addOrder.execute (order);
    }

    public static ArrayList<Order> retrieveAllOrders(int customer_id){

        Log.d ("Customer ID - Controls", "" + customer_id);
        GetOrderListFromDB getOrders = new GetOrderListFromDB ();
        ArrayList<Order> orders = new ArrayList<Order>();

        try {
            orders = getOrders.execute (customer_id).get ();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return orders;
    }
}
