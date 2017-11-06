package fragments_ingredient_page;


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

    public BunsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_buns, container, false);
        rg = (RadioGroup) rootView.findViewById(R.id.radiogroup_bun_choices);
        bunWhite = (RadioButton) rootView.findViewById(R.id.bun_white);
        bunWholemeal = (RadioButton) rootView.findViewById(R.id.bun_wholemeal);
        bunSordough = (RadioButton) rootView.findViewById(R.id.bun_sordough);
        bunGlutenFree =(RadioButton) rootView.findViewById(R.id.bun_gluten_free);

        return rootView;
    }



}
