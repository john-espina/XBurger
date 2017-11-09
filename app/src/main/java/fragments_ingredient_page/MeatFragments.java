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


public class MeatFragments extends Fragment {

    View rootView;
    RadioGroup rg;
    RadioGroup rg2;
    RadioButton beef;
    RadioButton chicken;
    RadioButton falafel;
    RadioButton tofu;
    RadioButton pork;
    RadioButton lamb;
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
//        rg2= (RadioGroup) rootView.findViewById(R.id.radiogroup_meat_choices2);
//        beef = (RadioButton)rootView.findViewById(R.id.pattie_beef);
//        chicken = (RadioButton)rootView.findViewById(R.id.pattie_chicken);
//        falafel = (RadioButton)rootView.findViewById(R.id.pattie_falafel);
//        tofu = (RadioButton)rootView.findViewById(R.id.pattie_tofu);
//        pork = (RadioButton)rootView.findViewById(R.id.pattie_pork);
//        lamb = (RadioButton)rootView.findViewById(R.id.pattie_lamb);

        try {
            //if statement here if previously clicked so won't need to query the database again
            allStocks = MainActivity.getStockHashMap();
            stocks =  new StockDetailsController().execute().get();
            allPatties = allStocks.get("pattieCategory");
            patties = stocks.get("pattieCategory");

            //Create and add radiobuttons to radiogroup from current stocks
            radioButtons = StockControls.generateRadioButtonItem(rg,this, allPatties);



        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        //create arraylist of radiobuttons
        //radioButtonArrayList = StockControls.createRadioButtonList(rg);

        //compare radiobuttonarraylist to available stocks
        StockControls.updateStockView(patties, radioButtons );



        return rootView;
    }




}
