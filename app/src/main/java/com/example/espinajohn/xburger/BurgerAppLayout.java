package com.example.espinajohn.xburger;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import api_communicators.StockDetailsController;
import entity.Customer;
import entity.Item;
import entity.Order;
import entity.Stock;
import fragments_ingredient_page.BunsFragment;
import fragments_ingredient_page.CheeseFragment;
import fragments_ingredient_page.MeatFragments;
import fragments_ingredient_page.SaladsFragment;
import fragments_ingredient_page.SaucesFragment;
import helpers.CustomerControls;
import helpers.OrderControls;
import helpers.StockControls;
import passwords.Passwords;


/**
 * Created by stlaumade on 2/11/2017.
 */

public class BurgerAppLayout extends ListActivity{

    //Variables
    Activity activity;
    Order master_order;

    //Things to remember for state
    Boolean app_logged_in;
    int customer_id;
    int currentLayout;
    ArrayList<Integer> burger_order_ingredients;
    public static HashMap<Integer, Boolean> selectedStock = MainActivity.selectedStock;

    //Things to remembers for orders
    ArrayList<Item> listofitems = new ArrayList<Item>();
    ArrayList<Stock> current_burger;
    ArrayList<Stock> current_sides;
    ArrayList<Stock> current_drinks;
    ArrayList<Stock> current_special;

    //Constructor
    //Will need to add the shared preferences variables
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
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
            case R.layout.sides_page:
                setUpSidesPage();
                break;
            case  R.layout.drinks_page:
                setUpDrinksPage();
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
               if (app_logged_in != null && app_logged_in) {
                   setUpIngredientPage();
                } else {
                    setUpHomePage();
                }
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

        Log.d("Customer ID", customer_id + "");



        if (app_logged_in != null && app_logged_in){

            //Get the list of orders
            ArrayList<Order> orders = OrderControls.retrieveAllOrders (customer_id);

            if (orders != null) {
                for (int i = 0; i < orders.size (); i++) {
                    //Populate the lists with this information
                    //Will also need to add a reorder button
                    Log.d("Order " + i, orders.get (i).toString ());
                }
            }
        }

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
        app_logged_in = false;

        //Set the controls
        final EditText username = (EditText) activity.findViewById(R.id.username_);
        final EditText password = (EditText) activity.findViewById(R.id.password);
        Button login = (Button) activity.findViewById(R.id.homepage_login);
        TextView signup = (TextView) activity.findViewById(R.id.homepage_signup);

