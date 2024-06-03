package com.example.cat3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.cat3.fragments.CheckedItemsFragment;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

public class Homepage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    NotificationManagerCompat notificationManagerCompat;
    Notification notification;

    TabLayout tabLayout;
    ViewPager2 viewPager2;
    MyViewPagerAdapter myViewPagerAdapter;
    private String userEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        // Retrieve the user's email from the Intent extras
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("user_email")) {
            userEmail = intent.getStringExtra("user_email");
        }
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
        } else if (itemId == R.id.nav_profile) {
            Intent intent = new Intent(Homepage.this, ProfilePage.class);
            intent.putExtra("user_email", userEmail);
            startActivity(intent);
        }

        else if(itemId==R.id.nav_items)
        {
            Intent intent = new Intent(Homepage.this, CheckedItemsFragment.class);
            startActivity(intent);
        }

        else if (itemId == R.id.nav_share) {
            Toast.makeText(this, "Your Profile is Shared!", Toast.LENGTH_SHORT).show();
        }
        else if (itemId == R.id.nav_logout) {
            // Handle "Share" click
            Toast.makeText(this, "Logging you out", Toast.LENGTH_LONG).show();
            showNotification("You have successfully logged out!");
            Intent intent = new Intent(Homepage.this, MainActivity.class);
            startActivity(intent);
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
    @SuppressLint("MissingPermission")
    private void showNotification(String message) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel();
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "mych")
                .setSmallIcon(R.drawable.notification_icon)
                .setContentTitle("Login/Signup Status")
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        notification = builder.build();

        notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(1, notification);

    }

    private void createNotificationChannel() {
        NotificationChannel channel = new NotificationChannel("mych", "My Channel", NotificationManager.IMPORTANCE_DEFAULT);
        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);
    }

}