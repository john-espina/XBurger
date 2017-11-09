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


public class SaucesFragment extends Fragment {


    View rootView;
    RadioGroup rg;
    RadioGroup rg2;
    RadioButton halloumi;
    RadioButton tomatoSauce;
    RadioButton aioli;
    RadioButton mayonnaise;
    RadioButton americanMustard;
    RadioButton dijonMustard;
    RadioButton honeyMustard;
    RadioButton mintSauce;
    RadioButton brownSauce;
    ArrayList<RadioButton> radioButtons = new ArrayList<>();
    HashMap<String, ArrayList> stocks = new HashMap<>();
    HashMap<String,ArrayList> allStocks = new HashMap<>();
    ArrayList<Stock> sauces = new ArrayList<>();
    ArrayList<Stock> allSauces = new ArrayList<>();


    public SaucesFragment() {
        // Required empty public constructor
    }



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       rootView = inflater.inflate(R.layout.fragment_sauces, container, false);
       rg = (RadioGroup) rootView.findViewById(R.id.radiogroup_sauce_choices);
//       rg2 = (RadioGroup) rootView.findViewById(R.id.radiogroup_sauce_choices2);
//       halloumi = (RadioButton) rootView.findViewById(R.id.sauce_halloumi);
//       tomatoSauce = (RadioButton) rootView.findViewById(R.id.sauce_tomato_sauce);
//       aioli = (RadioButton) rootView.findViewById(R.id.sauce_aioli);
//       mayonnaise = (RadioButton) rootView.findViewById(R.id.sauce_mayonnaise);
//       americanMustard = (RadioButton) rootView.findViewById(R.id.sauce_american_mustard);
//       dijonMustard = (RadioButton) rootView.findViewById(R.id.sauce_dijon_mustard);
//       honeyMustard = (RadioButton) rootView.findViewById(R.id.sauce_honey_mustard);
//       mintSauce = (RadioButton) rootView.findViewById(R.id.sauce_mint);
//       brownSauce = (RadioButton) rootView.findViewById(R.id.sauce_brown);


        try {
            //if statement here if previously clicked so won't need to query the database again
            allStocks = MainActivity.getStockHashMap();
            stocks =  new StockDetailsController().execute().get();
            allSauces = allStocks.get("sauceCategory");
            sauces = stocks.get("sauceCategory");

            //Create and add radiobuttons to radiogroup from current stocks
            radioButtons = StockControls.generateRadioButtonItem(rg,this, allSauces);



        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        //create arraylist of radiobuttons
        //radioButtonArrayList = StockControls.createRadioButtonList(rg);

        //compare radiobuttonarraylist to available stocks
        StockControls.updateStockView(sauces, radioButtons );


       return  rootView;
    }


}