        // Connect to database to validate the username and ID
        // Method to be put in CustomerControls class?
        // If successful go to login page
        login.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            public void onClick(View v){
                final String usernameString = username.getText().toString();
                String passwordString = password.getText().toString();

                Customer customer = CustomerControls.createCustomer(usernameString);

                if (customer !=null){
                    if (!passwordString.equals("") && customer.validateCustomerPassword (passwordString, customer.getPassHash (), customer.getSalt (), customer.getIterations ())) {
                            setUpPreMadeBurgerPage();
                            app_logged_in = true;
                            customer_id = customer.getCustomer_id ();
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

    public void setUpPreMadeBurgerPage(){

        currentLayout = R.layout.premade_burger_page;
        activity.setContentView(R.layout.premade_burger_page);
        Button next = (Button) activity.findViewById(R.id.button_next);
        LinearLayout linearLayout = (LinearLayout)activity.findViewById(R.id.premade_holder);


        next.setOnClickListener(new View.OnClickListener(){
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            public void onClick(View v){

                saveOrder ();
                setUpSidesPage();
            }
        });


    }




    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void setUpSidesPage(){

        //set the layout
        currentLayout = R.layout.sides_page;
        activity.setContentView(R.layout.sides_page);

        ArrayList <CheckBox> checkBoxes = new ArrayList<>();
        ArrayList<Stock> sides = new ArrayList<>();
        ArrayList<Stock> allSides = new ArrayList<>();
        HashMap<String,ArrayList> allStocks = new HashMap<>();
        HashMap<String,ArrayList> stocks = new HashMap<>();
        LinearLayout sidesHolder = (LinearLayout) activity.findViewById(R.id.sides_holder);

        //set the controls
        Button back3 = (Button)activity.findViewById(R.id.back_to_landing_page3);
        Button next = (Button) activity.findViewById(R.id.button_next);

        //Generate the CheckBoxes
        try {
            //if statement here if previously clicked so won't need to query the database again
            allStocks = MainActivity.getStockHashMap();
            stocks =  new StockDetailsController().execute().get();
            allSides = allStocks.get("sideCategory");
            sides = stocks.get("sideCategory");

            //Create and add checkboxes to radiogroup from current stocks
            checkBoxes = StockControls.generateCheckBoxes(sidesHolder,activity, allSides);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        StockControls.updateStockViewOfCheckBoxes(sides, checkBoxes);


        back3.setOnClickListener (new View.OnClickListener (){

            @Override
            public void onClick(View view) {
                setUpLandingPage ();
            }
        });


        next.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                saveOrder ();
                setUpDrinksPage ();
            }
        });
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void setUpDrinksPage(){
        //set the layout
        currentLayout = R.layout.drinks_page;
        activity.setContentView(R.layout.drinks_page);

        ArrayList <CheckBox> checkBoxes = new ArrayList<>();
        ArrayList<Stock> drinks = new ArrayList<>();
        ArrayList<Stock> allDrinks = new ArrayList<>();
        HashMap<String,ArrayList> allStocks = new HashMap<>();
        HashMap<String,ArrayList> stocks = new HashMap<>();
        LinearLayout drinksHolder = (LinearLayout) activity.findViewById(R.id.drinks_holder);

        //set the controls
        Button back3 = (Button)activity.findViewById(R.id.back_to_landing_page3);
        Button next = (Button) activity.findViewById(R.id.button_next);

        //Generate the CheckBoxes
        try {
            //if statement here if previously clicked so won't need to query the database again
            allStocks = MainActivity.getStockHashMap();
            stocks =  new StockDetailsController().execute().get();
            allDrinks = allStocks.get("drinkCategory");
            drinks = stocks.get("drinkCategory");

            //Create and add checkboxes to radiogroup from current stocks
            checkBoxes = StockControls.generateCheckBoxes(drinksHolder,activity, allDrinks);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        StockControls.updateStockViewOfCheckBoxes(drinks, checkBoxes);


        back3.setOnClickListener (new View.OnClickListener (){

            @Override
            public void onClick(View view) {
                setUpLandingPage ();
            }
        });


        next.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                saveOrder ();
                setUpReviewOrder ();
            }
        });
    }


