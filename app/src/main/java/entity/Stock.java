package entity;

import java.io.Serializable;

import helpers.StockControls;

/**
 * Created by stlaumade on 7/11/2017.
 */

public class Stock implements Serializable {

    int ingredient_id;
    String ingredient_name;
    String category;
    private int categoryID;
    int stock_level;
    double price;
    String img_file_name;

    public Stock(int ingredient_id, String ingredient_name, String category, int stock_level, double price, String img_file_name) {
        this.ingredient_id = ingredient_id;
        this.ingredient_name = ingredient_name;
        this.category = category;
        this.stock_level = stock_level;
        this.price = price;
        this.img_file_name = img_file_name;
    }

    public Stock(int ingredient_id) {
        this.ingredient_id = ingredient_id;
        this.ingredient_name = StockControls.getIngredientName(ingredient_id);
        this.category = StockControls.getItemCategory(ingredient_id);
        this.stock_level = -1;
        this.price = StockControls.getIngredientPrice(ingredient_id);
        this.img_file_name = "";
    }

    public int getIngredient_id() {
        return ingredient_id;
    }

    public String getIngredient_name() {
        return ingredient_name;
    }

    public String getCategory() {
        return category;
    }

    public int getStock_level() {
        return stock_level;
    }

    public double getPrice() {
        return price;
    }

    public String getImg_file_name() {
        return img_file_name;
    }

    public void setIngredient_id(int ingredient_id) {
        this.ingredient_id = ingredient_id;
    }

    public void setIngredient_name(String ingredient_name) {
        this.ingredient_name = ingredient_name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setStock_level(int stock_level) {
        this.stock_level = stock_level;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setImg_file_name(String img_file_name) {
        this.img_file_name = img_file_name;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }
}
