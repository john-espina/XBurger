package helpers;

import android.app.Fragment;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import entity.Stock;

/**
 * Created by Julian on 8/11/2017.
 */
public class StockControls {

    //Hashmap for stock names
    public static final HashMap<Integer, String> stockHash = new HashMap<>();
    //Hashmap for stock prices
    public static final HashMap<Integer, Double> stockPrice = new HashMap<>();

    static {
        stockHash.put(1, "White");
        stockHash.put(11, "Wholemeal");
        stockHash.put(21, "Sourdough");
        stockHash.put(31, "Gluten Free");
        stockHash.put(41, "Lettuce");
        stockHash.put(51, "Tomato");
        stockHash.put(61, "Onion");
        stockHash.put(71, "Red Onion");
        stockHash.put(81, "Beetroot");
        stockHash.put(91, "Pickle");
        stockHash.put(101, "Capsicum");
        stockHash.put(111, "Olives");
        stockHash.put(121, "Cucumber");
        stockHash.put(131, "Beef");
        stockHash.put(141, "Chicken");
        stockHash.put(151, "Falafel");
        stockHash.put(161, "Tofu");
        stockHash.put(171, "Pork");
        stockHash.put(181, "Lamb");
        stockHash.put(191, "Smoked");
        stockHash.put(201, "Swiss");
        stockHash.put(211, "Edam");
        stockHash.put(221, "Brie");
        stockHash.put(231, "Halloumi");
        stockHash.put(241, "Tomato Sauce");
        stockHash.put(251, "Aioli");
        stockHash.put(261, "Mayonnaise");
        stockHash.put(271, "American Mustard");
        stockHash.put(281, "Dijon Mustard");
        stockHash.put(291, "Honey Mustard");
        stockHash.put(301, "Mint Sauce");
        stockHash.put(311, "Brown Sauce");
        stockHash.put(321, "Regular Fries");
        stockHash.put(331, "Wedges");
        stockHash.put(341, "Onion Rings");
        stockHash.put(351, "Curly Fries");
        stockHash.put(361, "Ice Cream");
        stockHash.put(371, "Sundae");
        stockHash.put(381, "Gelato");
        stockHash.put(391, "Beer Battered Fries");
        stockHash.put(401, "Coke");
        stockHash.put(411, "Fanta");
        stockHash.put(421, "Sprite");
        stockHash.put(431, "Milkshake");
        stockHash.put(441, "Smoothie");
        stockHash.put(451, "Thickshake");
        stockHash.put(461, "Unicorn Tears");
        stockHash.put(471, "Power Flower");
        stockHash.put(481, "Bill's Spaghetti");
        stockHash.put(491, "One-up");
    }

    static {
        stockPrice.put(1, 1.0);
        stockPrice.put(11, 1.5);
        stockPrice.put(21, 2.5);
        stockPrice.put(31, 4.0);
        stockPrice.put(41, 0.4);
        stockPrice.put(51, 1.0);
        stockPrice.put(61, 0.2);
        stockPrice.put(71, 0.4);
        stockPrice.put(81, 0.5);
        stockPrice.put(91, 0.2);
        stockPrice.put(101, 0.5);
        stockPrice.put(111, 0.6);
        stockPrice.put(121, 0.4);
        stockPrice.put(131, 2.0);
        stockPrice.put(141, 3.0);
        stockPrice.put(151, 1.5);
        stockPrice.put(161, 1.5);
        stockPrice.put(171, 2.5);
        stockPrice.put(181, 4.0);
        stockPrice.put(191, 1.0);
        stockPrice.put(201, 1.0);
        stockPrice.put(211, 1.0);
        stockPrice.put(221, 1.5);
        stockPrice.put(231, 2.0);
        stockPrice.put(241, 0.1);
        stockPrice.put(251, 0.25);
        stockPrice.put(261, 0.1);
        stockPrice.put(271, 0.1);
        stockPrice.put(281, 0.4);
        stockPrice.put(291, 0.3);
        stockPrice.put(301, 0.3);
        stockPrice.put(311, 0.1);
        stockPrice.put(321, 2.5);
        stockPrice.put(331, 4.0);
        stockPrice.put(341, 3.5);
        stockPrice.put(351, 3.5);
        stockPrice.put(361, 1.5);
        stockPrice.put(371, 2.0);
        stockPrice.put(381, 3.0);
        stockPrice.put(391, 4.0);
        stockPrice.put(401, 1.5);
        stockPrice.put(411, 1.5);
        stockPrice.put(421, 1.5);
        stockPrice.put(431, 2.0);
        stockPrice.put(441, 2.5);
        stockPrice.put(451, 4.0);
        stockPrice.put(461, 5.0);
        stockPrice.put(471, 4.0);
        stockPrice.put(481, 10.0);
        stockPrice.put(491, 1.0);
    }

    public static int getIngredientId(String ingredient_name) {
        for (Map.Entry entry : stockHash.entrySet()) {
            if (entry.getValue().equals(ingredient_name)) {
                return Integer.parseInt(entry.getKey().toString());
            }
        }
        return -1;
    }

    public static String getIngredientName(int ingredient_id) {
        return stockHash.get(ingredient_id);
    }

    public static double getIngredientPrice(double ingredient_id) {
        return stockPrice.get(ingredient_id);
    }