    public void setUpIngredientPage(){

        //Set the layout
        currentLayout = R.layout.ingredient_alternative_prototype;
        Log.d("CHECK", "ingredient page " + currentLayout);
        activity.setContentView(R.layout.ingredient_alternative_prototype);

        //Set the controls
        TabLayout tabLayout = (TabLayout) activity.findViewById(R.id.tabs);
        Button next = (Button) activity.findViewById(R.id.button_next);
        TabItem salads = (TabItem) activity.findViewById(R.id.salads_button);
        TabItem buns = (TabItem) activity.findViewById(R.id.buns_button);
        TabItem meats = (TabItem) activity.findViewById(R.id.meats_button);
        TabItem cheese = (TabItem) activity.findViewById(R.id.cheese_button);
        TabItem sauces = (TabItem) activity.findViewById(R.id.sauces_button);
        Button back3 = (Button) activity.findViewById (R.id.back_to_landing_page3);

        back3.setOnClickListener (new View.OnClickListener (){

            @Override
            public void onClick(View view) {
                setUpLandingPage ();
            }
        });

       tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
           @Override
           public void onTabSelected(TabLayout.Tab tab) {

               int selected = tab.getPosition();
               switch (tab.getPosition()) {


                   case 0:
                       if (selected==0) {
                       BunsFragment bunFragment = new BunsFragment();
                       activity.getFragmentManager().beginTransaction()
                               .replace(R.id.placeholder, bunFragment)
                               .commit();
                   }
                   break;
                   case 1:
                   if (selected==1) {
                       MeatFragments meatFragment = new MeatFragments();
                       activity.getFragmentManager().beginTransaction()
                               .replace(R.id.placeholder, meatFragment)
                               .commit();

                   }
                   break;
                   case 2:
                   if (selected==2) {
                       CheeseFragment cheeseFragment = new CheeseFragment();
                       activity.getFragmentManager().beginTransaction()
                               .replace(R.id.placeholder, cheeseFragment)
                               .commit();

                   }
                   break;
                   case 3:
                   if (selected==3) {
                       SaladsFragment saladsFragment = new SaladsFragment();
                       activity.getFragmentManager().beginTransaction()
                               .replace(R.id.placeholder, saladsFragment)
                               .commit();

                   }
                   break;
                   case 4:
                   if (selected==4) {

                       SaucesFragment saucesFragment = new SaucesFragment();
                       activity.getFragmentManager().beginTransaction()
                               .replace(R.id.placeholder, saucesFragment)
                               .commit();
                   }
                   break;
               }
           }

           @Override
           public void onTabUnselected(TabLayout.Tab tab) {

           }

           @Override
           public void onTabReselected(TabLayout.Tab tab) {

           }
       });


        next.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                saveOrder ();
                setUpReviewOrder ();
              }
        });
    }

    public void setUpPaymentPage(){
        //Set the layout
        currentLayout = R.layout.payment_page;
        activity.setContentView(R.layout.payment_page);

        //Set the controls
        EditText creditcard = (EditText) activity.findViewById(R.id.credit_card);
        EditText expirydate = (EditText) activity.findViewById(R.id.expiry_date);
        EditText cvv = (EditText) activity.findViewById(R.id.cvv_number);
        Button confirmpayment = (Button) activity.findViewById(R.id.button_confirm);
        Button editorder = (Button) activity.findViewById(R.id.button_edit2);
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
                //Will connect with the payment provided to confirm card details and validity.
                //If payment is successful do the code below.
                //Else will need to pop up a diallog message saying that the order is not successful

                //Put the items in an order
                //Sends the order to the database
                master_order = new Order(customer_id, listofitems);
                OrderControls.addOrderToDB (master_order);

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
        Button addtocart = (Button) activity.findViewById (R.id.button_addtocart);

        //Set all the ingredients in the textview based on the shared preferences

        paynow.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                if (!current_burger.isEmpty ()){

                    listofitems.add (new Item(current_burger));
                    Log.d ("Check - Burger list", "" + listofitems.get (0));
                }

                if (!current_drinks.isEmpty ()) {
                    listofitems.add (new Item (current_drinks));
                    Log.d ("Check - Drink list", "" + listofitems.get (1));
                }

                if (!current_sides.isEmpty ()){
                    listofitems.add (new Item(current_sides));
                    Log.d ("Check - Side list", "" + listofitems.get (2));
                }

                if (!current_special.isEmpty ()) {
                    listofitems.add (new Item (current_special));
                    Log.d ("Check - Special list", "" + listofitems.get (3));
                }

                selectedStock.clear ();
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

        addtocart.setOnClickListener (new View.OnClickListener (){

            @Override
            public void onClick(View view) {

                //Add to list of items
                if (!current_burger.isEmpty ()){
                    listofitems.add (new Item(current_burger));
                    Log.d ("Check - Burger list", "" + listofitems.get (0));
                }

                if (!current_drinks.isEmpty ()) {
                    listofitems.add (new Item (current_drinks));
                    Log.d ("Check - Drink list", "" + listofitems.get (1));
                }

                if (!current_sides.isEmpty ()){
                    listofitems.add (new Item(current_sides));
                    Log.d ("Check - Side list", "" + listofitems.get (2));
                }

                if (!current_special.isEmpty ()) {
                    listofitems.add (new Item (current_special));
                    Log.d ("Check - Special list", "" + listofitems.get (3));
                }

                selectedStock.clear ();
                setUpIngredientPage ();
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
        EditText signup_ph = (EditText) activity.findViewById (R.id.signup_phnum);
        Button signup = (Button) activity.findViewById(R.id.sign_up_button);
        Button back6 = (Button) activity.findViewById (R.id.back_to_landing_page6);

        signup.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                String usernameString = signup_username.getText().toString();
                String passwordString = signup_password.getText ().toString ();
                String emailString = signup_email.getText().toString();
                String fnameString = signup_fname.getText().toString();
                String lnameString = signup_lname.getText().toString();
                String phoneString = signup_ph.getText ().toString ();

                //Send all the strings to the database
                //Check if username is taken
                //Will need to do some testing on this not validating properly

                Customer check = CustomerControls.createCustomer(usernameString);
                if (check != null){
                    alertDialogMessage ("Username Not Available", "Please select a different username");
                } else {

                    //Get a new salt
                    byte[] salt = Passwords.getNextSalt (16);
                    String saltString = Passwords.base64Encode (salt);
                    Log.d("test", saltString);

                    //Get a new iteration
                    int iterationsInt = Passwords.getNextNumIterations ();
                    Log.d("iterations", "" + iterationsInt);

                    //Get the password as char[]
                    char[] pword = passwordString.toCharArray ();
                    Log.d("TEST", passwordString);

                    //Hash the password
                    byte[] hashpass = Passwords.hash (pword, salt, iterationsInt);
                    String hashpassString = Passwords.base64Encode (hashpass);

                    //Other variables that are needed to construct a customer
                    String passpinString = "";
                    String cardtokenString = "";

                    Customer customer = new Customer(usernameString, emailString, phoneString, iterationsInt, saltString, hashpassString,passpinString, cardtokenString );

                    Log.d("Customer", customer.getUsername ());
                    // Send this customer to the database to be added
                    // Connect to the API to send the customer
                    CustomerControls.addCustomerToDB (customer);

                    //Alert dialog thanks for signing up
                    alertDialogMessage ("Sign Up Successful", "Thanks for joining Xtreme Burgers!");

                    //Then set up ingredient page
                    setUpIngredientPage();
                }
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

    public void saveOrder(){

        current_burger = new ArrayList<>();
        current_sides = new ArrayList<> ();
        current_drinks = new ArrayList<> ();
        current_special = new ArrayList<> ();

        //Get the hashmap of true items

        for (int key : selectedStock.keySet ()){

            Boolean check = selectedStock.get (key);
            Log.d("Check - Key", "" + key + " " + selectedStock.get (key));

            //Check if they are an instance of burger, sides drink
            if(check){
                Log.d("BurgerApp - Boolean", "" + selectedStock.containsKey (key));
                //Check if the stock item is an instance of burger, sides, or drinks
                if (StockControls.getItemType (key).equals ("Burger")){
                    //Put it in the burger arraylist
                    Stock s = new Stock(key);
                    Log.d("BurgerApp - Burger", "" + s.getIngredient_id () + s.getIngredient_name ());
                    current_burger.add (s);
                } else if (StockControls.getItemCategory(key).equals ("Side")){
                    //Put it in a stock list for sides
                    Stock s = new Stock(key);
                    current_sides.add (s);
                    Log.d("BurgerApp - Side", "" + s.getIngredient_id () + s.getIngredient_name ());
                } else if (StockControls.getItemCategory(key).equals ("Drink")){
                    //Put it in a stock list for drinks
                    Stock s = new Stock(key);
                    current_drinks.add (s);
                    Log.d("BurgerApp - Drink", "" + s.getIngredient_id () + s.getIngredient_name ());
                } else if (StockControls.getItemCategory(key).equals ("Special")){
                    //Put it in a stock list for special
                    Stock s = new Stock(key);
                    current_special.add (s);
                    Log.d("BurgerApp - Special", "" + s.getIngredient_id () + s.getIngredient_name ());
                }

            }
        }
    }
}
