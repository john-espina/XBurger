package com.example.espinajohn.xburger;

import android.app.Activity;
import android.app.ListActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

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

//        switch(currentLayout){
//            case R.layout.activity_main:
//                setUpLandingPage();
//                break;
//            case R.layout.home_page:
//                setUpHomePage();
//                break;
//            case R.layout.ingredient_page:
//                setUpIngredientPage();
//                break;
//            case R.layout.payment_page:
//                setUpPaymentPage();
//                break;
//            case R.layout.review_order:
//                setUpReviewOrder();
//                break;
//            case R.layout.sign_up_page:
//                setUpSignUpPage();
//                break;
//        }
    }

    //Methods
    public void setUpLandingPage(){

        //Set the layout
        //currentLayout = R.layout.activity_main;
        activity.setContentView(R.layout.activity_main);

        //Set the shared preferences

        //Set the controls
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
        currentLayout = R.layout.home_page;
        activity.setContentView(R.layout.activity_main);

        //Set the shared preferences

        //Set the controls
        EditText username = (EditText) findViewById(R.id.username);
        EditText password = (EditText) findViewById(R.id.password);
        Button login = (Button) findViewById(R.id.homepage_login);
        Button signup = (Button) findViewById(R.id.homepage_signup);

        String usernameString = username.getText().toString();
        String passwordString = password.getText().toString();

        // Connect to database to validate the username and ID
        // Method to be put in CustomerControls class?
        // If successful go to login page
        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){activity.setContentView(R.layout.ingredient_page);
            }
        });
        signup.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){ activity.setContentView(R.layout.sign_up_page);
            }
        });
    }

    public void setUpIngredientPage(){

        //Set the layout
        currentLayout = R.layout.ingredient_alternative_prototype;
        activity.setContentView(R.layout.ingredient_alternative_prototype);

        //Set the shared preferences

        //Set the controls
        Button buns = (Button) findViewById(R.id.buns);
        Button meats = (Button) findViewById(R.id.meats);
        Button salads = (Button) findViewById(R.id.salads);
        Button sauces = (Button) findViewById(R.id.sauces);
        Button next = (Button) findViewById(R.id.button_next);

        //Need three more radio groups for the other types of selections
        //May need to think of a different layout option rather than radio groups
        RadioGroup bun_choices = (RadioGroup) findViewById(R.id.bun_choices);


        buns.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //Make the bun choices the active radio group
                //Need to remember the selected option
            }
        });

        meats.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //Make the meats choices the active radio group
                //Need to remember the selected option
            }
        });

        salads.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //Make the salads choices the active radio group
                //Need to remember the selected option
            }
        });

        sauces.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //Make the sauces choices the active radio group
                //Need to remember the selected option
            }
        });

        next.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                activity.setContentView(R.layout.payment_page);
            }
        });
    }

    public void setUpPaymentPage(){
        //Set the layout
        currentLayout = R.layout.payment_page;
        activity.setContentView(R.layout.payment_page);

        //Set the shared preferences

        //Set the controls
        EditText creditcard = (EditText) findViewById(R.id.credit_card);
        EditText expirydate = (EditText) findViewById(R.id.expiry_date);
        EditText cvv = (EditText) findViewById(R.id.cvv_number);
        Button confirmpayment = (Button) findViewById(R.id.button_confirm);
        Button editorder = (Button) findViewById(R.id.button_edit);

        String creditcard_string = creditcard.getText().toString();
        String expirydate_string = expirydate.getText().toString();
        String cvv_String = cvv.getText().toString();

        //Connect with the database and Customer Control methods to validate the numbers
        //Connect with external payment provider

        //On click of confirm payment, if payment details are successful
        confirmpayment.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                activity.setContentView(R.layout.review_order);
            }
        });

        editorder.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                activity.setContentView(R.layout.ingredient_alternative_prototype);
            }
        });

    }

    public void setUpReviewOrder(){

        //Set the layout
        currentLayout = R.layout.review_order;
        activity.setContentView(R.layout.review_order);

        //Set the shared preferences

        //Set the controls
        TextView ingredient_list = (TextView) findViewById(R.id.order_details);
        Button paynow = (Button) findViewById(R.id.button_confirm);
        Button editorder2 = (Button) findViewById(R.id.button_edit);

        //Set all the ingredients in the textview based on the shared preferences

        paynow.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //Make a popup to say order successful

                //Go back to the homepage
                activity.setContentView(R.layout.home_page);
            }
        });

        editorder2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                activity.setContentView(R.layout.ingredient_alternative_prototype);
            }
        });

    }

    public void setUpSignUpPage(){

        //Set the layout
        currentLayout = R.layout.sign_up_page;
        activity.setContentView(R.layout.sign_up_page);

        EditText signup_username = (EditText) findViewById(R.id.signup_username);
        EditText signup_password = (EditText) findViewById(R.id.signup_password);
        EditText signup_email = (EditText) findViewById(R.id.signup_email);
        EditText signup_fname = (EditText) findViewById(R.id.signup_fname);
        EditText signup_lname = (EditText) findViewById(R.id.signup_lname);
        Button signup = (Button) findViewById(R.id.sign_up_button);

        String usernameString = signup_username.getText().toString();
        String passwordString = signup_password.getText().toString();
        String emailString = signup_email.getText().toString();
        String fnameString = signup_fname.getText().toString();
        String lnameString = signup_lname.getText().toString();

        //Set the shared preferences
        //Send all the strings to the database

        signup.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                activity.setContentView(R.layout.ingredient_alternative_prototype);
            }
        });
    }
}
