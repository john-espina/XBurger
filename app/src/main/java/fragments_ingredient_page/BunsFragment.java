package fragments_ingredient_page;


import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.espinajohn.xburger.BurgerAppLayout;
import com.example.espinajohn.xburger.MainActivity;
import com.example.espinajohn.xburger.R;

import java.util.ArrayList;
import java.util.HashMap;

import entity.Stock;
import helpers.StockControls;


/**
 * A simple {@link Fragment} subclass.
 * This Fragment class is responsible for the activity under the Bun Category
 * This class extends Fragment
 * I don't think there is a need to implement all the methods; as far this app, the this Fragment class only requires
 * an empty public constructor and the callback function onCreateView.
 * Treat this Fragment class as another activity, so all elements inside this Fragment will be define here.
 * It will also have it's own xml file.
 * However, there is only one Fragment Placeholder inserted into the main activity xml. That is, multiple Fragment xml files,
 * but only one placeholder inside the main activity xml.
 */
public class BunsFragment extends Fragment {

    // Declare all objects that willbe used in this acitivity
    RadioGroup rg;
    View rootView;
    ArrayList<Stock> buns = new ArrayList<>();
    ArrayList<Stock> allBuns = new ArrayList<>();
    ArrayList<RadioButton> radioButtonArrayList = new ArrayList<>();
    HashMap<String,ArrayList> stocks = new HashMap<>();
    HashMap<String,ArrayList> allStocks = new HashMap<>();


    public BunsFragment() {
        // Required empty public constructor
    }



    
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_buns, container, false);
        rg = (RadioGroup) rootView.findViewById(R.id.radiogroup_bun_choices);

        allStocks = MainActivity.getAllStockHashMap(); // this is a HashMap of ALL of the stocks across all category (querried when app was started onCreate())
        stocks =  MainActivity.getAvailableStocksHashMap(); // also a HashMap of all Stocks, but only the available ones
        allBuns = allStocks.get("bunCategory"); //this is a list of ALL the buns
        buns = stocks.get("bunCategory"); // this is a list of Available buns


        // Create and add Radiobuttons to the Radiogroup (rg) based on ALL current Stocks from this category
        radioButtonArrayList = StockControls.generateRadioButtons(rg,this, allBuns);


        // Compare RadiobuttonArrayList to available stocks from this category
        // Update the Fragment View
        StockControls.updateStockViewOfRadioButtons(buns, radioButtonArrayList );

        //Set the hashmap to true/false when button is clicked
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){

            // All logic related to this activity will be defined here
            // That includes Listeners
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                int count = radioGroup.getChildCount();

                //For all the other radio buttons in this group set the to false.
                ArrayList<RadioButton> listOfRadioButtons = new ArrayList<RadioButton>();
                for (int i = 0;i < count;i++) {
                    View o = radioGroup.getChildAt(i);
                    if (o instanceof RadioButton) {
                        BurgerAppLayout.selectedStock.put(o.getId (), false);
                        Log.d ("Selected Bun", "" + ((RadioButton) o).getText());
                    }
                }

                //Set the new selection to true
                BurgerAppLayout.selectedStock.put (checkedId, true);
            }
        });

        return rootView;
    }

}
