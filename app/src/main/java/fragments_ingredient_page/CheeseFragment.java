package fragments_ingredient_page;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.espinajohn.xburger.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CheeseFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CheeseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CheeseFragment extends Fragment {


    public CheeseFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cheese, container, false);
    }

}
