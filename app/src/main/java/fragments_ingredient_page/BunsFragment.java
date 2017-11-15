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
import java.util.concurrent.ExecutionException;

import entity.Stock;
import api_communicators.StockDetailsController;
import helpers.StockControls;


/**
 * A simple {@link Fragment} subclass.
 */
public class BunsFragment extends Fragment {

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

        //if statement here if previously clicked so won't need to query the database again

        // Retrieve All stock from this category
        // Retrieve Available stock from this category
        // Compare the two

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

            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                int count = radioGroup.getChildCount();

                //For all the other radio buttons in this group set the to false.
                ArrayList<RadioButton> listOfRadioButtons = new ArrayList<RadioButton>();
                for (int i = 0;i < count;i++) {
                    View o = radioGroup.getChildAt(i);
                    if (o instanceof RadioButton) {
                        MainActivity.selectedStock.put(o.getId (), false);
                        Log.d ("RG", "" + o.getId () + MainActivity.selectedStock.get(o.getId()));
                    }
                }

                //Set the new selection to true
                BurgerAppLayout.selectedStock.put (checkedId, true);
            }
        });

        return rootView;
    }

//    @Override
//    public void onDetach() {
//        super.onDetach();
//
//        try {
//            Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
//            childFragmentManager.setAccessible(true);
//            childFragmentManager.set(this, null);
//
//        } catch (NoSuchFieldException e) {
//            throw new RuntimeException(e);
//        } catch (IllegalAccessException e) {
//            throw new RuntimeException(e);
//        }
//    }
}
