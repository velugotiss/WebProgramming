package com.example.vijaya.myorder;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.commons.lang3.BooleanUtils;

import java.util.ArrayList;
import java.util.List;

public class OrderLatest extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    private static final Integer PIZZA_PRICE = 7;
    private static final Integer CHICKEN_PRICE = 3;
    private static final Integer VEGGIE_PRICE = 2;
    private static final Integer PEPPERONI_PRICE = 2;
    private static final Integer OP_PRICE = 1;
    float totalPrice;
    Integer quantity = 1;
    String orderSummary;

    EditText userNameText;
    TextView quantityTextView;
    CheckBox chickenChecked, veggieChecked, pepperoniChecked, opChecked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page1);

        quantityTextView = findViewById(R.id.quantity);
        userNameText = findViewById(R.id.name);
        chickenChecked = findViewById(R.id.chicken_checked);
        veggieChecked = findViewById(R.id.veggie_checked);
        pepperoniChecked = findViewById(R.id.pepperoni_checked);
        opChecked = findViewById(R.id.op_checked);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        // Spinner click listener
        spinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Fries");
        categories.add("Coke");
        categories.add("fries and Coke");


        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
    }

    private boolean isUserEmpty(){
        String userName = userNameText.getText().toString();
        if(userName == null || userName.isEmpty()){
            Context context = getApplicationContext();
            String upperLimitToast = getString(R.string.userNull);
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, upperLimitToast, duration);
            toast.show();
            return true;
        }
        return false;
    }


    private String fetchDetails() {
        boolean chicken = chickenChecked.isChecked();
        boolean veggie = veggieChecked.isChecked();
        boolean pepperoni = pepperoniChecked.isChecked();
        boolean op = opChecked.isChecked();

        totalPrice = calculatePrice(chicken, veggie, pepperoni, op, quantity);
        return fetchOrderSummary(userNameText.getText().toString(), chicken, veggie, pepperoni, op, totalPrice);
    }

    public void orderSummary(View view) {
        if (!isUserEmpty()) {
            orderSummary = fetchDetails();
            Intent intent = new Intent(OrderLatest.this, OrderSummary.class);
            intent.putExtra("orderSummary", orderSummary);
            startActivity(intent);
        }
    }

    public void orderPizzaMain(View view) {
        if (!isUserEmpty()) {
            orderSummary = fetchDetails();
            Intent emailIntent = new Intent(Intent.ACTION_SEND);
            emailIntent.setType("plain/text");
            emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {"PizzaOrder@gmail.com"});
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Order Summary");
            emailIntent.putExtra(Intent.EXTRA_TEXT, orderSummary);
            startActivity(Intent.createChooser(emailIntent, ""));
        }
    }



    private String fetchOrderSummary(String Name, boolean chicken, boolean veggie,
                                     boolean pepperoni, boolean op, float totalPrice) {
        return getString(R.string.order_summary_name,Name) +"\n"+
                getString(R.string.order_summary_chicken, BooleanUtils.toStringYesNo(chicken))+"\n"+
                getString(R.string.order_summary_veggie,BooleanUtils.toStringYesNo(veggie)) +"\n"+
                getString(R.string.order_summary_pepperoni,BooleanUtils.toStringYesNo(pepperoni)) +"\n"+
                getString(R.string.order_summary_op,BooleanUtils.toStringYesNo(op)) +"\n"+
                getString(R.string.order_summary_quantity,quantity)+"\n"+
                getString(R.string.order_summary_total_price,totalPrice) +"\n"+
                getString(R.string.thank_you);
    }

    private float calculatePrice(boolean chicken, boolean veggie, boolean pepperoni, boolean op, Integer quantity) {
        int basePrice = PIZZA_PRICE;
        if (chicken) {
            basePrice += CHICKEN_PRICE;
        }
        if (veggie) {
            basePrice += VEGGIE_PRICE;
        }
        if (pepperoni){
            basePrice +=PEPPERONI_PRICE;
        }
        if(op){
            basePrice +=OP_PRICE;
        }
        return quantity * basePrice;
    }

    public void increment(View view) {
        if (quantity < 20) {
            quantity = quantity + 1;
            display(quantity);
        } else {
            Log.i("PizzaOrder", "Please select less than 20 Pizzas");
            Context context = getApplicationContext();
            String lowerLimitToast = "Please select less than 20 Pizzas";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, lowerLimitToast, duration);
            toast.show();
            return;
        }
    }


    public void decrement(View view) {
        if (quantity > 1) {
            quantity = quantity - 1;
            display(quantity);
        } else {
            Log.i("pizzaOrder", "Please select atleast one Pizza");
            Context context = getApplicationContext();
            String upperLimitToast = "Please select atleast one Pizza";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, upperLimitToast, duration);
            toast.show();
            return;
        }
    }

    private void display(int number) {
        quantityTextView.setText("" + number);
    }

    public void callStore(View view) {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:2244466666"));
        startActivity(Intent.createChooser(callIntent, ""));
    }
    @Override


    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

}

