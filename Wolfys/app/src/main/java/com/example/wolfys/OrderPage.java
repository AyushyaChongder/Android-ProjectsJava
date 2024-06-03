package com.example.wolfys;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class OrderPage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    private RecyclerView cartRecyclerView;
    private CartAdapter cartAdapter;
    private ArrayList<VegetableModel> selectedItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_page);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar_icon);
        setSupportActionBar(toolbar);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setCheckedItem(R.id.nav_orders);

        // Manually add items to the selectedItems list (for the cart)
        selectedItems.add(new VegetableModel(1, "Tomato", R.drawable.tomato));
        selectedItems.add(new VegetableModel(2, "Cucumber", R.drawable.cucumber));
        selectedItems.add(new VegetableModel(3, "Cabbage", R.drawable.cabbage));
        selectedItems.add(new VegetableModel(4, "Mango", R.drawable.mango));
        selectedItems.add(new VegetableModel(5, "Rice", R.drawable.rice));
        selectedItems.add(new VegetableModel(6, "Maggi", R.drawable.maggi));

        // Initialize RecyclerView for the cart and CartAdapter
        cartRecyclerView = findViewById(R.id.cartRecyclerView);
        cartAdapter = new CartAdapter(selectedItems);
        cartRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        cartRecyclerView.setAdapter(cartAdapter);
        // Find the "Place Order" button and set its click listener
        Button placeOrderButton = findViewById(R.id.placeorder);
        placeOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Clear the selectedItems list and notify the adapter
                selectedItems.clear();
                cartAdapter.notifyDataSetChanged();

                // Display a toast message for placing the order
                Toast.makeText(OrderPage.this, "Order has been placed", Toast.LENGTH_SHORT).show();
            }
        });
    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        final int itemId = menuItem.getItemId();

        if (itemId == R.id.nav_home) {
            Intent intent = new Intent(OrderPage.this, HomePage.class);
            startActivity(intent);
        } else if (itemId == R.id.nav_orders) {
            // Already on the OrderPage
            Intent intent = new Intent(OrderPage.this, OrderPage1.class);
            startActivity(intent);
        } else if (itemId == R.id.nav_share) {
            // Handle "Share" click
            Toast.makeText(this, "Your Profile is Shared!", Toast.LENGTH_SHORT).show();
        } else if (itemId == R.id.nav_logout) {
            // Handle "Share" click
            Toast.makeText(this, "Logging you out", Toast.LENGTH_SHORT).show();
            finishAffinity();
            System.exit(0);
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
