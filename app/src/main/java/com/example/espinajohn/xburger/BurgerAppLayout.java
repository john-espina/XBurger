package com.example.espinajohn.xburger;

import android.app.Activity;
import android.app.ListActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by stlaumade on 2/11/2017.
 */

public class BurgerAppLayout extends ListActivity{

    //Variables
    Activity activity;
    int currentLayout;

    //Constructor
    //Will need to add the shared preferences variables
    BurgerAppLayout(Activity act) {
        activity = act;

        switch(currentLayout){
            case R.layout.activity_main:
                setUpLandingPage();
                break;
            case 2:
                setUpHomePage();
                break;
            case 3:
                setUpIngredientPage();
                break;
            case 4:
                setUpPaymentPage();
                break;
            case 5:
                setUpReviewOrder();
                break;
            case 6:
                setUpSignUpPage();
                break;
        }
    }

    //Methods
    public void setUpLandingPage(){

        //Set the layout
        currentLayout = R.layout.activity_main;
        activity.setContentView(R.layout.activity_main);

        //Set the shared preferences
        Button order = (Button) activity.findViewById(R.id.button_make_order);

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.setContentView(R.layout.home_page);
            }
        });
    }

    public void setUpHomePage(){

        //Set the layout

        //Set the shared preferences
    }

    public void setUpIngredientPage(){

        //Set the layout

        //Set the shared preferences
    }

    public void setUpPaymentPage(){
        //Set the layout

        //Set the shared preferences
    }

    public void setUpReviewOrder(){

        //Set the layout

        //Set the shared preferences
    }

    public void setUpSignUpPage(){

        //Set the layout

        //Set the shared preferences
    }
}
