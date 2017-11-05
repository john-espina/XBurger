package com.example.espinajohn.xburger;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import fragments_ingredient_page.BunsFragment;
import fragments_ingredient_page.CheeseFragment;
import fragments_ingredient_page.MeatFragments;
import fragments_ingredient_page.SaladsFragment;


public class MainActivity extends AppCompatActivity {

    BurgerAppLayout control;
    int currentLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        control = new BurgerAppLayout(this);


        //SharedPreferences settings = getSharedPreferences("BurgerPreferences", Context.MODE_PRIVATE);

        //Get the preferences here
        /**
         * Placeholder:
         * username;
         * password;
         * email;
         * currentLayout;
         * bun_choice;
         * meat_choice;
         * salad_choice;
         * sauce_choice;
         * logged_in;
         *
         * */

        //Call the Burger Controller to set up the main screen
        if (savedInstanceState != null) {
            // Remember the layout
            // currentLayout = savedInstanceState.getInt(currentLayout);
        } else {
            control.setUpLandingPage();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //Put the saved instance state preferences here
        super.onSaveInstanceState(outState);
    }

    protected void onStop(){
        //SharedPreferences settings = getSharedPreferences("BurgerPreferences", Context.MODE_PRIVATE);
        //SharedPreferences.Editor editor = settings.edit();

        //Save the preferences in here
        /**
         * Placeholder:
         * username;
         * password;
         * email;
         * currentLayout;
         * bun_choice;
         * meat_choice;
         * salad_choice;
         * sauce_choice;
         * logged_in;
         *
         * */

        //editor.commit();
        super.onStop();
    }

    public void addBunFragment(View view){
        BunsFragment bunFragment = new BunsFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.placeholder, bunFragment)
                .commit();

    }

    public void addMeatFragment(View view){
        MeatFragments meatFragment = new MeatFragments();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.placeholder, meatFragment)
                .commit();

    }

    public void addCheeseFragment(View view){
        CheeseFragment cheeseFragment = new CheeseFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.placeholder, cheeseFragment)
                .commit();

    }

    public void addSaladFragment(View view){
        SaladsFragment saladsFragment = new SaladsFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.placeholder, saladsFragment)
                .commit();

    }


}
