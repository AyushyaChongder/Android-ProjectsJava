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

import com.google.android.material.navigation.NavigationView;

public class OrderPage1 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_page1);

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


        Button placeOrderButton = findViewById(R.id.placeorder);
        placeOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Display a toast message for placing the order
                Toast.makeText(OrderPage1.this, "Add something to cart!!", Toast.LENGTH_SHORT).show();
            }
        });
    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        final int itemId = menuItem.getItemId();

        if (itemId == R.id.nav_home) {
            Intent intent = new Intent(OrderPage1.this, HomePage.class);
            startActivity(intent);
        } else if (itemId == R.id.nav_orders) {
            // Already on the OrderPage
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
