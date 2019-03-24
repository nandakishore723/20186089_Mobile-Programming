package com.example.ecommerce;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class CartActivity extends AppCompatActivity {

    protected RecyclerView cartList;

    protected LinearLayoutManager linearLayoutManager;
    protected DividerItemDecoration dividerItemDecoration;
    protected RecyclerView.Adapter adapter;

    protected TextView totalAmountDisplay;

    protected Button placeOrderButton, backToShoppingButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        totalAmountDisplay = findViewById(R.id.tv_total);

        cartList = findViewById(R.id.recycler_cart);
        adapter = new CartAdaptor(getApplicationContext());

        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        dividerItemDecoration = new DividerItemDecoration(cartList.getContext(), linearLayoutManager.getOrientation());

        cartList.setHasFixedSize(true);
        cartList.setLayoutManager(linearLayoutManager);
        cartList.addItemDecoration(dividerItemDecoration);



        cartList.setAdapter(adapter);

        calculateTotalAmount();
    }

    private void calculateTotalAmount() {
        int total = 0;
        for (String key : GlobalClass.cart.keySet()) {
            total +=  GlobalClass.cart.get(key).TotalPrice;
        }
        totalAmountDisplay.setText(new StringBuilder().append("EUR ").append(String.valueOf(total)).toString());
    }
}
