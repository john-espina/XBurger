package fragments_ingredient_page;

import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import com.example.espinajohn.xburger.MainActivity;
import com.example.espinajohn.xburger.R;

import java.util.ArrayList;
import java.util.HashMap;

import entity.Stock;
import helpers.StockControls;


public class SaucesFragment extends Fragment {


    View rootView;
    LinearLayout rg;
    ArrayList<CheckBox> radioButtons = new ArrayList<>();
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
       rg = (LinearLayout) rootView.findViewById(R.id.radiogroup_sauce_choices);

        //if statement here if previously clicked so won't need to query the database again
        allStocks = MainActivity.getAllStockHashMap();
        stocks =  MainActivity.getAvailableStocksHashMap();
        allSauces = allStocks.get("sauceCategory");
        sauces = stocks.get("sauceCategory");

        //Create and add radiobuttons to radiogroup from current stocks
        radioButtons = StockControls.generateCheckBoxes(rg,this, allSauces);

        //create arraylist of radiobuttons
        //radioButtonArrayList = StockControls.createRadioButtonList(rg);

        //compare radiobuttonarraylist to available stocks
        StockControls.updateStockViewOfCheckBoxes(sauces, radioButtons );

       return  rootView;
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
