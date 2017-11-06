package com.example.espinajohn.xburger;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import fragments_ingredient_page.BunsFragment;
import fragments_ingredient_page.CheeseFragment;
import fragments_ingredient_page.MeatFragments;
import fragments_ingredient_page.SaladsFragment;


public class MainActivity extends AppCompatActivity {

    BurgerAppLayout control;
    int currentLayout;
    //Boolean loggedin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.d("CHECK", "on create");
        super.onCreate(savedInstanceState);

        SharedPreferences settings = getSharedPreferences("BurgerPreferences", Context.MODE_PRIVATE);

        //loggedin = settings.getBoolean("logged_in", false);
        //control.app_logged_in.equals(loggedin.booleanValue());//

        //Get the preferences here

        /**
         * email;
         * bun_choice;
         * meat_choice;
         * salad_choice;
         * sauce_choice;*
         * */

        //Call the Burger Controller to set up the main screen
        if (savedInstanceState != null) {
            // Remember the layout
            currentLayout = savedInstanceState.getInt("current_layout");
            control = new BurgerAppLayout(this, currentLayout);
        } else {
            control = new BurgerAppLayout(this, R.layout.activity_main);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //Put the saved instance state preferences here
        outState.putInt("current_layout", control.currentLayout);
        super.onSaveInstanceState(outState);
    }

    protected void onStop(){
        Log.d("CHECK", "on stop");
        SharedPreferences settings = getSharedPreferences("BurgerPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();

        //editor.putBoolean("logged_in", control.app_logged_in);

        //Save the preferences in here
        /**
         * Placeholder:
         * email;
         * bun_choice;
         * meat_choice;
         * salad_choice;
         * sauce_choice;*
         * */

        editor.commit();
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
