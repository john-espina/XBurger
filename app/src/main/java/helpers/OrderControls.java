package helpers;

import api_communicators.AddOrderToDB;
import entity.Order;

/**
 * Created by stlaumade on 14/11/2017.
 */

public class OrderControls {

    public static void addOrderToDB(Order order) {
        AddOrderToDB addOrder = new AddOrderToDB ();
        addOrder.execute (order);

    }
}
