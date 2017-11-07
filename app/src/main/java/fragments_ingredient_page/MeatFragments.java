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


    public MeatFragments() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_meat_fragments, container, false);
        rg = (RadioGroup) rootView.findViewById(R.id.radiogroup_meat_choices);
        rg2= (RadioGroup) rootView.findViewById(R.id.radiogroup_meat_choices2);
        beef = (RadioButton)rootView.findViewById(R.id.pattie_beef);
        chicken = (RadioButton)rootView.findViewById(R.id.pattie_chicken);
        falafel = (RadioButton)rootView.findViewById(R.id.pattie_falafel);
        tofu = (RadioButton)rootView.findViewById(R.id.pattie_tofu);
        pork = (RadioButton)rootView.findViewById(R.id.pattie_pork);
        lamb = (RadioButton)rootView.findViewById(R.id.pattie_lamb);


        return rootView;
    }




}
