package com.example.espinajohn.xburger;

import android.app.Activity;
import android.app.ListActivity;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import api_communicators.Customer;
import api_communicators.CustomerDetailsController;
import fragments_ingredient_page.BunsFragment;
import fragments_ingredient_page.CheeseFragment;
import fragments_ingredient_page.MeatFragments;
import fragments_ingredient_page.SaladsFragment;


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
                //Needs to be created
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


        // Connect to database to validate the username and ID
        // Method to be put in CustomerControls class?
        // If successful go to login page
        login.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v){
                final String usernameString = username.getText().toString();
                String passwordString = password.getText().toString();
//                String loginMethod ="";
//                // CustomerVerifier object will execute async processes in the background to retrieve check and retrive customer email and HashPass
//                //CustomerVerifier customerDetails = new CustomerVerifier();
//                //customerDetails.execute(usernameString);
//
//                // hash/salt inputted password
//                //compare hashed/salted inutted password to the hashed password retrieved from database
//                //if the same, proceeds to ingredient page
//
//
//                CustomerDetailsController customerDetails = new CustomerDetailsController();
//                customerDetails.execute(loginMethod,usernameString);
//
//                Customer customer = new Customer();
//                try {
//                    customer = customerDetails.get();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                } catch (ExecutionException e) {
//                    e.printStackTrace();
//                }
//
//                Log.d("user", customer.getUsername());



                activity.setContentView(R.layout.ingredient_page);
            setUpIngredientPage();
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

        next.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                setUpReviewOrder();
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

        String creditcard_string = creditcard.getText().toString();
        String expirydate_string = expirydate.getText().toString();
        String cvv_String = cvv.getText().toString();

        //Connect with the database and Customer Control methods to validate the numbers
        //Connect with external payment provider

        //On click of confirm payment, if payment details are successful
        confirmpayment.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //Pop up button to confirm order is successful
                setUpHomePage();
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
    }



}
