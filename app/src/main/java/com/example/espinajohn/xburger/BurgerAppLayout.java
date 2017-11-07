package com.example.espinajohn.xburger;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.ArrayList;

import api_communicators.Customer;
import fragments_ingredient_page.BunsFragment;
import fragments_ingredient_page.CheeseFragment;
import fragments_ingredient_page.MeatFragments;
import fragments_ingredient_page.SaladsFragment;
import fragments_ingredient_page.SaucesFragment;
import helpers.CustomerControls;


/**
 * Created by stlaumade on 2/11/2017.
 */

public class BurgerAppLayout extends ListActivity{

    //Variables
    Activity activity;

    //Things to remember for state
    Boolean app_logged_in;
    int currentLayout;
    ArrayList<Integer> burger_order_ingredients;

    //Constructor
    //Will need to add the shared preferences variables
    BurgerAppLayout(Activity act, int layout) {
        activity = act;
        currentLayout = layout;

        switch(currentLayout){
            case R.layout.activity_main:
                setUpLandingPage();
                break;
            case R.layout.home_page:
                setUpHomePage();
                break;
            case R.layout.ingredient_alternative_prototype:
                setUpIngredientPage();
                break;
            case R.layout.payment_page:
                setUpPaymentPage();
                break;
            case R.layout.review_order:
                setUpReviewOrder();
                break;
            case R.layout.sign_up_page:
                setUpSignUpPage();
                break;
        }
    }

