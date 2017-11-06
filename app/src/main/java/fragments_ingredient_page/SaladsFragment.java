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



public class SaladsFragment extends Fragment {

    View rootView;
    RadioGroup rg;
    RadioButton lettuce;
    RadioButton tomato;
    RadioButton onion;
    RadioButton redOnion;
    RadioButton beetroot;
    RadioButton pickle;
    RadioButton capsicum;
    RadioButton olives;
    RadioButton cucumber;


    public SaladsFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_buns, container, false);
        rg = (RadioGroup) rootView.findViewById(R.id.radiogroup_bun_choices);
        lettuce = (RadioButton) rootView.findViewById(R.id.salad_lettuce);
        tomato =(RadioButton) rootView.findViewById(R.id.salad_tomato);
        onion = (RadioButton) rootView.findViewById(R.id.salad_onion);
        redOnion =(RadioButton) rootView.findViewById(R.id.salad_red_onion);
        beetroot = (RadioButton) rootView.findViewById(R.id.salad_beetroot);
        pickle = (RadioButton) rootView.findViewById(R.id.salad_pickle);
        capsicum = (RadioButton) rootView.findViewById(R.id.salad_capsicum);
        olives = (RadioButton) rootView.findViewById(R.id.salad_olives);
        cucumber = (RadioButton) rootView.findViewById(R.id.salad_cucumber);


        return  rootView;
    }


}
