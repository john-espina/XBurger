package fragments_ingredient_page;


import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.espinajohn.xburger.MainActivity;
import com.example.espinajohn.xburger.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import api_communicators.Stock;
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
        try {

            allStocks = MainActivity.getStockHashMap(); // this is a HashMap of ALL of the stocks across all category (querried when app was started onCreate())
            stocks =  new StockDetailsController().execute().get();  // also a HashMap of all Stocks, but only the available ones
            allBuns = allStocks.get("bunCategory"); //this is a list of ALL the buns
            buns = stocks.get("bunCategory"); // this is a list of Available buns


            // Create and add Radiobuttons to the Radiogroup (rg) based on ALL current Stocks from this category
            radioButtonArrayList = StockControls.generateRadioButtonItem(rg,this, allBuns);


        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        // Compare RadiobuttonArrayList to available stocks from this category
        // Update the Fragment View
        StockControls.updateStockViewRadioButton(buns, radioButtonArrayList );

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                //Add selected ingredient to Order
            }
        });


        return rootView;
    }
}
