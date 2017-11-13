package fragments_ingredient_page;

import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import com.example.espinajohn.xburger.MainActivity;
import com.example.espinajohn.xburger.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import entity.Stock;
import api_communicators.StockDetailsController;
import helpers.StockControls;


public class SaladsFragment extends Fragment {

    View rootView;
    LinearLayout rg;
    ArrayList<CheckBox> radioButtonArrayList = new ArrayList<>();
    HashMap<String, ArrayList> stocks = new HashMap<>();
    ArrayList<Stock> salads = new ArrayList<>();
    HashMap<String, ArrayList> allStocks = new HashMap<>();
    ArrayList<Stock>allSalads = new ArrayList<>();



    public SaladsFragment() {
        // Required empty public constructor
    }



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_salads, container, false);
        rg = (LinearLayout) rootView.findViewById(R.id.radiogroup_salad_choices);

        try {
            //if statement here if previously clicked so won't need to query the database again
            allStocks = MainActivity.getStockHashMap();
            stocks =  new StockDetailsController().execute().get();
            allSalads = allStocks.get("saladCategory");
            salads = stocks.get("saladCategory");

            //Create and add radiobuttons to radiogroup from current stocks
            radioButtonArrayList = StockControls.generateCheckBoxes(rg,this, allSalads);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        //create arraylist of radiobuttons
        //radioButtonArrayList = StockControls.createRadioButtonList(rg);



        //compare radiobuttonarraylist to available stocks
        StockControls.updateStockViewOfCheckBoxes(salads, radioButtonArrayList );
        
        return  rootView;
    }



}
