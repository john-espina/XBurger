package fragments_ingredient_page;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v13.app.FragmentStatePagerAdapter;

/**
 * Created by espinajohn on 15/11/2017.
 * This class is a custom Adapter used to present the Fragments by using Tabs
 */

public class ViewPageAdapter extends FragmentPagerAdapter {

    // Requires a constructor
    public ViewPageAdapter(FragmentManager fm) {
        super(fm);
    }

    // This method will create the new Fragment from each Fragment class (i.e. BunsFragment)
    @Override
    public Fragment getItem(int position) {

        Fragment fragment = null;

        if (position==0){

            fragment = new BunsFragment();

        }

        else if (position==1){

            fragment = new MeatFragments();

        }
        else if (position==2){

            fragment = new CheeseFragment();

        }
        else if (position==3){

            fragment = new SaladsFragment();

        }
        else  if (position==4){

            fragment = new SaucesFragment();

        }

        return fragment;
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public CharSequence getPageTitle (int position){
        String title = null;

        if (position==0){
            title = "CARBS";
        }

        else if (position==1){
            title= "MEAT";
        }
        else if (position==2){
            title= "CHEESE";
        }
        else if (position==3){
            title="SALADS";
        }
        else if (position==4){
            title="SAUCES";
        }
        return title;
    }
}