    public static String getItemType(int ingredient_id) {
        if (ingredient_id < 321) {
            return "Burger";
        } else if (ingredient_id < 401) {
            return "Side";
        } else if (ingredient_id < 461) {
            return "Drink";
        } else {
            return "Special";
        }
    }

    public static String getItemCategory(int ingredient_id) {
        if (ingredient_id < 41) {
            return "Bread";
        } else if (ingredient_id < 131) {
            return "Salad";
        } else if (ingredient_id < 191) {
            return "Meat";
        } else if (ingredient_id < 241) {
            return "Cheese";
        } else if (ingredient_id < 321) {
            return "Sauce";
        } else if (ingredient_id < 401) {
            return "Side";
        } else if (ingredient_id < 461) {
            return "Drink";
        } else {
            return "Special";
        }
    }


    /**
     * This method an ArrayList or Radiobuttons
     * The list can be used to compare Radiobutton to corresponding java object
     * @param rg
     * @return
     */
    public static ArrayList <RadioButton> createRadioButtonList(RadioGroup rg){
        int count = rg.getChildCount();
        ArrayList<RadioButton> radioButtonArrayList = new ArrayList<>();
        for (int i=0;i<count;i++){
            View o = rg.getChildAt(i);
            if (o instanceof RadioButton){
                radioButtonArrayList.add((RadioButton) o);
            }
        }
        return radioButtonArrayList;
    }


    /**
     * This method updates the Fragment view of a category based on the available ingredients
     * @param stocks
     * @param radioButtonArrayList
     */
    public static void updateStockViewRadioButton(ArrayList<Stock> stocks, ArrayList<RadioButton>radioButtonArrayList){
        ArrayList<String> ingredientNames = new ArrayList<>();
        for (int i=0; i<stocks.size();i++){
            ingredientNames.add(stocks.get(i).getIngredient_name());
            Log.d("buns", stocks.get(i).getIngredient_name());
        }

        for (int i=0; i<radioButtonArrayList.size();i++){
            if (!ingredientNames.contains(radioButtonArrayList.get(i).getText().toString())) {
                radioButtonArrayList.get(i).setEnabled(false);
                radioButtonArrayList.get(i).setText(radioButtonArrayList.get(i).getText().toString() + "  ( Not Available )");

            }

        }
    }



    /**
     * This method updates the Fragment view of a category based on the available ingredients
     * @param buns
     * @param checkBoxes
     */
    public static void updateStockView(ArrayList<Stock> buns, ArrayList<CheckBox>checkBoxes){
        ArrayList<String> ingredientNames = new ArrayList<>();
        for (int i=0; i<buns.size();i++){
            ingredientNames.add(buns.get(i).getIngredient_name());
            Log.d("buns", buns.get(i).getIngredient_name());
        }

        for (int i=0; i<checkBoxes.size();i++){
            if (!ingredientNames.contains(checkBoxes.get(i).getText().toString())) {
                checkBoxes.get(i).setEnabled(false);
                checkBoxes.get(i).setText(checkBoxes.get(i).getText().toString() + "  ( Not Available )");

            }

        }
    }

    /**
     * This method generates Radiobuttons programatically based on on the elements inside an ArrayList of Stocks
     * @param rg
     * @param fragment
     * @param stocks
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static ArrayList<RadioButton> generateRadioButtonItem(RadioGroup rg, Fragment fragment, ArrayList<Stock> stocks){

        ArrayList<RadioButton> radioButtons = new ArrayList<>();
        for (int i=0; i<stocks.size(); i++){

            int categoryID = stocks.get(i).getCategoryID();
            int resourceID = idBuilder(categoryID,stocks.get(i).getIngredient_id());

                RadioButton newRadioButton = new RadioButton(fragment.getActivity());
                newRadioButton.setId(resourceID);
                newRadioButton.setText((CharSequence) stocks.get(i).getIngredient_name());
                newRadioButton.setTextColor(Color.BLACK);
                newRadioButton.setButtonTintList(ColorStateList.valueOf(Color.BLACK));
                newRadioButton.setTextSize(19);
                radioButtons.add (newRadioButton);
                rg.addView(newRadioButton);

        }
        return radioButtons;
    }


    protected static int  getCategoryID (Stock stock){
        int categoryID = stock.getCategoryID();
        return categoryID;
    }

    /**
     * Helper method to build resource id for each radiobutton created
     * @param categoryID
     * @param ingredientID
     * @return
     */
    protected  static int idBuilder (int categoryID, int ingredientID){

        int id = Integer.valueOf(String.valueOf(categoryID)+ String.valueOf(ingredientID));
        return id;

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static ArrayList<CheckBox> generateCheckBoxes(RadioGroup rg, Fragment fragment, ArrayList<Stock> stocks){

        ArrayList<CheckBox> checkBoxes = new ArrayList<>();
        for (int i=0; i<stocks.size(); i++){

            int categoryID = stocks.get(i).getCategoryID();
            int resourceID = idBuilder(categoryID,stocks.get(i).getIngredient_id());

            CheckBox newCheckBox = new CheckBox(fragment.getActivity());
            newCheckBox.setId(resourceID);
            newCheckBox.setText((CharSequence) stocks.get(i).getIngredient_name());
            newCheckBox.setTextColor(Color.BLACK);
            newCheckBox.setButtonTintList(ColorStateList.valueOf(Color.BLACK));
            newCheckBox.setTextSize(19);
            rg.addView(newCheckBox);

        }
        return checkBoxes;
    }



}