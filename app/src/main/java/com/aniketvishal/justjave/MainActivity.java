/**
 * Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 *
 * package com.example.android.justjava; 
 */
package com.aniketvishal.justjave;

import android.content.Intent;
import android.content.IntentSender;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

import static android.R.attr.checked;
import static android.R.attr.name;
import static android.R.id.message;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity =2;
    boolean checked1;
    boolean checked2;
    String name;
    String message;
    int flag=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {

        EditText et = (EditText) findViewById(R.id.name_view);
        name=et.getText().toString();

        int price = calculation();
        String message;
        message = orderSum(price);
        displayMessage(message);
        flag = 1;


//            Intent intent = new Intent(Intent.ACTION_SENDTO);
//            intent.setData(Uri.parse("mailto:")); // only email apps should handle this
//            intent.putExtra(Intent.EXTRA_SUBJECT, "It is just an Example of Intent");
//            intent.putExtra(Intent.EXTRA_EMAIL, new String[] { "test@gmail.com" });
//            intent.putExtra(Intent.EXTRA_TEXT, message);
//            if (intent.resolveActivity(getPackageManager()) != null) {
//                startActivity(intent);
//            }

    }

    public void send_email(View view) {

        if(flag == 0){
            Toast.makeText(this, "First display your order than click Send again", Toast.LENGTH_SHORT).show();
        }

        else {
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:")); // only email apps should handle this
            intent.putExtra(Intent.EXTRA_SUBJECT, "It is just an Example of Intent");
            intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"test@gmail.com"});
            intent.putExtra(Intent.EXTRA_TEXT, message);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        }
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    public void onCheckbox1Clicked(View view) {
        // Is the view now checked?
        checked1 = ((CheckBox) view).isChecked();
    }

    public void onCheckbox2Clicked(View view) {
        // Is the view now checked?
        checked2 = ((CheckBox) view).isChecked();
    }

    public void increment(View view) {
        if(quantity < 100) {
            quantity = quantity + 1;
            display(quantity);
        }
        else {
            Toast.makeText(this, "You can't have more then 100 cups of coffee", Toast.LENGTH_SHORT).show();
        }
    }

    public void decrement(View view) {
        if(quantity >=2 ){
            quantity = quantity - 1;
            display(quantity);
        }
        else {
            Toast.makeText(this, "You can't have less then 1 cup of coffee", Toast.LENGTH_SHORT).show();
        }
    }

    private void displayMessage(String message1){
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message1);
    }

    private int calculation() {
        int price = (quantity * 5);
        return price;
    }

    private String orderSum(int price) {
        message = "Name = " + name;
        if (checked1 && checked2) {
            message += "\nBoth WC and Chocolate is chacked ?" + checked1;
            message += "\nQuantity = " + quantity;
            message += "\nTotal Rs.= " + (price+3);
        }
        else if (checked1) {
            message += "\nIs WC chacked ?" + checked1;
            message += "\nQuantity = " + quantity;
            message += "\nTotal = Rs." + (price+1);
        }
        else if (checked2){
            message += "\nIs Chocolate chacked ?" + checked2;
            message += "\nQuantity = " + quantity;
            message += "\nTotal Rs.= " + (price+2);
        }
        else {
            message += "No WC or Chocolate";
            message += "\nQuantity = " + quantity;
            message += "\nTotal Rs.= " + price;
        }
        message += "\nThank you!";
        return message;
    }
}