package fragments_ingredient_page;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
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


public class CheeseFragment extends Fragment {

    View rootView;
    RadioGroup rg;
    RadioButton swiss;
    RadioButton edam;
    RadioButton smoked;
    RadioButton brie;
    ArrayList<RadioButton> radioButtons;
    HashMap<String, ArrayList> stocks;
    ArrayList<Stock> cheeses;

    public CheeseFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       rootView =inflater.inflate(R.layout.fragment_cheese, container, false);
       rg = (RadioGroup)rootView.findViewById(R.id.radiogroup_cheese_options);
       swiss = (RadioButton) rootView.findViewById(R.id.cheese_swiss);
       edam = (RadioButton) rootView.findViewById(R.id.cheese_edam);
       smoked = (RadioButton) rootView.findViewById(R.id.cheese_smoke);
       brie = (RadioButton) rootView.findViewById(R.id.cheese_brie);

       radioButtons = StockControls.createRadioButtonList(rg);

       try{
           //if statement here if previosly clicked so wont have to query databse again
           stocks = new StockDetailsController().execute().get();
           cheeses = stocks.get("cheeseCategory");

       }catch (InterruptedException e){
           e.printStackTrace();
       } catch (ExecutionException e){
           e.printStackTrace();
       }

       StockControls.updateStockView(cheeses,radioButtons);

       return rootView;
    }

}
