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


public class SaladsFragment extends Fragment {

    View rootView;
    RadioGroup rg;
    RadioGroup rg2;
    RadioButton lettuce;
    RadioButton tomato;
    RadioButton onion;
    RadioButton redOnion;
    RadioButton beetroot;
    RadioButton pickle;
    RadioButton capsicum;
    RadioButton olives;
    RadioButton cucumber;
    ArrayList<RadioButton> radioButtons;
    HashMap<String, ArrayList> stocks;
    ArrayList<Stock> salads;


    public SaladsFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_salads, container, false);
        rg = (RadioGroup) rootView.findViewById(R.id.radiogroup_salad_choices);
        rg2 = (RadioGroup) rootView.findViewById(R.id.radiogroup_salad_choices2);
        lettuce = (RadioButton) rootView.findViewById(R.id.salad_lettuce);
        tomato =(RadioButton) rootView.findViewById(R.id.salad_tomato);
        onion = (RadioButton) rootView.findViewById(R.id.salad_onion);
        redOnion =(RadioButton) rootView.findViewById(R.id.salad_red_onion);
        beetroot = (RadioButton) rootView.findViewById(R.id.salad_beetroot);
        pickle = (RadioButton) rootView.findViewById(R.id.salad_pickle);
        capsicum = (RadioButton) rootView.findViewById(R.id.salad_capsicum);
        olives = (RadioButton) rootView.findViewById(R.id.salad_olives);
        cucumber = (RadioButton) rootView.findViewById(R.id.salad_cucumber);

        radioButtons = StockControls.createRadioButtonList(rg,rg2);


        try{
            //if statement here if previosly clicked so wont have to query databse again
            stocks = new StockDetailsController().execute().get();
            salads = stocks.get("cheeseCategory");

        }catch (InterruptedException e){
            e.printStackTrace();
        } catch (ExecutionException e){
            e.printStackTrace();
        }

        StockControls.updateStockView(salads,radioButtons);

        return  rootView;
    }


}
