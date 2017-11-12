package fragments_ingredient_page;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.RequiresApi;
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


public class CheeseFragment extends Fragment {

    View rootView;
    RadioGroup rg;
    ArrayList<RadioButton> radioButtonArrayList = new ArrayList<>();
    HashMap<String, ArrayList> stocks = new HashMap<>();
    ArrayList<Stock> cheeses = new ArrayList<>();
    HashMap<String,ArrayList> allStocks = new HashMap<>();
    ArrayList<Stock> allCheese;

    public CheeseFragment() {
        // Required empty public constructor
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       rootView =inflater.inflate(R.layout.fragment_cheese, container, false);
       rg = (RadioGroup)rootView.findViewById(R.id.radiogroup_cheese_options);

        try {
            //if statement here if previously clicked so won't need to query the database again
            allStocks = MainActivity.getStockHashMap();
            stocks =  new StockDetailsController().execute().get();
            allCheese = allStocks.get("cheeseCategory");
            cheeses = stocks.get("cheeseCategory");

            //Create and add radiobuttons to radiogroup from current stocks
            radioButtonArrayList = StockControls.generateRadioButtonItem(rg,this, allCheese);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        //create arraylist of radiobuttons
        //radioButtonArrayList = StockControls.createRadioButtonList(rg);

        //compare radiobuttonarraylist to available stocks
        StockControls.updateStockViewRadioButton(cheeses, radioButtonArrayList );

       return rootView;
    }

}
