package com.example.espinajohn.xburger;

import android.app.Activity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import entity.Item;
import entity.Order;
import entity.Stock;

/**
 * Created by stlaumade on 16/11/2017.
 * John Lerke showed me how to implement this
 */

public class ResourceSave implements Serializable{

    private Order master_order;
    private Boolean app_logged_in;
    private int customer_id;
    private int currentLayout;
    private HashMap<Integer, Boolean> selectedStock;
    private ArrayList<Item> listofitems;

    public ResourceSave(Order order, Boolean login, int c_id, int cust_lay, HashMap<Integer,Boolean> sstock, ArrayList<Item> list) {
        this.master_order = order;
        this.app_logged_in = login;
        this.customer_id = c_id;
        this.currentLayout = cust_lay;
        this.selectedStock = sstock;
        this.listofitems = list;
        }



    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public Order getMaster_order() {
        return master_order;
    }

    public void setMaster_order(Order master_order) {
        this.master_order = master_order;
    }

    public Boolean getApp_logged_in() {
        return app_logged_in;
    }

    public void setApp_logged_in(Boolean app_logged_in) {
        this.app_logged_in = app_logged_in;
    }

    public int getCurrentLayout() {
        return currentLayout;
    }

    public void setCurrentLayout(int currentLayout) {
        this.currentLayout = currentLayout;
    }

    public HashMap<Integer, Boolean> getSelectedStock() {
        return selectedStock;
    }

    public void setSelectedStock(HashMap<Integer, Boolean> selectedStock) {
        this.selectedStock = selectedStock;
    }

    public ArrayList<Item> getListofitems() {
        return listofitems;
    }

    public void setListofitems(ArrayList<Item> listofitems) {
        this.listofitems = listofitems;
    }
}

