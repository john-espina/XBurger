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


public class MeatFragments extends Fragment {

    View rootView;
    RadioGroup rg;
    ArrayList<RadioButton> radioButtons = new ArrayList<>();
    HashMap<String,ArrayList> stocks = new HashMap<>();
    HashMap<String,ArrayList> allStocks = new HashMap<>();
    ArrayList<Stock> patties = new ArrayList<>();
    ArrayList<Stock> allPatties = new ArrayList<>();


    public MeatFragments() {
        // Required empty public constructor
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_meat_fragments, container, false);
        rg = (RadioGroup) rootView.findViewById(R.id.radiogroup_meat_choices);

        //if statement here if previously clicked so won't need to query the database again
        allStocks = MainActivity.getAllStockHashMap();
        stocks =  MainActivity.getAvailableStocksHashMap();
        allPatties = allStocks.get("pattieCategory");
        patties = stocks.get("pattieCategory");

        //Create and add radiobuttons to radiogroup from current stocks
        radioButtons = StockControls.generateRadioButtons(rg,this, allPatties);

        //create arraylist of radiobuttons
        //radioButtonArrayList = StockControls.createRadioButtonList(rg);

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
                        BurgerAppLayout.selectedStock.put(o.getId (), false);
                        Log.d ("RG", "" + o.getId () + BurgerAppLayout.selectedStock.get(o.getId()));
                    }
                }

                //Set the new selection to true
                BurgerAppLayout.selectedStock.put (checkedId, true);
            }
        });

        //compare radiobuttonarraylist to available stocks
        StockControls.updateStockViewOfRadioButtons(patties, radioButtons );

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
