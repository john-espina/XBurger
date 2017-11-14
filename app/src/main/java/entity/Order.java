package entity;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.ArrayList;

/**
 * This Class stores the order details and post it back to the server/api for the the database
 * to be updated
 * Created by johnny on 11/4/2017.
 */

public class Order implements Serializable {

    int order_id;
    Staff staff;
    Customer customer;
    String order_datetime;
    String status;
    ArrayList<Item> items;

    public Order(int order_id, Staff staff, Customer customer, String order_datetime, String status, ArrayList<Item> items) {
        this.order_id = order_id;
        this.staff = staff;
        this.customer = customer;
        this.order_datetime = order_datetime;
        this.status = status;
        this.items = items;
    }

    public Order(int customer_id, ArrayList<Item> items) {
        this.items = items;
        this.customer = new Customer(customer_id, null, null, null, -1, null, null, null, null);
    }

    public int getOrder_id() {
        return order_id;
    }

    public Staff getStaff() {
        return staff;
    }

    public Customer getCustomer() {
        return customer;
    }

    public String getOrder_datetime() {
        return order_datetime;
    }

    public String getStatus() {
        return status;
    }

    public double getPrice() {

        double price = 0.0;

        for (Item item : items) {

            for (Stock ingredient : item.getIngredients()) {

                price += ingredient.getPrice();

            }

        }

        return price;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setOrder_datetime(String order_datetime) {
        this.order_datetime = order_datetime;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public JsonObject createOrderJson () {

        JsonObject orderObject = new JsonObject();
        JsonArray orderItems = new JsonArray();

        for (int i = 0; i < items.size(); i++) {

            Item item = items.get(i);

            JsonArray item_ingredients = new JsonArray();
            for (int j = 0; j < item.getIngredients().size(); j++) {
                item_ingredients.add(item.getIngredients().get(j).getIngredient_id());
            }

            orderItems.add(item_ingredients);

        }

        orderObject.add("item_details_list", orderItems);

        return orderObject;
    }
}




