package entity;

import java.io.Serializable;
import java.util.ArrayList;

import entity.Stock;
import helpers.StockControls;

/**
 * This class talks to the API and retrieves ingredient list to be displayed on the ingredient screen
 * Created by johnny on 11/4/2017.
 */

public class Item implements Serializable {

    int order_details_id;
    ArrayList<Stock> ingredients;
    int order_id;
    String item_type;

    public Item(int order_details_id, int order_id, ArrayList<Stock> ingredients, String item_type) {
        this.order_details_id = order_details_id;
        this.ingredients = ingredients;
        this.order_id = order_id;
        this.item_type = item_type;

        if(this.ingredients.get(0).getCategory().equals("Side")) {
            this.item_type = "side";
        } else if (this.ingredients.get(0).getCategory().equals("Drink")) {
            this.item_type = "drink";
        } else {
            this.item_type = "burger";
        }
    }

    public Item(ArrayList<Stock> ingredients) {

        this.order_details_id = -1;
        this.ingredients = ingredients;
        this.order_id = -1;
        //this.item_type = StockControls.getItemCategory(ingredients.get(0).getIngredient_id());

    }

    public int getOrder_details_id() {
        return order_details_id;
    }

    public ArrayList<Stock> getIngredients() {
        return ingredients;
    }

    public int getOrder_id() {
        return order_id;
    }

    public String getItem_type() {
        return item_type;
    }

    public void setOrder_details_id(int order_details_id) {
        this.order_details_id = order_details_id;
    }

    public void setIngredients(ArrayList<Stock> ingredients) {
        this.ingredients = ingredients;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public void setItem_type(String item_type) {
        this.item_type = item_type;
    }
}
