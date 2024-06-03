package com.example.a2247214_cat3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class HomepageActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{


    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    String email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("email")) {
            email = intent.getStringExtra("email");
        }

        FragmentManager fragmentManager = getSupportFragmentManager();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {


                Fragment fragment = null;
                int itemId = item.getItemId();

                if (itemId == R.id.home) {
                    fragment = new Home(email);
                } else if (itemId == R.id.Events) {
                    fragment = new Events();
                }else if (itemId == R.id.Profile) {
                    fragment = new Profile(email);
                }

                if (fragment != null) {
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    transaction.replace(R.id.container, fragment);
                    transaction.commit();
                }
                return true;
            }
        });

        // Set default fragment
        bottomNavigationView.setSelectedItemId(R.id.home);

        drawerLayout=findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.nav_view);
        toolbar=findViewById(R.id.toolbar_icon);
        setSupportActionBar(toolbar);//toolbar



        navigationView.bringToFront();
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setCheckedItem(R.id.home);


    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        final int itemId = menuItem.getItemId();

        if (itemId == R.id.logout) {
            Intent intent = new Intent(HomepageActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

}