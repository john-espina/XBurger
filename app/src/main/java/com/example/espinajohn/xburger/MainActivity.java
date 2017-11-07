package com.example.espinajohn.xburger;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import fragments_ingredient_page.BunsFragment;
import fragments_ingredient_page.CheeseFragment;
import fragments_ingredient_page.MeatFragments;
import fragments_ingredient_page.SaladsFragment;


public class MainActivity extends AppCompatActivity {

    BurgerAppLayout control;
    int currentLayout;
    ArrayList<Integer> burger_order;
    //Boolean loggedin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.d("CHECK", "on create");
        super.onCreate(savedInstanceState);

        SharedPreferences settings = getSharedPreferences("BurgerPreferences", Context.MODE_PRIVATE);

        //loggedin = settings.getBoolean("logged_in", false);
        //control.app_logged_in.equals(loggedin.booleanValue());//

        //Get the preferences here

        //Call the Burger Controller to set up the main screen
        if (savedInstanceState != null) {
            // Remember the layout
            currentLayout = savedInstanceState.getInt("current_layout");
            control = new BurgerAppLayout(this, currentLayout);
            //Do something with the burger ingredient list


        } else {
            control = new BurgerAppLayout(this, R.layout.activity_main);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //Put the saved instance state preferences here
        outState.putInt("current_layout", control.currentLayout);
        //outState.putStringArrayList("burger_order", burger_order_ingredients);
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

    public void onRadioButtonClicked(View view){

        boolean checked = ((RadioButton)view).isChecked();
        String chosen = null;
        switch (view.getId()){
            case R.id.bun_white:
                if (checked){
                    chosen = "white";
                    Log.d("chosen:", chosen);
                }
            case R.id.bun_wholemeal:
                if (checked){
                    //code here
                }
            case R.id.bun_sourdough:
                if (checked){
                    //code here
                }
            case R.id.bun_gluten_free:
                if (checked){
                    //code here
                }
            case R.id.salad_lettuce:
                if (checked){
                    chosen = "white";
                    Log.d("chosen:", chosen);
                }
            case R.id.salad_tomato:
                if (checked){
                    //code here
                }
            case R.id.salad_onion:
                if (checked){
                    //code here
                }
            case R.id.salad_red_onion:
                if (checked){
                    //code here
                }
            case R.id.salad_beetroot:
                if (checked){
                    chosen = "white";
                    Log.d("chosen:", chosen);
                }
            case R.id.salad_pickle:
                if (checked){
                    //code here
                }
            case R.id.salad_capsicum:
                if (checked){
                    //code here
                }
            case R.id.salad_olives:
                if (checked){
                    //code here
                }
            case R.id.salad_cucumber:
                if (checked){
                    chosen = "white";
                    Log.d("chosen:", chosen);
                }
            case R.id.pattie_beef:
                if (checked){
                    //code here
                }
            case R.id.pattie_chicken:
                if (checked){
                    //code here
                }
            case R.id.pattie_falafel:
                if (checked){
                    //code here
                }
            case R.id.pattie_tofu:
                if (checked){
                    chosen = "white";
                    Log.d("chosen:", chosen);
                }
            case R.id.pattie_pork:
                if (checked){
                    //code here
                }
            case R.id.pattie_lamb:
                if (checked){
                    //code here
                }
            case R.id.cheese_smoke:
                if (checked){
                    //code here
                }
            case R.id.cheese_edam:
                if (checked){
                    chosen = "white";
                    Log.d("chosen:", chosen);
                }
            case R.id.cheese_brie:
                if (checked){
                    //code here
                    //Create stock object
                    //Add it to item list
                    //Add item to the order
                }
            case R.id.sauce_halloumi:
                if (checked){
                    //code here
                }
            case R.id.sauce_tomato_sauce:
                if (checked){
                    //code here
                }
            case R.id.sauce_aioli:
                if (checked){
                    //code here
                }
            case R.id.sauce_mayonnaise:
                if (checked){
                    //code here
                }
            case R.id.sauce_american_mustard:
                if (checked){
                    chosen = "white";
                    Log.d("chosen:", chosen);
                }
            case R.id.sauce_dijon_mustard:
                if (checked){
                    //code here
                }
            case R.id.sauce_honey_mustard:
                if (checked){
                    //code here
                }
            case R.id.sauce_mint:
                if (checked){
                    //code here
                }
            case R.id.sauce_brown:
                if (checked){
                    //code here
                }


        }

    }
}
