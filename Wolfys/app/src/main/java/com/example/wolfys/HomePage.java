package com.example.wolfys;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

public class HomePage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
DrawerLayout drawerLayout;
NavigationView navigationView;
Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager2 viewPager2;
    MyViewPagerAdapter myViewPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        //fragment viewer
        tabLayout=findViewById(R.id.tabswitch);
        viewPager2=findViewById(R.id.view_pager);
        myViewPagerAdapter=new MyViewPagerAdapter(this);
        viewPager2.setAdapter(myViewPagerAdapter);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tabLayout.getTabAt(position).select();
            }
        });

        //navigation drawer
        drawerLayout=findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.nav_view);
        toolbar=findViewById(R.id.toolbar_icon);
        setSupportActionBar(toolbar);//toolbar



        navigationView.bringToFront();
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setCheckedItem(R.id.nav_home);
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

        if (itemId == R.id.nav_home) {
            // Do nothing for "Home" item
        } else if (itemId == R.id.nav_orders) {
            Intent intent = new Intent(HomePage.this, OrderPage1.class);
            startActivity(intent);
        } else if (itemId == R.id.nav_share) {
            Toast.makeText(this, "Your Profile is Shared!", Toast.LENGTH_SHORT).show();
        }
        else if (itemId == R.id.nav_logout) {
            // Handle "Share" click
            Toast.makeText(this, "Logging you out", Toast.LENGTH_SHORT).show();
            finishAffinity();
            System.exit(0);
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


}