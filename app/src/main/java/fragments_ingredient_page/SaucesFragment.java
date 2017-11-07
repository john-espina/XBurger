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


    public SaucesFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       rootView = inflater.inflate(R.layout.fragment_sauces, container, false);
       rg = (RadioGroup) rootView.findViewById(R.id.radiogroup_sauce_choices);
       rg2 = (RadioGroup) rootView.findViewById(R.id.radiogroup_sauce_choices2);
       halloumi = (RadioButton) rootView.findViewById(R.id.sauce_halloumi);
       tomatoSauce = (RadioButton) rootView.findViewById(R.id.sauce_tomato_sauce);
       aioli = (RadioButton) rootView.findViewById(R.id.sauce_aioli);
       mayonnaise = (RadioButton) rootView.findViewById(R.id.sauce_mayonnaise);
       americanMustard = (RadioButton) rootView.findViewById(R.id.sauce_american_mustard);
       dijonMustard = (RadioButton) rootView.findViewById(R.id.sauce_dijon_mustard);
       honeyMustard = (RadioButton) rootView.findViewById(R.id.sauce_honey_mustard);
       mintSauce = (RadioButton) rootView.findViewById(R.id.sauce_mint);
       brownSauce = (RadioButton) rootView.findViewById(R.id.sauce_brown);


       return  rootView;
    }


}
