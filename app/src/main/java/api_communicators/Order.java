package api_communicators;

import android.content.ClipData;

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
    DateFormat order_datetime;
    int status;
    ArrayList<Item> items;

    public Order(int order_id, Staff staff, Customer customer, DateFormat order_datetime, int status, ArrayList<Item> items) {
        this.order_id = order_id;
        this.staff = staff;
        this.customer = customer;
        this.order_datetime = order_datetime;
        this.status = status;
        this.items = items;
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

    public DateFormat getOrder_datetime() {
        return order_datetime;
    }

    public int getStatus() {
        return status;
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

    public void setOrder_datetime(DateFormat order_datetime) {
        this.order_datetime = order_datetime;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }
}
