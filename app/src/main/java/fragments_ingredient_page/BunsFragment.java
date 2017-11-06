package fragments_ingredient_page;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.espinajohn.xburger.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class BunsFragment extends Fragment {


    public BunsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_buns, container, false);
    }

}