    //Methods
    public void setUpLandingPage(){

        //Set the layout
        currentLayout = R.layout.activity_main;
        activity.setContentView(R.layout.activity_main);

        //Set the controls
        Button order = (Button) activity.findViewById(R.id.button_make_order);
        Button order_history = (Button) activity.findViewById(R.id.button_order_history);

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //if (app_logged_in) {
                   //setUpIngredientPage();
                //} else {
                    setUpHomePage();
                //}
            }
        });

        order_history.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //Open the list layout with order history
                setUpOrderHistory ();
            }
        });
    }

    public void setUpOrderHistory(){
        currentLayout = R.layout.previous_orders;
        activity.setContentView (R.layout.previous_orders);

        Button reorder = (Button) activity.findViewById (R.id.reorder);
        Button back = (Button) activity.findViewById (R.id.back_to_landing_page);

        reorder.setOnClickListener (new View.OnClickListener (){

            @Override
            public void onClick(View view) {
                //Do what needs to be done to reorder
            }
        });

        back.setOnClickListener (new View.OnClickListener (){

            @Override
            public void onClick(View view) {
               setUpLandingPage ();
            }
        });
    }

    public void setUpHomePage(){

        //Set the layout
        currentLayout = R.layout.home_page;
        activity.setContentView(R.layout.home_page);

        //Set the shared preferences

        //Set the controls
        final EditText username = (EditText) activity.findViewById(R.id.username_);
        final EditText password = (EditText) activity.findViewById(R.id.password);
        Button login = (Button) activity.findViewById(R.id.homepage_login);
        Button signup = (Button) activity.findViewById(R.id.homepage_signup);
        Button back2 = (Button) activity.findViewById (R.id.back_to_landing_page2);

        back2.setOnClickListener (new View.OnClickListener (){

            @Override
            public void onClick(View view) {
                setUpLandingPage ();
            }
        });

        // Connect to database to validate the username and ID
        // Method to be put in CustomerControls class?
        // If successful go to login page
        login.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v){
                final String usernameString = username.getText().toString();
                String passwordString = password.getText().toString();
                String loginMethod ="";

                Customer customer = CustomerControls.createCustomer(usernameString);

                if (customer !=null){
                    if(customer.validateCustomerPassword(passwordString, customer.getPassHash(), customer.getSalt(),customer.getIterations())){
                     setUpIngredientPage();
                     app_logged_in = true;
                    } else {
                        alertDialogMessage ("Invalid Password", "Please check credentials");
                    }
                }else {
                    alertDialogMessage ("Invalid User", "We don't recognise the username. Please check.");
                }
            }
        });
        signup.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){activity.setContentView(R.layout.sign_up_page);
            setUpSignUpPage();
            }
        });
    }

    public void setUpIngredientPage(){

        //Set the layout
        currentLayout = R.layout.ingredient_alternative_prototype;
        Log.d("CHECK", "ingredient page " + currentLayout);
        activity.setContentView(R.layout.ingredient_alternative_prototype);

        //Set the controls
        Button next = (Button) activity.findViewById(R.id.button_next);
        Button salads = (Button) activity.findViewById(R.id.salads_button);
        Button buns = (Button) activity.findViewById(R.id.buns_button);
        Button meats = (Button) activity.findViewById(R.id.meats_button);
        Button cheese = (Button) activity.findViewById(R.id.cheese_button);
        Button sauces = (Button) activity.findViewById(R.id.sauces_button);
        Button back3 = (Button) activity.findViewById (R.id.back_to_landing_page3);

        back3.setOnClickListener (new View.OnClickListener (){

            @Override
            public void onClick(View view) {
                setUpLandingPage ();
            }
        });

        buns.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){


                BunsFragment bunFragment = new BunsFragment();
                activity.getFragmentManager().beginTransaction()
                            .replace(R.id.placeholder, bunFragment)
                            .commit();


                }

        });


        meats.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                MeatFragments meatFragment = new MeatFragments();
                activity.getFragmentManager().beginTransaction()
                        .replace(R.id.placeholder, meatFragment)
                        .commit();
            }
        });

        cheese.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                CheeseFragment cheeseFragment = new CheeseFragment();
                activity.getFragmentManager().beginTransaction()
                        .replace(R.id.placeholder, cheeseFragment)
                        .commit();
            }
        });


        salads.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                SaladsFragment saladsFragment = new SaladsFragment();
                activity.getFragmentManager().beginTransaction()
                        .replace(R.id.placeholder, saladsFragment)
                        .commit();

            }
        });

        sauces.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                SaucesFragment saucesFragment = new SaucesFragment();
                activity.getFragmentManager().beginTransaction()
                        .replace(R.id.placeholder, saucesFragment)
                        .commit();

            }
        });

        next.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //if (burger_order_ingredients == null){
                    // Commented out until ingredients are added to temp list
                    //alertDialogMessage("Error", "You have not selected any ingredients");
                //} else {
                    setUpReviewOrder ();
                //}
            }
        });
    }

    //This method crashed the app need to investigate.
    public void setUpPaymentPage(){
        //Set the layout
        currentLayout = R.layout.payment_page;
        activity.setContentView(R.layout.payment_page);

        //Set the shared preferences

        //Set the controls
        EditText creditcard = (EditText) activity.findViewById(R.id.credit_card);
        EditText expirydate = (EditText) activity.findViewById(R.id.expiry_date);
        EditText cvv = (EditText) activity.findViewById(R.id.cvv_number);
        Button confirmpayment = (Button) activity.findViewById(R.id.button_confirm);
        Button editorder = (Button) activity.findViewById(R.id.button_edit);
        Button back4 = (Button) activity.findViewById (R.id.back_to_landing_page4);

        String creditcard_string = creditcard.getText().toString();
        String expirydate_string = expirydate.getText().toString();
        String cvv_String = cvv.getText().toString();

        back4.setOnClickListener (new View.OnClickListener (){

            @Override
            public void onClick(View view) {
                setUpLandingPage ();
            }
        });

        //Connect with the database and Customer Control methods to validate the numbers
        //Connect with external payment provider

        //On click of confirm payment, if payment details are successful
        confirmpayment.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                alertDialogMessage ("Order Successful", "Thank you for ordering with XBurger!");
                setUpLandingPage ();
            }
        });

        editorder.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
               setUpIngredientPage();
            }
        });

    }

    public void setUpReviewOrder(){

        //Set the layout
        currentLayout = R.layout.review_order;
        activity.setContentView(R.layout.review_order);

        //Set the shared preferences

        //Set the controls
        TextView ingredient_list = (TextView) activity.findViewById(R.id.order_details);
        Button paynow = (Button) activity.findViewById(R.id.button_confirm);
        Button editorder2 = (Button) activity.findViewById(R.id.button_edit_order);
        Button back5 = (Button) activity.findViewById (R.id.back_to_landing_page5);

        //Set all the ingredients in the textview based on the shared preferences

        paynow.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                setUpPaymentPage();
            }
        });

        editorder2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                setUpIngredientPage();
            }
        });

        back5.setOnClickListener (new View.OnClickListener (){

            @Override
            public void onClick(View view) {
                setUpLandingPage ();
            }
        });
    }

    public void setUpSignUpPage(){

        //Set the layout
        currentLayout = R.layout.sign_up_page;
        activity.setContentView(R.layout.sign_up_page);

        EditText signup_username = (EditText) activity.findViewById(R.id.signup_username);
        EditText signup_password = (EditText) activity.findViewById(R.id.signup_password);
        EditText signup_email = (EditText) activity.findViewById(R.id.signup_email);
        EditText signup_fname = (EditText) activity.findViewById(R.id.signup_fname);
        EditText signup_lname = (EditText) activity.findViewById(R.id.signup_lname);
        Button signup = (Button) activity.findViewById(R.id.sign_up_button);
        Button back6 = (Button) activity.findViewById (R.id.back_to_landing_page6);

        String usernameString = signup_username.getText().toString();
        String passwordString = signup_password.getText().toString();
        String emailString = signup_email.getText().toString();
        String fnameString = signup_fname.getText().toString();
        String lnameString = signup_lname.getText().toString();

        //Set the shared preferences
        //Send all the strings to the database

        signup.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                setUpIngredientPage();
            }
        });

        back6.setOnClickListener (new View.OnClickListener (){

            @Override
            public void onClick(View view) {
                setUpLandingPage ();
            }
        });
    }

    public void alertDialogMessage (String title, String message) {
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder (activity);

        //Title & message
        alertDialogBuilder.setMessage (message).setTitle (title);

        //Action button ok
        alertDialogBuilder.setPositiveButton ("OK", new DialogInterface.OnClickListener () {
            public void onClick(DialogInterface dialog, int id) {
                finish ();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create ();
        alertDialog.show ();
    }
}
