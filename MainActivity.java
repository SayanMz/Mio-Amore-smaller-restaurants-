package com.example.mioamoreshop;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    int quantityLC, quantityVC, quantityCC, quantityBC = 0;
    int priceLC = 120, priceVC = 540, priceCC = 330, priceBC = 460;
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
//Quantity section
    public void incrementLC(View view) {
        quantityLC = quantityLC + 1;
        QLC(quantityLC);
    }
    public void decrementLC(View view) {
        if(quantityLC>0) {
            quantityLC = quantityLC - 1;
            QLC(quantityLC);
        }
    }
    private void QLC(int number) {
        TextView quantity = findViewById(R.id.quantityLC);
        quantity.setText("" + number);
    }
    public void incrementVC(View view) {
        quantityVC = quantityVC + 1;
        QVC(quantityVC);
    }
    public void decrementVC(View view) {
        if(quantityVC>0) {
            quantityVC = quantityVC - 1;
            QVC(quantityVC);
        }
    }
    private void QVC(int number) {
        TextView quantity = findViewById(R.id.quantityVC);
        quantity.setText("" + number);
    }
    public void incrementCC(View view) {
        quantityCC = quantityCC + 1;
        QCC(quantityCC);
    }
    public void decrementCC(View view) {
        if(quantityCC>0) {
            quantityCC = quantityCC - 1;
            QCC(quantityCC);
        }
    }
    private void QCC(int number) {
        TextView quantity = findViewById(R.id.quantityCC);
        quantity.setText("" + number);
    }
    public void incrementBC(View view) {
        quantityBC = quantityBC + 1;
        QBC(quantityBC);
    }
    public void decrementBC(View view) {
        if(quantityBC>0) {
            quantityBC = quantityBC - 1;
            QBC(quantityBC);
        }
    }
    private void QBC(int number) {
        TextView quantity = findViewById(R.id.quantityBC);
        quantity.setText("" + number);
    }
//Order summary section
    private String displayName() {
        EditText text = (EditText) findViewById(R.id.name);
        String value = text.getText().toString();
        return value;
    }
    private void displayMessage(String name) {
        TextView orderSummary = findViewById(R.id.orderSummary);
        orderSummary.setText(name);
    }
    private int calculatePrice() {
        int price;
        price = quantityLC * priceLC;
        price = price + quantityVC * priceVC;
        price = price + quantityCC * priceCC;
        price = price + quantityBC * priceBC;
        return price;
    }
    public String checkBox() {
        int a = 0;
        String finalOutput = new String();
        if(quantityLC>0) {
            a = a + 1;
            finalOutput = a + ")" + "Lemon Cake: " + quantityLC + "pcs.\n";
        }
        if(quantityVC>0) {
            a = a+1;
            finalOutput = finalOutput + a + ")" + "Vanilla Cake: " + quantityVC + "pcs.\n";
        }
        if(quantityCC>0) {
            a = a+1;
            finalOutput = finalOutput + a + ")" + "Chocolate Cake: " + quantityCC + "pcs.\n";
        }
        if(quantityBC>0) {
            a = a+1;
            finalOutput = finalOutput + a + ")" + "Birthday Cake: " + quantityBC + "pcs.";
        }
        return finalOutput;
    }
    public void submitOrder(View view) {
        String priceMessage = createOrderSummary();
        displayMessage(priceMessage);
    }
    private String createOrderSummary() {
        String orderSummary = "Hi, " + displayName() + "\nThis is your order: \n";
orderSummary = orderSummary + checkBox() + "\nTotal price: " + "₹" + calculatePrice();
orderSummary = orderSummary + "\nThank you.";
        return orderSummary;
    }
//Order to Intent section
    public void orderNow(View view) {
    String orderSummary = createOrderSummary();
    composeEmail(orderSummary);
    }
    public void composeEmail(String message) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_SUBJECT, message);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}