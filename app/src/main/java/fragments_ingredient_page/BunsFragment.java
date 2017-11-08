package fragments_ingredient_page;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.app.Fragment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

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
    RadioButton bunWhite;
    RadioButton bunWholemeal;
    RadioButton bunSordough;
    RadioButton bunGlutenFree;
    View rootView;
    ArrayList<Stock> buns = new ArrayList<>();
    ArrayList<String> ingredientNames = new ArrayList<>();
    ArrayList<RadioButton> radioButtonArrayList = new ArrayList<>();
    HashMap<String,ArrayList> stocks = new HashMap<>();

    public BunsFragment() {
        // Required empty public constructor
    }
    
    @SuppressLint("ResourceAsColor")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_buns, container, false);
        rg = (RadioGroup) rootView.findViewById(R.id.radiogroup_bun_choices);
        bunWhite = (RadioButton) rootView.findViewById(R.id.bun_white);
        bunWholemeal = (RadioButton) rootView.findViewById(R.id.bun_wholemeal);
        bunSordough = (RadioButton) rootView.findViewById(R.id.bun_sourdough);
        bunGlutenFree =(RadioButton) rootView.findViewById(R.id.bun_gluten_free);


        radioButtonArrayList = StockControls.createRadioButtonList(rg);

        try {
            //if statement here if previously clicked so won't need to query the database again
            stocks =  new StockDetailsController().execute().get();
            buns = stocks.get("bunCategory");



        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        StockControls.updateStockView(buns, radioButtonArrayList );






        return rootView;
    }



}
